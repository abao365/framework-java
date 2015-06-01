package cn.abao365.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class InetUtils {

	private InetUtils(){}
	
	/**
	 * 获取本机ip地址
	 * @author chengwenfeng
	 * @return 
	 * @return String
	 * @date 2015年5月13日 上午9:32:12
	 */
	public static String getLocalHost(){
		try {
			InetAddress addr = InetAddress.getLocalHost();
			return addr.getHostAddress().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "";
	}
}
