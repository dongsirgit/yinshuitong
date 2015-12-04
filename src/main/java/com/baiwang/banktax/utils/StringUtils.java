package com.baiwang.banktax.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import com.baiwang.banktax.utils.message.SendMsgUtils;

/**
 * 常用字符串工具类
 * 
 * @author YinHua
 * 
 */
public class StringUtils {

    private static final char[] chars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
            'V', 'W', 'X', 'Y', 'Z' };

    private static final char[] hexStrings = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f' };
    private static final String CodeCharStr = "ZABCDEFGHIJKLMNP";

    private static final String[][] h2t = { 
            { "<", "&lt;" }, 
            { ">", "&gt;" }, 
            { "'", "" }, 
            { "\"", "" }, 
            { "%", "" },
            // { ";", "" }
            // { " ", "&nbsp;" },
    };
    private static final char EXTENSION_SEPARATOR = '.';
    /**
     * 匹配中国手机号码
     */
    private static String PATTERN_CHINAMOBILE = "^(13[0-9]|145|147|15[0-3,5-9]|18[0,2,5-9])(\\d{8})$";
    /**
     * 匹配邮箱
     */
    private static String PATTERN_EMAIL = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
    /**
     * 匹配数字
     */
    private static String PATTERN_NUMBER = "^[0-9]*$";
    /**
     * 匹配短链接
     */
    private static String PATTERN_SHORTURL = "^[0-9]*-[A-Za-z0-9]+$";

    /**
     * 常用字符串
     */
    private static String EMPTYstr = "";

    public static String toHtml(String s) {
        s = Replace(s, "&", "&amp;");
        s = Replace(s, "<", "&lt;");
        s = Replace(s, ">", "&gt;");
        s = Replace(s, "\t", "    ");
        s = Replace(s, "\r\n", "\n");
        s = Replace(s, "\n", "<br>");
        s = Replace(s, "  ", " &nbsp;");
        s = Replace(s, "'", "&#39;");
        s = Replace(s, "\\", "&#92;");
        return s;
    }

    public static String formatOperatTime(String date) {
        if (isNotBlank(date) && date.length() == 6)
            date = date.substring(0, 4) + "年" + date.substring(4, date.length()) + "月";
        return date;
    }

    public static String queryAllOperateByList(List<Map<String, Object>> list, String key, String content) {
        String operate = "";
        try {
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Map<String, Object> map = list.get(i);
                    if (map != null && (!map.isEmpty())) {
                        String nid = (String) map.get("operate_key");
                        if (StringUtils.isBlank(content)) {
                            content = "operate_value";
                        }
                        String value = (String) map.get(content);
                        if (key.equalsIgnoreCase(nid)) {
                            operate = value;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return operate;

    }

    /**
     * 检查传入的字符串是否为null或空字符串
      * @author liujingui
      * @param str  
      * @return boolean: <br>true:是null或空字符串
      * @date 2015年10月31日 下午10:59:12
     */
    public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0);
    }

    /**
     * 检查传入的字符串是否为null或空字符串
      * @author liujingui
      * @param str  
      * @return boolean: <br>true:不是null或空字符串
      * @date 2015年10月31日 下午10:59:12
     */
    public static boolean isNotBlank(String str) {
        return (str != null && str.trim().length() > 0);
    }

    /**
     * 检查所有的传入的字符串是否为null或空字符串
      * @author liujingui
      * @param 所有的字符串
      * @return boolean: <br />true->传入的字符串有null或空字符串
      * @date 2015年10月31日 下午10:53:13
     */
    public static boolean hasBlank(String... str){
        for(int i=0;i<str.length;i++){
            if(str[i] == null || str[i].trim().length() == 0)
                return true;
        }
        return false;
    }
    
    /**
     * 根据KEY获取map数据中的值
     */
    @SuppressWarnings("rawtypes")
    public static String getValueForMapByKey(Map map, String key, String defaultStr) {
        if (map != null && (!map.isEmpty())) {
            return map.get(key) == null ? defaultStr : map.get(key).toString();
        }
        return defaultStr;
    }

    /**
     * 获取格式化之后的 手机号 邮箱或者 用户名
     * 
     * @param account
     * @param begin
     * @param end
     * @return
     */
    public static String getAccount(String account, int begin, int end) {
        if (account != null && account.length() > 0 && begin > 0 && end > 0 && account.length() > (begin + end)) {
            account = account.substring(0, begin) + "***" + account.substring(account.length() - end, account.length());
        }
        return account;
    }

    /**
     * 判断字符串是否是为""或者null
     * 
     * @param strtemp
     *            传入的字符串
     * @return 为""或者null是，返回"";相反返回字符串本身(去掉前后空格)
     * 
     */
    public static String getString(String strtemp) {
        if (strtemp != null && !"".equals(strtemp)) {
            return strtemp.trim();
        } else {
            return "";
        }
    }

    /**
     * 字符串是否匹配正则表达式
     * 
     * @param matchString
     *            被匹配的字符串
     * @param regexString
     *            正则表达式
     * @return true：被匹配的字符串符合正则 ；false：被匹配的字符串不符合正则
     */
    public static boolean isMatchRegex(String matchString, String regexString) {
        boolean flag = false;
        if (isNotBlank(matchString)) {
            Pattern pattern = Pattern.compile(regexString);
            Matcher matcher = pattern.matcher(matchString);
            flag = matcher.matches();
            pattern = null;
            matcher = null;
        }
        matchString = null;
        return flag;
    }

    /**
     * 检查指定的字符串是否为NULL或者空值 注意: 如果字符串是个空格也会返回 <code>true</code>
     * 
     * <pre>
     * StringUtils.hasLength(null) = false
     * StringUtils.hasLength("") = false
     * StringUtils.hasLength(" ") = true
     * StringUtils.hasLength("Hello") = true
     * </pre>
     * 
     * @param str
     *            要检查的字符串 (may be <code>null</code>)
     * @return 如果字符串不是空，并且有长度就返回<code>true</code>
     * @see #hasText(String)
     */
    public static boolean hasLength(String str) {
        return (str != null && str.length() > 0);
    }

    /**
     * 判断字符串是否是邮箱地址
     * 
     * @param mailStr
     *            需要判断的字符
     * @return true：字符串是标准的邮箱地址；false：非邮箱地址
     */
    public static boolean isEmail(String mailStr) {
        return isMatchRegex(mailStr, PATTERN_EMAIL);
    }

    /**
     * 判断指定字符串是否是手机号。不再区分运营商
     * 
     * @param phoneNumber
     *            要检查的字符串
     * @return 是手机号返回<code>true</code>
     */
    public static boolean isMobile(String phoneNumber) {
        return isMatchRegex(phoneNumber, PATTERN_CHINAMOBILE);
    }

    /**
     * 校验字符串是否是数字
     * 
     * @param numberStr
     *            数字形式的字符串
     * @return true：该字符串是数字；false：不是数字型的字符串
     */
    public static boolean isNumber(String numberStr) {
        return isMatchRegex(numberStr, PATTERN_NUMBER);
    }

    /**
     * 校验字符串是否是整数
     * 
     * @param numberStr
     *            整数形式的字符串
     * @return true：该字符串是数字；false：不是数字型的字符串
     */
    public static boolean isInt(String numberStr) {
        try {
            Long.parseLong(numberStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static int s2i(String numberStr) {
        try {
            return Integer.parseInt(numberStr);
        } catch (Exception e) {
            return 0;
        }
    }

    public static long s2l(String numberStr) {
        try {
            return Long.parseLong(numberStr);
        } catch (Exception e) {
            return 0;
        }
    }

    public static byte s2byte(String numberStr) {
        try {
            return Byte.parseByte(numberStr);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 检查指定的字符串是否包含空格
     * 
     * @param str
     *            要检查的字符串 (may be null)
     * @return 如果string不是空并且包含至少一个空格，返回<code>true</code>
     * @see java.lang.Character#isWhitespace
     */
    public static boolean containsWhitespace(String str) {
        if (!hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 去掉开头和结尾的空格
     * 
     * @param str
     *            要操作的字符串
     * @return 去掉了开头和结尾空格的字符
     * @see java.lang.Character#isWhitespace
     */
    public static String trimWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
            buf.deleteCharAt(0);
        }
        while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
            buf.deleteCharAt(buf.length() - 1);
        }
        return buf.toString();
    }

    /**
     * 只去掉开头的空格
     * 
     * @param str
     *            要操作的字符串
     * @return 去掉开头空格后的字符
     * @see java.lang.Character#isWhitespace
     */
    public static String trimLeadingWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
            buf.deleteCharAt(0);
        }
        return buf.toString();
    }

    /**
     * 去掉开头的某个字符
     * 
     * @param str
     *            要操作的字符
     * @param leadingCharacter
     *            要去掉的开头字符
     * @return 去掉开头字符的字符
     */
    public static String trimLeadingCharacter(String str, char leadingCharacter) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        while (buf.length() > 0 && buf.charAt(0) == leadingCharacter) {
            buf.deleteCharAt(0);
        }
        return buf.toString();
    }

    /**
     * 只去掉结尾的空格
     * 
     * @param str
     *            要操作的字符串
     * @return 去掉了结尾空格的字符
     * @see java.lang.Character#isWhitespace
     */
    public static String trimTrailingWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
            buf.deleteCharAt(buf.length() - 1);
        }
        return buf.toString();
    }

    /**
     * 去掉字符串中所有的空格
     * 
     * @param str
     *            要操作的字符
     * @return 去掉了所有空格的的字符
     * @see java.lang.Character#isWhitespace
     */
    public static String trimAllWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str);
        int index = 0;
        while (buf.length() > index) {
            if (Character.isWhitespace(buf.charAt(index))) {
                buf.deleteCharAt(index);
            } else {
                index++;
            }
        }
        return buf.toString();
    }

    /**
     * 在给予的<code>urlPath</code>参数中提取完整的文件名称<b>(包括扩展名)</b>
     * </p>
     * 
     * <pre>
     * StringUtils.getFullFilenameFromUrlPath("/products/index.html") == "index.html";
     * StringUtils.getFullFilenameFromUrlPath("") == "";
     * StringUtils.getFullFilenameFromUrlPath(null) == null;
     * </pre>
     * 
     * @param urlPath
     *            请求URL路径
     * @return 提取URL路径中的全部文件名称，包括扩展名
     */
    public static String getFullFilenameFromUrlPath(String urlPath) {
        if (null == urlPath) {
            return null;
        }
        int end = urlPath.indexOf(';');
        if (end == -1) {
            end = urlPath.indexOf('?');
            if (end == -1)
                end = urlPath.length();
        }
        int begin = urlPath.lastIndexOf('/', end) + 1;
        return urlPath.substring(begin, end);
    }

    /**
     * 在给予的 urlPath 参数中提取文件名称<b>(不包括扩展名)</b>
     * </p>
     * 
     * <pre>
     * StringUtils.getFilenameFromUrlPath("/products/index.html") == "index";
     * StringUtils.getFilenameFromUrlPath("index.html") == "index";
     * StringUtils.getFilenameFromUrlPath("index") == "index";
     * StringUtils.getFilenameFromUrlPath("") == "";
     * * StringUtils.getFilenameFromUrlPath(null) == null;
     * </pre>
     * 
     * @param urlPath
     *            请求URL路径
     * @return 提取URL路径中的文件名称，不包括扩展名
     */
    public static String getFilenameFromUrlPath(String urlPath) {
        String filename = getFullFilenameFromUrlPath(urlPath);
        if (null == filename) {
            return null;
        }
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex != -1) {
            filename = filename.substring(0, dotIndex);
        }
        return filename;
    }

    /**
     * 在给予的<code>urlPath</code>参数中提取文件扩展名
     * </p>
     * 
     * <pre>
     * StringUtils.getFilenameExtension("index.html") == "html";
     * StringUtils.getFilenameExtension("") == "";
     * StringUtils.getFilenameExtension(null) == null;
     * </pre>
     * 
     * @param path
     *            文件路径 (may be <code>null</code>)
     * @return 文件扩展名，如果是NULL返回 <code>null</code>
     */
    public static String getFilenameExtension(String urlPath) {
        String fileName = getFullFilenameFromUrlPath(urlPath);
        if (fileName == null) {
            return null;
        }
        int sepIndex = urlPath.lastIndexOf(EXTENSION_SEPARATOR);
        return (sepIndex != -1 ? urlPath.substring(sepIndex + 1) : null);
    }

    /**
     * 获取异常轨道信息，将其转化为String
     * 
     * @author hewm
     * @param e
     *            异常
     * @return
     */
    public static String getStackMessage(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }

    /**
     * <p>
     * 将字符串进行截取，如果超出指定超度，后续内容以指定字符结尾
     * </p>
     * <p>
     * <b>字符串截取的时候将区别中文与英文。中文每字2个字符。数字与英文一个字符。填写长度时请注意</b>
     * </p>
     * <p>
     * 感谢魏新锁同学
     * </p>
     * <p>
     * 例子：
     * </p>
     * 
     * <pre>
     * <code>StringUtils.substring("济南新闻", 8, "...") -> "济南新闻"</code>
     * <code>StringUtils.substring("济南新闻", 9, "...") -> "济南新闻"</code>
     * <code>StringUtils.substring("济南新闻", 3, "...") -> "济..."</code>
     * <code>StringUtils.substring("济南新闻24",9 , "...") -> "济南新闻2..."</code>
     * </pre>
     * 
     * @author hewm
     * @param sourceStr
     *            需要截取的字符串
     * @param limitLength
     *            指定截取长度
     * @param more
     *            超出长度的字符替代字符
     * @return
     */
    public static String substring(String sourceText, int limitLength, String more) {
        try {
            if (null == sourceText) {
                return "";
            } else if (sourceText.getBytes("GBK").length <= limitLength) {
                return sourceText;
            } else {
                StringBuilder sb = new StringBuilder();
                int currentLength = 0;
                for (char c : sourceText.toCharArray()) {
                    currentLength += String.valueOf(c).getBytes("GBK").length;
                    if (currentLength <= limitLength) {
                        sb.append(c);
                    } else {
                        break;
                    }
                }
                return sb.append(more).toString();
            }
        } catch (UnsupportedEncodingException e) {

        }
        return "";
    }

    /**
     * 返回搜索字符串
     * 
     * @author hewm
     * @param keyWords
     * @return
     */
    public static String getSearchKeyWord(String keyWords) {
        if (StringUtils.isNotBlank(keyWords))
            return "%" + keyWords.toUpperCase() + "%";
        return "";
    }

    /**
     * 隐藏手机号码
     * 
     * @author hewm
     * @param phoneNumber
     * @return
     */
    public static String hidePhoneNumber(String phoneNumber) {
        if (!isMobile(phoneNumber)) {
            return "";
        }
        String phoneBefore = phoneNumber.substring(0, 3);
        String phoneBehind = phoneNumber.substring(7, 11);
        return phoneBefore + "****" + phoneBehind;
    }

    /**
     * 字符串替换方法，性能比String自带方法高数倍，但不支持正则
     * 
     * @author Yinhua
     * @param src
     *            源字符串
     * @param replaced
     *            将被替换的字符串片段
     * @param replace
     *            替换成的字符串片段
     * @return String
     */
    public static String Replace(String srcStr, String replaced, String replace) {
        if (null == srcStr || null == replaced || null == replace)
            return "";
        StringBuffer stringbuffer = new StringBuffer();
        int i = srcStr.length(), j = replaced.length(), k, l;
        for (k = 0; (l = srcStr.indexOf(replaced, k)) >= 0; k = l + j) {
            stringbuffer.append(srcStr.substring(k, l));
            stringbuffer.append(replace);
        }
        if (k < i)
            stringbuffer.append(srcStr.substring(k));
        return stringbuffer.toString();
    }

    public static String mD5(String str) {
        return hashCode(str, "MD5");
    }

    /**
     * 以SHA-1算法加密字符串,返回十六进制字符串
     * 
     * @author liujingui
     * @param Str
     *            原字符串
     * @param isUpperCase
     *            返回的十六进制字符串字母是否大写
     * @return String 加密后字符串
     * @date 2015年10月17日 下午1:43:21
     */
    public static String hashCode(String Str, boolean isUpperCase) {
        if (isUpperCase) {
            return hashCode(Str, "SHA-1").toUpperCase();
        } else {
            return hashCode(Str, "SHA-1");
        }
    }

    /**
     * 将字符串加密,并转大写16进制字符串返回
     * 
     * @author liujingui
     * @param str
     *            原字符串
     * @param algorithm
     *            加密算法
     * @return String 加密后字符串
     * @date 2015年10月17日 下午4:35:51
     */
    public static String hashCode(String str, String algorithm) {

        if (str == null)
            return EMPTYstr;

        return toHexString(digest(str, algorithm));

    }

    /*
     * MessageDigest.getInstance(String algorithm); Param algorithm, the name of
     * the algorithm requested.(MD5,SHA-1,SHA-256,etc...)
     */
    public static byte[] digest(String str, String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(str.getBytes());
            return digest.digest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * 将小于2^24的数字转为6个大写字母组合的邀请码
     * 
     * @author Yinhua
     * @param intCode
     *            数字邀请码(本系统中为用户ID)
     * @return String
     */
    public static String inviteCode(long intCode) {
        String sb = "";
        for (int i = 5; i >= 0; i--) {
            sb = CodeCharStr.charAt((int) intCode & 0x0000000f) + sb;
            intCode >>= 4;
        }
        return sb;
    }

    /**
     * 将6个大写字母组合的邀请码转换为数字
     * 
     * @author Yinhua
     * @param incodeStr
     *            六位大写字母组成的邀请码
     * @return String
     */
    public static long deInviteCode(String incodeStr) {
        long ret = 0l;
        if (null != incodeStr) {
            int len = incodeStr.trim().length();
            if (len > 6)
                len = 6;
            for (int i = 0; i < len; i++) {
                int x = CodeCharStr.indexOf(incodeStr.charAt(i));
                if (x > -1) {
                    ret <<= 4;
                    ret += x;
                } else {
                    ret = 0l;
                    break;
                }
            }
        }
        return ret;
    }
    
    /**
     * 对原始URL进行加密。获得短链接
     * 
     * @author hewm
     * @param url
     * @return
     */
    public static String shortUrl(String url) {
        String key = "writebylzw";// 可以自定义生成 MD5 加密字符传前的混合 KEY，然后对传入网址进行 MD5 加密
        String sMD5EncryptResult = mD5(key + "_" + url + UUID.randomUUID().toString());
        String hex = sMD5EncryptResult;
        String[] resUrl = new String[4];
        for (int i = 0; i < 4; i++) {
            // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);
            // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 ,
            // 如果不用long ，则会越界
            long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
            StringBuilder outChars = new StringBuilder();
            for (int j = 0; j < 6; j++) {
                long index = 0x0000003D & lHexLong;// 把得到的值与 0x0000003D
                                                   // 进行位与运算，取得字符数组 chars 索引
                outChars.append(chars[(int) index]);// 把取得的字符相加
                lHexLong = lHexLong >> 5; // 每次循环按位右移 5 位
            }
            // 把字符串存入对应索引的输出数组
            resUrl[i] = outChars.toString();
        }
        return resUrl[1];
    }

    /**
     * 判断链接是否符合短链接规范
     * 
     * @author hewm
     * @param url
     * @return
     */
    public static boolean isShortUrl(String url) {
        return isMatchRegex(url, PATTERN_SHORTURL);
    }

    public static String getSsoLoginStr(String userMobile, String key) {
        StringBuilder param = new StringBuilder(userMobile); // 拼接当前手机号
        param.append("|").append(DateUtils.getDate(DateUtils.YMdhms));// 拼接当前时间
        // 对参数进行加密
        String jiami;
        try {
            jiami = URLEncoder.encode(param.toString(), "utf-8").toLowerCase();
            String encryptStr = toHexString(desEncrypt(jiami, key)).toUpperCase();
            return encryptStr;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 加密数据
     *
     * @title encrypt
     * @author hewm
     * @param message
     *            需要加密的数据
     * @param key
     *            加密密钥
     * @return
     * @throws Exception
     */
    public static byte[] desEncrypt(String message, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        return cipher.doFinal(message.getBytes("UTF-8"));
    }

    /**
     * 将一个字节数组转换成十六进制字符串
     * 
     * @author liujingui
     * @param b
     *            字节数组
     * @return String 十六进制字符串
     * @date 2015年10月19日 下午1:53:11
     */
    public static String toHexString(byte b[]) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            hexString.append(byteHEX(b[i]));

        return hexString.toString();
    }

    /**
     * 将一个字节转换成十六进制字符串的数组,效率优于Integer.toHexString(),不需要再判断位数,来决定是否加0
     * 
     * @author liujingui
     * @param ib
     *            一个字节
     * @return char[] 一个字节的十六进制字符串数组
     * @date 2015年10月19日 下午1:51:49
     */
    public static char[] byteHEX(byte ib) {
        char[] ob = new char[2];
        ob[0] = hexStrings[(ib >>> 4) & 0X0F];
        ob[1] = hexStrings[ib & 0X0F];
        return ob;
    }

    /**
     * 获取手机号码的号段信息--即前7位
     * 
     * @param mobile
     *            手机号码
     * @return 手机号码的号段信息
     */
    public static String getMobileRangeStr(String mobile) {
        if (StringUtils.isMobile(mobile)) {
            String mobileRange = mobile.substring(0, 7);
            return mobileRange;
        }
        return "";
    }

    /**
     * 生成CookieKey——是否联通3G号码
     * 
     * @param mobile
     *            用户手机号码
     * @return
     */
    public static String generateKey_IsUniCom3GMobile(String mobile) {
        if (StringUtils.isNotBlank(mobile)) {
            return "upf_" + TEA.Encrypt(mobile);
        }
        return "";
    }

    public static String myPercentToString(int y, int z) {
        String baifenbi = "";// 接受百分比的值
        double baiy = y * 1.0;
        double baiz = z * 1.0;
        double fen = baiy / baiz;
        // NumberFormat nf = NumberFormat.getPercentInstance(); 注释掉的也是一种方法
        // nf.setMinimumFractionDigits( 2 ); 保留到小数点后几位
        DecimalFormat df1 = new DecimalFormat("##.00%"); // ##.00%
                                                         // 百分比格式，后面不足2位的用0补齐
        // baifenbi=nf.format(fen);
        baifenbi = df1.format(fen);
        return baifenbi;
    }

    public static double myPercentToNumber(int y, int z) {
        double baiy = y * 1.0;
        double baiz = z * 1.0;
        double fen = (baiy / baiz) * 100;
        NumberFormat nf = NumberFormat.getIntegerInstance();// 注释掉的也是一种方法
        nf.setMinimumFractionDigits(2); // 保留到小数点后几位
        return Double.parseDouble(nf.format(fen));
    }

    /**
     * 过滤URL中的危险字符,防止跨站点脚本编制，SQL盲注，通过框架钓鱼，链接注入（便于跨站请求伪造）等
     * 
     * @author liujingui
     * @param value
     *            URL字符
     * @return 过滤后字符 String
     * @date 2015年10月10日 下午2:23:59
     */
    public static String filterDangerString(String s) {
        if (s == null)
            return "";
        for (int i = 0; i < h2t.length; i++)
            s = Replace(s, h2t[i][0], h2t[i][1]);
        return s;
    }

    /**
     * 生成5-9位的随机字符串
     * 
     * @author liujingui
     * @return String 5-9位的随机字符串
     * @date 2015年10月15日 上午11:42:12
     */
    public static String getRandomString() {
        Random random = new Random();
        int length = 5 + random.nextInt(5);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(chars.length);
            sb.append(chars[number]);
        }
        return sb.toString();
    }

    /**
     * 对字符串数组各元素根据首字母进行排序,并返回所有元素拼接后字符串
     * 
     * @author liujingui
     * @param strings
     *            任意长度字符串数组
     * @return String
     * @date 2015年10月17日 下午2:01:01
     */
    public static String sortString(String... strings) {

        StringBuffer sb = new StringBuffer();
        // 字符串排序
        Arrays.sort(strings);
        for (int i = 0; i < strings.length; i++) {
            sb.append(strings[i]);
        }
        return sb.toString();
    }
    
    /**
      * @author ldm
      * @Description: 生成订单流水号：YST+时间戳+6位随机数
      * @param @return  
      * @return String  
      * @throws
      * @date 2015年12月4日 下午4:10:27
      */
    public static String generSerialNum(){
    	return "YST"+DateUtils.getDate(DateUtils.YMD)+SendMsgUtils.random();
    }
}
