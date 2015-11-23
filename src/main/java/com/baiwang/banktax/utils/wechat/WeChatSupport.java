package com.baiwang.banktax.utils.wechat;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baiwang.banktax.beans.Cuser;
import com.baiwang.banktax.services.iface.IUserService;
import com.baiwang.banktax.utils.ConfigUtil;
import com.baiwang.banktax.utils.HttpRequester;
import com.baiwang.banktax.utils.IPUtil;
import com.baiwang.banktax.utils.PassWordUtils;
import com.baiwang.banktax.utils.StringUtils;

/**
 * 此类封装了所有的支持微信公众平台接口调用的服务方法
 * 
 * @ClassName: WeChatSupport
 * @author liujingui
 * @date 2015年10月15日 上午9:10:59
 */
public class WeChatSupport {

    private static final Log logger = LogFactory.getLog(WeChatSupport.class);

    private static final String token = ConfigUtil.get("Token");
    private static final String corpId = ConfigUtil.get("CorpID");
    private static final String encodingAESKey = ConfigUtil.get("EncodingAESKey");
    private static final String corpSecret = ConfigUtil.get("CorpSecret");
    private static final String getAccessToken_link = ConfigUtil.get("getAccessToken_link");
    private static final String getUserInfo_link = ConfigUtil.get("getUserInfo_link");
    private static final String AccessToken_ExpiresTime = ConfigUtil.get("AccessToken.ExpiresTime");
    private static final String defaultPwd = ConfigUtil.get("defaultPwd");

    private static Map<String, String> accessTokenMap = new HashMap<String, String>();

    static {
        accessTokenMap.put("accessToken", "");
        accessTokenMap.put("saveTime", "0");
    }

    /**
     * 先从内存中的变量accessTokenMap中获取accessToken,没有或者过期则去调用微信公众平台的接口获取
     * 
     * @author liujingui
     * @return accessToken String
     * @date 2015年10月14日 下午1:57:15
     */
    public static String getAccessToken() {
        String accessToken = (String) accessTokenMap.get("accessToken");
        String saveTime = (String) accessTokenMap.get("saveTime");
        if (System.currentTimeMillis() - Long.parseLong(saveTime) >= Long.parseLong(AccessToken_ExpiresTime) * 1000) {
            accessToken = "";
        }
        if ("".equals(accessToken)) {
            String responseResult = HttpRequester.sendGet(getAccessToken_link,
                    "corpid=" + corpId + "&corpsecret=" + corpSecret);
            JSONObject responseJson = JSON.parseObject(responseResult);
            accessToken = responseJson.getString("access_token");
            logger.info("调用微信平台接口获取的accessToken => " + accessToken);
            accessTokenMap.put("accessToken", accessToken);
            accessTokenMap.put("saveTime", Long.toString(System.currentTimeMillis()));
        } else {
            logger.info("从内存变量accessTokenMap中获取到有效accessToken=>" + accessToken);
        }
        return accessToken;
    }

