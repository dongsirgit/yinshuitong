package com.baiwang.banktax.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.baiwang.banktax.beans.Cuser;
import com.baiwang.banktax.beans.Puser;
import com.baiwang.banktax.services.iface.IPuserService;
import com.baiwang.banktax.services.iface.IUserService;
import com.baiwang.banktax.utils.ConfigUtil;
import com.baiwang.banktax.utils.Constant;
import com.baiwang.banktax.utils.IDCard;
import com.baiwang.banktax.utils.IPUtil;
import com.baiwang.banktax.utils.StringUtils;

/**
 * 
 * @ClassName: UserController
 * @Description: 用户模块控制器
 * @author 张衡
 * @date 2015年7月24日 上午8:58:32
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Log logger = LogFactory.getLog(UserController.class);
    private static final String sessionInfo = ConfigUtil.getSessionInfoName();
    @Resource
    private IUserService userService;

    @Resource
    private IPuserService puserService;

    /**
     * 
     * @Description 转发到找回密码界面
     * @return String
     */
    @RequestMapping("/findPwd")
    public String findPwd() {
        return "user/findpwd";
    }

    /**
     * 
     * @Description: 转发到注册界面
     * @param user
     *            Cuser
     * @return String
     */
    @RequestMapping("/forwardRegist")
    public String forwardRegist(Cuser user, String inviteid, HttpServletRequest request) {
        request.setAttribute("inviteId", inviteid);
        return "../regist";
    }

    /**
     * 
     * @Description 可以直接发送请求，访问登陆界面
     * @param session
     * @return String
     */
    @RequestMapping("/forwardLogin")
    public String forwardLogin(HttpSession session) {
        String sessionInfo = ConfigUtil.getSessionInfoName();
        String typeUser = (String) session.getAttribute("typeUser");
        Cuser c_user = null;
        Puser p_user = null;
        // 从0判断是企业还是个人用户，如果已经登录不让其再访问登录界面
        if ("0".equals(typeUser)) {
            c_user = (Cuser) session.getAttribute(sessionInfo);
        } else {
            p_user = (Puser) session.getAttribute(sessionInfo);
        }
        if (null == c_user && null == p_user) {
            return "../login";
        } else {
            return "../../index";
        }
    }

    /**
     * 注册处理controller,注册成功后,跳入注册成功提示页面
     * 
     * @author liujingui
     * @param user
     *            Cuser
     * @param code
     *            String
     * @param regType
     *            String
     * @param request
     *            HttpServletRequest
     * @return String
     * @date 2015年10月21日 下午1:55:57
     */
    @RequestMapping(value = "/toLogin", method = RequestMethod.POST)
    public String toLogin(Cuser user, String code, String regType, HttpServletRequest request) {

        HttpSession session = request.getSession();
        // 图形验证码验证
        if (!checkcode(code, session)) {
            request.setAttribute("flag", Constant.IMAGE_CODE_ERROR);
            request.setAttribute("user", JSONObject.toJSONString(user));
            return "../regist";
        }

        // 检查用户注册信息完整性及危险字符
        if (!checkUserInfo(user)) {
            return "../../index";
        }

        try {
            /* if("company".equals(regType)){ */ // 取消个人用户业务模块
            user.setRegIp(IPUtil.getIpAddr(request));
            // 如果inviteid为null或为空或存在不是转换数组中的字符时,邀请人id=0
            long uId = StringUtils.deInviteCode(StringUtils.getString(user.getInviteCode()).toUpperCase());
            // 只保存存在的此邀请id,否则存0
            if (null != userService.selectById(uId)) {
                user.setInviteUid(uId);
            }else{
                user.setInviteUid(0l);
            }
            userService.insertSelective(user);
            // 从数据库中查出注册时没有的字段，封装到新的对象中
            user = userService.selectByPrimaryKey(user.getUserName());
            String sessionInfo = ConfigUtil.getSessionInfoName();
            session.setAttribute("typeUser", "0");
            session.setAttribute(sessionInfo, user);
            return "user/registsuc";
            /*
             * }else{ puserService.insertRegister(user); String
             * username=user.getUserName(); Puser
             * use=puserService.selectByNameFromPuser(username); String
             * sessionInfo=ConfigUtil.getSessionInfoName();
             * session.setAttribute("typeUser","1");
             * session.setAttribute(sessionInfo, use); return "user/registsuc";
             * }
             */

        } catch (Exception e) {
            logger.info("注册的时候，" + e.getMessage());
        }
        return "user/registsuc";
    }

    /**
     * ajax异步登录的处理controller,登录成功后跳入TacklUsersController进行页面跳转判断
     * 
     * @author liujingui
     * @param user
     *            封装了用户信息的对象
     * @param request
     * @return Map<String,Object>
     * @date 2015年10月21日 下午1:58:01
     */
    @RequestMapping(value = "/loginJudge", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> loginJudge(Cuser user, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("flag", Constant.USER_NAME_ERROR);

        // 检查用户注册信息完整性及危险字符
        if (!checkUserInfo(user))
            return result;

        String ip = StringUtils.getString(IPUtil.getIpAddr(request));
        Date loginTime = new Date();
        Cuser c_user = userService.selectByPrimaryKey(user.getUserName());
        if (null == c_user) {
            // Puser p_user=puserService.selectByNameAndPass(user);
            // if(null != p_user){
            // p_user.setLastLogIp(ip);
            // p_user.setLastLogTime(loginTime);
            // puserService.updateById(p_user);
            // session.setAttribute("typeUser","1");
            // session.setAttribute(sessionInfo, p_user);
            // result.put("flag", Constant.SUCCESS);
            // }
        } else if (user.getUserPass().equals(c_user.getUserPass())) {
            HttpSession session = request.getSession();
            c_user.setLastLogIp(ip);
            c_user.setLastLogTime(loginTime);
            // userService.updateLogById(c_user);
            session.setAttribute("typeUser", "0");// 方便以后从session中取值判断是Cuser或Puser
            session.setAttribute(sessionInfo, c_user);
            result.put("flag", Constant.SUCCESS);
        }
        return result;
    }

    /**
     * 注册时,ajax异步方式验证用户名是否存在
     * 
     * @author liujingui
     * @param userName
     *            用户输入的用户名
     * @return Map<String,Object>
     * @date 2015年10月21日 下午2:04:26
     */
    @RequestMapping(value = "/checkUserName", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> checkUserName(String userName) {

        Map<String, Object> res = new HashMap<String, Object>();
        String f_userName = StringUtils.filterDangerString(userName);

        // 过滤后不一样,表明含有非法字符,退出
        if ("".equals(f_userName) || !f_userName.equals(userName)) {
            res.put("flag", Constant.USER_NAME_ERROR);
            return res;
        }
        Cuser use = userService.selectByPrimaryKey(userName);
        // Puser users = puserService.selectByNameFromPuser(f_userName);
        if (null == use /* && null == users */) {
            // 注册的时候，检查用户名。查询为空，返回数码，表示用户名可以注册
            res.put("flag", Constant.USER_NAME_SUCCESS);
        } else {
            // 注册的时候，检查用户名。查询不为空，返回数码，表示用户名已经注册
            res.put("flag", Constant.USER_NAME_EXIST);
        }
        return res;
    }

    /**
     * 注册时,ajax异步方式验证图片验证码是否正确
     * 
     * @author liujingui
     * @param code
     *            用户输入的图片验证码
     * @param session
     * @return Map<String,Object>
     * @date 2015年10月21日 下午2:05:37
     */
    @RequestMapping(value = "/checkCode", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> checkCodeByAjax(String code, HttpSession session) {
        Map<String, Object> res = new HashMap<String, Object>();
        if (!checkcode(code, session)) {
            res.put("flag", Constant.IMAGE_CODE_ERROR);
        } else {
            res.put("flag", Constant.SUCCESS);
        }
        return res;
    }

    /**
     * 检查图片输入的图片验证码正确与否的方法
     * 
     * @author liujingui
     * @param code
     *            输入的图片验证码
     * @param session
     * @return boolean true:正确 false:不正确
     * @date 2015年10月21日 下午1:53:44
     */
    public static boolean checkcode(String code, HttpSession session) {

        String imageCode = (String) session.getAttribute("imageCode");
        if ("".equals(StringUtils.getString(imageCode))) {
            return false;
        }
        code = StringUtils.getString(code);
        return code.equals(imageCode);

    }

    /**
     * 
     * @author 张衡
     * @Description 检查邮箱是否注册过
     * @param mail
     *            String
     * @return Map<String,Object>
     * @date 2015年8月20日 下午1:50:39
     */
    @RequestMapping(value = "/checkMail", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> checkMail(String mail) {

        Map<String, Object> res = new HashMap<String, Object>();
        // 邮箱信息完整性验证,及邮箱中危险字符过滤
        mail = StringUtils.getString(mail);
        String f_mail = StringUtils.filterDangerString(mail);
        if ("".equals(mail) || !f_mail.equals(mail)) {
            res.put("flag", Constant.REGIST_MAIL_ERROR);
            return res;
        }

        int userNum = userService.selectByMail(mail);
        int puserMailNum = puserService.selectByMailFromPuser(mail);
        if (userNum > 0 || puserMailNum > 0) {
            res.put("flag", Constant.REGIST_MAIL_ERROR);
        } else {
            res.put("flag", Constant.REGIST_MAIL_SUCCESS);
        }
        return res;
    }

    /**
     * ajax验证用户输入的邀请码是否正确
     * 
     * @author liujingui
     * @param inviteCode
     *            用户输入的邀请码
     * @return Map<String,Integer>
     * @date 2015年10月21日 下午1:30:11
     */
    @RequestMapping(value = "/checkInviteCode", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Integer> checkInviteCode(String inviteCode) {
        Map<String, Integer> res = new HashMap<String, Integer>();
        res.put("flag", Constant.USER_INVITECODE_ERROR);
        inviteCode=StringUtils.getString(inviteCode).toUpperCase();
        Long userId = StringUtils.deInviteCode(inviteCode);
        // 输入的邀请码对应邀请id必须正确并存在
        if (0 != userId && null != userService.selectById(userId)) {
            res.put("flag", Constant.SUCCESS);
        }
        return res;
    }

    /**
     * 
     * @author liuJinGui
     * @Description ajax严格验证身份证是否有效
     * @param idCard
     *            String
     * @return Map<String,Object>
     * @date 2015年8月20日 下午1:50:39
     */
    @RequestMapping(value = "/checkIdCard", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> checkIdCard(String idCard, HttpSession session) {
        Map<String, Object> res = new HashMap<String, Object>();
        idCard = StringUtils.getString(idCard);
        if (!new IDCard().verify(idCard)) {
            res.put("flag", Constant.USER_IDCARD_ERROR);
        } else {
            res.put("flag", Constant.SUCCESS);
        }
        return res;
    }

    /**
     * 
     * @author liuJinGui
     * @Description 检查从前台接受的用户信息,以及安全性验证
     * @param user
     *            前台接受的封装的用户信息
     * @return boolean
     * @date 2015年10月10日 下午2:50:39
     */
    public static boolean checkUserInfo(Cuser user) {

        if (null == user) {
            return false;
        }

        String userName = StringUtils.getString(user.getUserName());
        String userPwd = StringUtils.getString(user.getUserPass());
        // 信息完整性验证
        if ("".equals(userName) || "".equals(userPwd)) {
            return false;
        }

        // 过滤用户名和密码中的危险字符
        String f_userName = StringUtils.filterDangerString(userName);
        String f_userPwd = StringUtils.filterDangerString(userPwd);
        if (!f_userName.equals(userName) || !f_userPwd.equals(userPwd)) {
            return false;
        }

        return true;
    }
}
