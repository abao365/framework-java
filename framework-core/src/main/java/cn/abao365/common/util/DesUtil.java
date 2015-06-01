package cn.abao365.common.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.alibaba.fastjson.JSONObject;
import cn.abao365.common.constant.CommonConstants;


/**
 * DES加密解密工具类
 * 
 * @author liufengyu
 * @date 2011-10-21
 */
public final class DesUtil {
	private static final String DEFAULT_PASSWORD_CRYPT_KEY = "__jDlog_";

	private static final String DES = "DES";
	private static Cipher cipher = null;

	static {
		// Cipher对象实际完成加密操作
		try {
			cipher = Cipher.getInstance(DES);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	private DesUtil() {

	}

	/**
	 * 加密
	 * 
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return 返回加密后的数据
	 * @throws Exception 异常
	 */
	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

		// 正式执行加密操作
		return cipher.doFinal(src);
	}

	/**
	 * 解密
	 * 
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return 返回解密后的原始数据
	 * @throws Exception 异常
	 */
	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {

		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

		// 正式执行解密操作
		return cipher.doFinal(src);
	}

	/**
	 * 密码解密
	 * 
	 * @param data 加密字符串
	 * @return
	 * @throws Exception 
	 */
	public static String decrypt(String data) throws Exception {
		try {
			return new String(decrypt(hex2byte(data.getBytes()),
			        DEFAULT_PASSWORD_CRYPT_KEY.getBytes()));
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 密码解密
	 * 
	 * @param data 加密字符串
	 * @param key 密钥，长度必须是8的倍数
	 * @return
	 * @throws Exception 
	 */
	public static String decrypt(String data, String key) throws Exception {
		try {
			return new String(decrypt(hex2byte(data.getBytes()), key.getBytes()));
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 密码加密
	 * 
	 * @param data 要加密字符串
	 * @return
	 * @throws Exception 
	 */
	public static String encrypt(String data) throws Exception {
		try {
			return byte2hex(encrypt(data.getBytes(), DEFAULT_PASSWORD_CRYPT_KEY.getBytes()));
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 密码加密
	 * 
	 * @param data 要加密字符串
	 * @param key 密钥，长度必须是8的倍数
	 * @return
	 * @throws Exception 
	 */
	public static String encrypt(String data, String key) throws Exception {
		try {
			return byte2hex(encrypt(data.getBytes(), key.getBytes()));
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 二行制转字符串
	 * 
	 * @param b 二进制
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";

		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
	            hs = hs + "0" + stmp;
            } else {
	            hs = hs + stmp;
            }
		}

		return hs.toUpperCase();
	}

	/**
	 * 
	 * @param b 二进制
	 * @return
	 * @author liufengyu
	 * @date 2013-1-25
	 */
	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0) {
	        throw new IllegalArgumentException("长度不是偶数");
        }

		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}

		return b2;
	}

	/**
	 * 测试方法
	 * @param args 参数
	 * @author liufengyu
	 * @throws Exception 
	 * @date 2013-1-25
	 */
	public static void main(String[] args) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("url", "http://news.xinhuanet.com/ziliao/2004-12/02/content_2286378.htm");
		jsonObject.put("data", "2015-02-09");
		jsonObject.put("anuthor", "程文峰");
		String dataString = DesUtil.encrypt(jsonObject.toJSONString(),CommonConstants.COMMON_URL_KEY);
		
		System.out.println(dataString);
		dataString = "6300594088CF74136E0ACDC0B00D15D99B6005C78689E1D9566F63B2A10BA93A951C977FE6504FEC172C0413C7FDD60EFF3B25500340909A635FA2C7C80C2FF80F84C9760A7AA085";
		System.out.println(DesUtil.decrypt(dataString, CommonConstants.COMMON_URL_KEY));

	}
}

