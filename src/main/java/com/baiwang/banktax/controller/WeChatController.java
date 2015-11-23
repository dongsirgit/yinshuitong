package com.baiwang.banktax.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baiwang.banktax.beans.Cuser;
import com.baiwang.banktax.services.iface.IUserService;
import com.baiwang.banktax.utils.ConfigUtil;
import com.baiwang.banktax.utils.StringUtils;
import com.baiwang.banktax.utils.wechat.WeChatSupport;

/**
 * 微信接口调用及响应的总控制类
 * 
 * @ClassName: WeChatController
 * @author liujingui
 * @date 2015年10月12日 下午5:32:59
 */
@Controller
@RequestMapping("/wechat")
public class WeChatController {

    private static final Log logger = LogFactory.getLog(WeChatController.class);

    @Resource
    private IUserService userService;

    /**
     * 处理微信企业公众号的回调请求的入口方法
     * 
     * @author liujingui
     * @param request
     *            HttpServletRequest
     * @param msg_signature
     *            加密签名
     * @param timestamp
     *            时间戳
     * @param nonce
     *            随机字符串
     * @param echostr
     *            加密数据
     * @return String 回复给微信企业公众号的请求的字符串
     * @date 2015年10月14日 下午3:50:19
     */
    @RequestMapping(value = "/processor", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String processor(HttpServletRequest request, String msg_signature, String timestamp, String nonce,
            String echostr) {
        logger.info("微信企业公众平台的回调请求接入！");
        msg_signature = StringUtils.getString(msg_signature);
        timestamp = StringUtils.getString(timestamp);
        nonce = StringUtils.getString(nonce);
        if ("".equals(msg_signature) || "".equals(timestamp) || "".equals(nonce)) {
            logger.error("微信企业公众平台的回调请求有误！");
            return "";
        }
        if (null != echostr) {
            // 此次请求是微信平台的验证URL链接的请求,验证成功,则成功接入微信接口
            return WeChatSupport.vertify_signature(msg_signature, timestamp, nonce, echostr);

        } else {
            // 此次请求是点击事件或用户消息的回调请求
            // 解析出消息的明文xml文档
            Document msgXml = WeChatSupport.decryptPostData(request, msg_signature, timestamp, nonce);
            if (null == msgXml)
                return "";
            //假设是点击事件的回调请求,获取点击按钮的对应文本
            String contents = getContentsByKey(msgXml, msg_signature, timestamp, nonce, userService);
            if (!"".equals(StringUtils.getString(contents))) {
                return contents;
            } else {
                //假设是用户发送的消息的回调请求,返回包含邀请码的URL链接
                return getInviteCodeByText(msgXml, msg_signature, timestamp, nonce, userService, request);
            }

        }

    }

    /**
     * 处理微信公众平台的回调请求,根据用户点击的不同键值,获取并返回不同内容
     * 
     * @author liuJingui
     * @param msgXml
     *            Document 回调消息的明文xml对象
     * @param msg_signature
     *            加密签名
     * @param timestamp
     *            时间戳
     * @param nonce
     *            随机字符串
     * @param userService
     *            IUserService 操作用户表的服务层对象
     * @return String 包含键值对应内容的加密xml字符串
     * @date 2015年10月15日 上午9:59:25
     */
    public static String getContentsByKey(Document msgXml, String msg_signature, String timestamp, String nonce,
            IUserService userService) {

        try {
            // 获取点击的按钮类型
			@SuppressWarnings("unchecked")
			List<Element> eventKeyNode = msgXml.getRootElement().elements("EventKey");
            if (eventKeyNode.size() == 0)
                return "";
            String eventKey = eventKeyNode.get(0).getStringValue();
            // 如果用户点击的是对应按钮
            if ("baiwang_introduction".equals(eventKey) || "qiyedai".equals(eventKey) || "farendai".equals(eventKey)) {
                logger.info("用户点击的按钮key值=>" + eventKey);
                // 获取用户id,即其邮箱
                @SuppressWarnings("unchecked")
				List<Element> fromUserNameNode = msgXml.getRootElement().elements("FromUserName");
                if (fromUserNameNode.size() == 0)
                    return "";
                String userEmail = fromUserNameNode.get(0).getStringValue();
                String contents = WeChatSupport.getContents(eventKey);
                return WeChatSupport.generateReplyXml(userEmail, Long.toString(System.currentTimeMillis()), "text",
                        contents);
            }

        } catch (Exception e) {
            logger.error("处理回调请求发生错误！");
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 
     * 处理微信公众平台的回调请求,如果是用户发送"邀请码"消息的回调请求,则生成包含邀请码链接URL的加密xml字符串,并返回
     * 
     * @author liuJingui
     * @param msgXml
     *            Document 回调消息的明文xml对象
     * @param msg_signature
     *            加密签名
     * @param timestamp
     *            时间戳
     * @param nonce
     *            随机字符串
     * @param userService
     *            IUserService 操作用户表的服务层对象
     * @param request
     *            HttpServletRequest
     * @return String 包含邀请码链接的URL加密xml字符串
     * @date 2015年10月20日 下午2:36:03
     */
    public String getInviteCodeByText(Document msgXml, String msg_signature, String timestamp, String nonce,
            IUserService userService, HttpServletRequest request) {

        try {
            @SuppressWarnings("unchecked")
			List<Element> contentNode = msgXml.getRootElement().elements("Content");
            if (contentNode.size() == 0)
                return "";
            String content = contentNode.get(0).getStringValue();
            // 如果用户点击的是"获取链接"按钮
            if ("邀请码".equals(content)) {
                // 获取包含全部用户信息的封装对象
                Cuser defaultUser = WeChatSupport.getDefaultUser(msgXml);
                if (null == defaultUser)
                    return "";
                // 根据用户表中的用户Id生成邀请码
                String inviteCode = WeChatSupport.generateInviteCode(defaultUser, request, userService);
                if (null == inviteCode)
                    return "";
                String replyMsg = ConfigUtil.get("replyMsg.template").replace("@", inviteCode);
                // 生成加密后的包含返回信息的xml字符串,并返回
                return WeChatSupport.generateReplyXml(defaultUser.getMail(), Long.toString(System.currentTimeMillis()),
                        "text", replyMsg);
            }

        } catch (Exception e) {
            logger.error("处理回调请求发生错误！");
            e.printStackTrace();
        }
        return "";
    }

}
