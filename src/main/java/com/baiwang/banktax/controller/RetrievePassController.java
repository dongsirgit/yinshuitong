/**
 *Copyright (c) 1997, 2015,BEST WONDER CO.,LTD. All rights reserved.
 */

package com.baiwang.banktax.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baiwang.banktax.beans.Cuser;
import com.baiwang.banktax.services.iface.IPuserService;
import com.baiwang.banktax.services.iface.IUserService;
import com.baiwang.banktax.utils.ConfigUtil;
import com.baiwang.banktax.utils.Constant;
import com.baiwang.banktax.utils.PassWordUtils;
import com.baiwang.banktax.utils.StringUtils;
import com.baiwang.banktax.utils.mail.MailServiceClient;

/**
 * @ClassName: RetrievePassController
 * @Description: 找回密码，修改密码等
 * @author 张衡
 * @date 2015年8月3日 下午2:50:37
 */
@Controller
@RequestMapping("/retrieve")
public class RetrievePassController {

    private static final Log logger = LogFactory.getLog(RetrievePassController.class);
    @Resource
    private IUserService userService;

    @Resource
    private IPuserService puserService;

    @Resource
    private MailServiceClient mailServiceClient;

    /**
     * 
     * @Description 比对用户名和邮箱。如果正确的话，发送邮件修改密码。
     * @param user Cuser
     * @param request HttpServletRequest
     * @return String
     */
    @RequestMapping(value = "/sentMail", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> sentMail(Cuser user, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        //未获得前台用户名
        if (null == user || null == user.getUserName() || null == user.getMail()) {
            result.put("flag", Constant.USER_NAME_ERROR);
            return result;
        }
        //产生随机密码
        String newPwd = random();
        String newPwdMD5 = PassWordUtils.encodeByMD5(newPwd);
        //开始修改密码
        Cuser c_user = userService.selectByNameAndMail(user);
        if(null == c_user){
//            Puser p_user = puserService.selectByNameAndMail(users);
//            if(null == p_user){
//                // 当查出来的用户为null的时候，向前台发送用户名错误码
//               result.put("flag", Constant.USER_NAME_ERROR);
//               return result;
//            }else{
//               p_user.setUserPass(newPwdMD5);   
//               puserService.updateSelectiveByUsername(p_user);
//           }
            result.put("flag", Constant.USER_NAME_ERROR);
            return result;
        }else{
            c_user.setUserPass(newPwdMD5);
            userService.updatePassById(c_user);
        }
        //开始发送邮件
        String sentmail = ConfigUtil.get("sentMail");
        String findPwd = ConfigUtil.get("findPwds");
        // 从config配置文件中获得“您好，欢迎使用银税互动平台。登录用户名：nbatoo，新的密码是:856377，请在登录后及时修改密码”。将用户名和密码替换为用户自己的。
        String textm = sentmail.replace("nbatoo", user.getUserName()).replace("856377", newPwd);
        // 在邮件中添加立即登录按钮
        StringBuffer textmodify = new StringBuffer();
        textmodify.append("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"
                + "</head><body stytle='font-size:14px;'><br>");
        textmodify.append(textm);
        textmodify.append("<a href=" + findPwd + ">点击登录</a>");
        textmodify.append("</body></html>");
        String lasttext = textmodify.toString();
        String subject = "重置密码";
        /*String mail = user.getMail();
        String[] to = new String[1];
        to[0] = mail;*/
        try {
            String sentStatus = mailServiceClient.sendEmail(user.getMail(), subject, lasttext,"",true);
            if ("success" == sentStatus) {
                logger.info("发送邮件成功");
                result.put("flag", Constant.SUCCESS);
            } else {
                logger.info("发送邮件失败，服务器发送邮件没开");
                result.put("flag", Constant.RETRIEVE_PASS_LATER);
            }
        } catch (Exception e) {
            logger.error("发送邮件失败" + e.getMessage());
            result.put("flag", Constant.RETRIEVE_PASS_LATER);
            e.printStackTrace();
        }

        return result;     
        
    }

    /**
     * 
     * @Description 随机产生6位数字
     * @return String
     */
    public static String random() {
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        // 6位验证码
        for (int i = 0; i < 6; i++) {
            code.append(String.valueOf(random.nextInt(10)));
        }
        String chars = "abcdefghijklmnopqrstuvwxyz";
        code.append(chars.charAt((int) (Math.random() * 26)));

        return code.toString();
    }

    /**
     * 
     * @Description 转发到邮件已发送成功界面
     * @param mail String
     * @param request HttpServletRequest
     * @return String
     */
    @RequestMapping(value = "/forwardSentSuccess")
    public String forwardSentSuccess(String mail, HttpServletRequest request) {
        mail=StringUtils.filterDangerString(mail);
        int a = mail.indexOf("@");
        if (a > 0) {
            String[] mails = mail.split("@");
            String mailR = mails[1];
            String maild = "http://mail.";
            String mailServer = maild.concat(mailR);
            request.setAttribute("mail", mailServer);
            return "user/findpwd2";
        } else {
            return "user/findpwd";
        }
    }

    /**
     * 
     * @Description: 转发到帮助界面 
     * @return String
     */
    @RequestMapping("/forwardHelp")
    public String forwardHelp() {
        return "user/help";
    }

}
