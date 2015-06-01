package cn.abao365.common.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import cn.abao365.common.constant.CommonConstants;

/**
 * Controller层工具类
 * @author ChengWenFeng
 * @date 2014年8月20日 下午2:10:05
 *
 */
public final class WEBUtil {

	private WEBUtil() {
	}
	
	
	/**
	 * 获取访问者IP
	 * @author ChengWenFeng
	 * @param request
	 * @return 
	 * @date 2014年8月20日 下午2:10:16
	 */
	public static String getIP(HttpServletRequest request){
		String ip = request.getHeader(CommonConstants.X_FORWARDED_FOR);
		if ((ip == null) || (ip.length() == 0) || (CommonConstants.UNKNOWN.equalsIgnoreCase(ip))) {
			ip = request.getHeader(CommonConstants.X_FORWARDED_FOR1);
		}
		if ((ip == null) || (ip.length() == 0) || CommonConstants.UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader(CommonConstants.PROXY_CLIENT_IP);
		}
		if ((ip == null) || (ip.length() == 0) || CommonConstants.UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader(CommonConstants.WL_PROXY_CLIENT_IP);
		}
		if ((ip == null) || (ip.length() == 0) || CommonConstants.UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip != null) {
			int index = ip.indexOf(",");
			if (index > 0) { // 有多个IP，取第一个
				ip = ip.substring(0, index);
			}
			ip = ip.trim();
		} else {
			ip = CommonConstants.BLANK;
		}
		return ip;
	}

	/**
	 * 获取请求传递的所有参数
	 * @author ChengWenFeng
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @date 2014年8月20日 下午2:10:33
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject getRequestParameters(HttpServletRequest request)
			throws UnsupportedEncodingException {
		JSONObject resultJson = new JSONObject();
		Map<String, String[]> map = request.getParameterMap();
		if (map == null || map.size() == 0) {
			return null;
		}
		for (String key : map.keySet()) {
			String[] obj = map.get(key);
			if (obj != null && obj.length > 0) {
				if (obj.length == 1) {
					resultJson.put(key, StringEcode(obj[0]));
				} else {
					resultJson.put(key, getCodeListUTF(obj));
				}
			}
		}
		return resultJson;
	}

	/**
	 * 参数转换
	 * @author ChengWenFeng
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @date 2014年8月20日 下午2:11:53
	 */
	private static List<String> getCodeListUTF(String[] request)
			throws UnsupportedEncodingException {
		List<String> resultList = new ArrayList<String>();
		for (String v : request) {
			resultList.add(StringEcode(v));
		}
		return resultList;
	}

	/**
	 * 值转码
	 * @author ChengWenFeng
	 * @param request 
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @date 2014年8月20日 下午2:11:59
	 */
	private static String StringEcode(String request)
			throws UnsupportedEncodingException {
		return new String(request.getBytes("ISO8859-1"), "UTF-8");
	}
}