    /**
     * 回调模式下，微信企业公众号管理界面中主动点击验证，会发送请求至服务器,然后调用此方法进行验证,并返回明文echostr
     * 
     * @author liujingui
     * @param msg_signature
     *            微信加密签名(结合了企业填写的token、请求中的timestamp、nonce参数、加密的消息体 )
     * @param timestamp
     *            时间戳
     * @param nonce
     *            随机数
     * @param echostr
     *            加密的随机字符串(需要解密并返回echostr明文,解密后有random,msg_len,msg,$CorpID四个字段,
     *            其中msg即为echostr明文)
     * @return String echostr明文
     * @date 2015年10月12日 下午5:36:54
     */
    public static String vertify_signature(String msg_signature, String timestamp, String nonce, String echostr) {

        String realEchostr = null;
        try {
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token, encodingAESKey, corpId);
            realEchostr = wxcpt.VerifyURL(msg_signature, timestamp, nonce, echostr);
            logger.info("微信接口连接->解密echostr后明文:" + realEchostr);
            return realEchostr;
        } catch (AesException e) {
            logger.error("连接微信接口时,验证失败");
            e.printStackTrace();
        }
        return realEchostr;
    }

    /**
     * 根据用户表的userid生成邀请码,如果表中用户不存在,先注册
     * 
     * @author liujingui
     * @param defaultUser
     *            封装了用户手机号,用户邮箱,用户姓名
     * @param request
     *            HttpServletRequest
     * @param userService
     *            用户表操作服务方法
     * @return String 邀请码
     * @date 2015年10月14日 下午6:11:37
     */
    public static String generateInviteCode(Cuser defaultUser, HttpServletRequest request, IUserService userService) {
        String inviteCode = null;
        try {
            Cuser user = userService.selectUserByMail(defaultUser.getMail());
            if (null != user) {
                logger.info("用户已使用邮箱注册过账号,根据其账号Id获取邀请码！");
                inviteCode = StringUtils.inviteCode(user.getId());
            } else {
                user = userService.selectByPrimaryKey(defaultUser.getMobilephone());
                if (null != user) {
                    logger.info("用户已使用手机号作为用户名注册过账号,根据其账号Id获取邀请码！");
                    inviteCode = StringUtils.inviteCode(user.getId());
                } else {
                    logger.info("用户未使用过邮箱或者用手机号作为用户名注册过账号,则为其注册新账号！");
                    defaultUser.setUserPass(PassWordUtils.createPassword(defaultPwd));
                    defaultUser.setRegIp(IPUtil.getIpAddr(request));
                    defaultUser.setUserType((byte) 22);
                    userService.insertSelective(defaultUser);
                    user = userService.selectByNameAndMail(defaultUser);
                    inviteCode = StringUtils.inviteCode(user.getId());
                }
            }
        } catch (Exception e) {
            logger.error("获取用户邀请码时发生错误");
            e.printStackTrace();
        }

        return inviteCode;
    }

    /**
     * 根据回调消息的明文xml中FromUserName，调用微信平台接口,获取用户的全部信息
     * 
     * @author liujingui
     * @param msgXml
     *            点击事件的产生的明文xml对象
     * @return JSONObject 用户全部信息的JSON对象
     * @date 2015年10月14日 下午6:25:05
     */
    public static JSONObject getUserInfo(Document msgXml) {

        try {
            @SuppressWarnings("unchecked")
			List<Element> fromUserNameNode = msgXml.getRootElement().elements("FromUserName");
            if (fromUserNameNode.size() == 0)
                return null;
            String userEmail = fromUserNameNode.get(0).getStringValue();
            String accessToken = getAccessToken();
            String userInfoJsonString = HttpRequester.sendGet(getUserInfo_link,
                    "access_token=" + accessToken + "&userid=" + userEmail);
            return JSON.parseObject(userInfoJsonString);
        } catch (Exception e) {
            logger.error("根据点击事件中用户Id,调用微信平台接口,获取用户全部信息时发生错误！");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将要返回的明文字符串打包成xml,并加密后返回
     * 
     * @author liujingui
     * @param userEmail
     *            用户ID,即邮箱
     * @param createTime
     *            消息创建时间,自己生成
     * @param msgType
     *            消息类型,这里都为text
     * @param replyMsg
     *            要返回的明文字符串
     * @return String 打包并加密的的回复字符串
     * @date 2015年10月16日 下午1:21:46
     */
    public static String generateReplyXml(String userEmail, String createTime, String msgType, String replyMsg) {
        try {
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token, encodingAESKey, corpId);
            String formatString = "<xml>\n<ToUserName><![CDATA[%1$s]]></ToUserName>\n<FromUserName><![CDATA[%2$s]]></FromUserName>\n<CreateTime>%3$s</CreateTime>\n<MsgType><![CDATA[%4$s]]></MsgType>\n<Content><![CDATA[%5$s]]></Content>\n</xml>";
            // 打包信息成为xml字符串
            String realMsg = String.format(formatString, userEmail, corpId, createTime, msgType, replyMsg);
            // 加密打包信息,并返回
            return wxcpt.EncryptMsg(realMsg, createTime, StringUtils.getRandomString());
        } catch (AesException e) {
            logger.error("打包及加密要回复的消息时发生错误");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据加密签名,时间戳,随机字符串等信息,解析出request请求中的postData,返回明文xml对象
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
     * @return 明文的消息xml对象
     * @date 2015年10月15日 上午9:20:52
     */
    public static Document decryptPostData(HttpServletRequest request, String msg_signature, String timestamp,
            String nonce) {
        try {
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token, encodingAESKey, corpId);
            InputStream inputStream = request.getInputStream();
            String postData = IOUtils.toString(inputStream, "UTF-8");
            // 解密出消息的明文xml
            String msgString = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, postData);
            return DocumentHelper.parseText(msgString);
        } catch (Exception e) {
            logger.error("解密request中的xml明文信息时发生错误！");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 先根据微信平台的回调消息中的明文xml的FromUserName,调用微信平台接口去获取全部的用户信息,然后封装成对象返回
     * 
     * @author liujingui
     * @param msgXml
     *            微信平台的回调消息中的明文xml对象
     * @return Cuser 包含回调消息中的所有用户信息的封装对象
     * @date 2015年10月20日 下午2:45:12
     */
    public static Cuser getDefaultUser(Document msgXml) {
        // 获取包含全部用户信息的json对象
        JSONObject userInfoJson = WeChatSupport.getUserInfo(msgXml);
        if (null == userInfoJson)
            return null;
        String userName = StringUtils.getString(userInfoJson.getString("name"));
        String userMobile = StringUtils.getString(userInfoJson.getString("mobile"));
        String userEmail = StringUtils.getString(userInfoJson.getString("email"));
        Cuser defaultUser = new Cuser();
        defaultUser.setUserName(userMobile);
        defaultUser.setMobilephone(userMobile);
        defaultUser.setApName(userName);
        defaultUser.setMail(userEmail);

        return defaultUser;
    }
    
    /**
     * 根据键值获取具体内容的字符串
      * @author liujingui
      * @param key 按钮关键字
      * @return String 要获取的字符串
      * @date 2015年10月20日 下午4:11:16
     */
    public static String getContents(String key){
        return ConfigUtil.get(key);
    }
}
