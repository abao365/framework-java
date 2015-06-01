package cn.abao365.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import cn.abao365.common.constant.CommonConstants;

/**
 * 字符串工具类
 * @author ChengWenFeng
 * @date 2014年8月20日 下午2:12:14
 *
 */
public final class StringUtil {
	
	private StringUtil(){}
	
	
	
	/** **/
	private static final String regEx = "[\\u4e00-\\u9fa5]";

	/** **/
	private static Pattern p = Pattern.compile(regEx);
	
	/**
	 * 判断文字中是否包含汉字
	 * 
	 * @param str 
	 * @return
	 */
	public static boolean checkword(String str) {
		int count = 0;
		Matcher m = p.matcher(str);
		while (m.find()) {
			for (int i = 0; i <= m.groupCount(); i++) {
				count = count + 1;
			}
		}
		if (count == 0) { // 无汉字
			return true;
		}
		return false; // 有汉字
	}
	
	
	/**
	 * 根据 ec 参数编码
	 * 
	 * @param q 
	 * @param ec 
	 * @return
	 */
	public static String filterQ(String v, String ec) {
		if (StringUtils.isNotEmpty(v)) {
			if (ec == null) {
				ec = CommonConstants.ECNAME_UTF;
			} else if (!CommonConstants.GB18030_UTF.equalsIgnoreCase(ec) && !CommonConstants.ECNAME_GB2312.equalsIgnoreCase(ec)
			        && !CommonConstants.ECNAME_GBK.equalsIgnoreCase(ec) && !CommonConstants.ECNAME_8859.equalsIgnoreCase(ec)
			        && !CommonConstants.ECNAME_UTF.equalsIgnoreCase(ec)) {
				ec = CommonConstants.ECNAME_UTF;
			}
			try {
				v = v.trim();
				v = new String(v.getBytes(CommonConstants.ECNAME_8859), ec);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return v;
	}
	
	/**
	 * URL转码
	 * 
	 * @param s
	 * @return
	 */
	public static String encode(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8");
		} catch (Exception e) {
			return s;
		}
	}
	
	/**
	 * MD5对字符串加密
	 * 
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());

			byte[] b = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < b.length; i++) {
				int v = (int) b[i];
				v = v < 0 ? 0x100 + v : v;
				String cc = Integer.toHexString(v);
				if (cc.length() == 1)
					sb.append('0');
				sb.append(cc);
			}

			return sb.toString();
		} catch (Exception e) {
		}

		return "";
	}
	
	/**
	 * 按字节长度截取字符串
	 * 
	 * @param str 将要截取的字符串参数
	 * @param toCount 截取的字节长度
	 * @param more 字符串末尾补上的字符串
	 * @return 返回截取后的字符串
	 */
	public static String substring(String str, int toCount, String more) {
		int reInt = 0;
		String reStr = "";
		if (str == null) {
			return "";
		}
		char[] tempChar = str.toCharArray();
		for (int kk = 0; (kk < tempChar.length && toCount > reInt); kk++) {
			String s1 = String.valueOf(tempChar[kk]);
			byte[] b = s1.getBytes();
			reInt += b.length;
			reStr += tempChar[kk];
		}
		if (toCount == reInt || (toCount == reInt - 1)) {
			reStr += more;
		}
		return reStr;
	}
	
	
	public static long getPID() {
		return System.nanoTime() + new Random().nextInt(9999);
	}
	
	/**
	 * 生成UUID
	 * @author ChengWenFeng
	 * @return 
	 * @date 2014年8月20日 下午2:12:40
	 */
	public static String getUUID(){
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
	
	/**
	 * 生成UUID，但会过滤"-"
	 * @author ChengWenFeng
	 * @return 
	 * @date 2014年8月20日 下午2:12:48
	 */
	public static String getUUID2(){
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-", "");
	}
	
	/**
	 * 列表转换成指定格式字符串
	 * @author ChengWenFeng
	 * @param list
	 * @param delimiter
	 * @return 
	 * @date 2014年11月3日 下午3:57:46
	 */
	public static String listToStr(List<?> list,String delimiter){
		if(list == null || list.size() == 0){
			return "";
		}
		StringBuilder sbtmp = new StringBuilder();
		int i = 0;
		for(Object t:list){
			if(StringUtils.isNotEmpty(t.toString())){
				if(i != 0){
					sbtmp.append(delimiter);
				}
				sbtmp.append(t);
				i++;
			}
		}
		return sbtmp.toString();
	}
	
	public static String arrayToStr(String[] arr,String delimiter){
		if(arr == null){
			return "";
		}
		StringBuilder sbtmp = new StringBuilder();
		int i = 0;
		for(String t:arr){
			if(StringUtils.isNotEmpty(t)){
				if(i != 0){
					sbtmp.append(delimiter);
				}
				sbtmp.append(t);
				i++;
			}
		}
		return sbtmp.toString();
	}
	
	
	
	
	public static boolean filterCallback(String callback) {
		if(StringUtils.isEmpty(callback)){
			return false;
		}
		boolean flag = true;
		//过滤特殊字符
		flag = callback.matches("^[A-Za-z0-9]+$");
		if(flag && callback.toLowerCase().equals("alert")){
			flag = false;
		}
		return flag;
	}
	
	
	public static void main(String[] args) {
		String[] strs = {"abc","123"};
		System.out.println(arrayToStr(strs, ","));
	}
}
