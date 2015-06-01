package cn.abao365.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

/**
 * HTTP请求工具类
 * @author ChengWenFeng
 * @date 2014年8月20日 下午2:14:26
 *
 */
public final class HTTPUtils {
	
	private HTTPUtils() {

	}
	private static final String DEFAULT_ENCODING = "UTF-8";
	
	
	/**
	 * 读取本地图片
	 * @author chengwenfeng
	 * @param path
	 * @return
	 * @throws Exception 
	 * @return ByteArrayOutputStream
	 * @date 2015年5月13日 上午10:09:37
	 */
	public static ByteArrayOutputStream readLocalImage(String path) throws Exception{
		File file = new File(path);
		InputStream in = new FileInputStream(file);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		IOUtils.copy(in, out);
		return out;
	}
	
	
	/**
	 * 下载网络图片流
	 * @author chengwenfeng
	 * @param url
	 * @return
	 * @throws Exception 
	 * @return ByteArrayOutputStream
	 * @date 2015年5月13日 上午9:50:29
	 */
	public static ByteArrayOutputStream getURL(String url) throws Exception{
		BufferedInputStream reader = new BufferedInputStream(new URL(url).openStream());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		IOUtils.copy(reader, out);
		return out;
	}

	
	/**
	 * 以byte形式返回对应的文件数据
	 * @param url url
	 * @return
	 * @throws IOException IO异常
	 * @author chengWenFeng
	 * @date 2014-2-15
	 */
	public static byte[] getRspAsByteByGetMethod(String url) throws IOException {
		return getRspAsByteByGetMethod(url,null,0);
	}
	
	/**
	 * 代理方式GET HTTP请求
	 * @author ChengWenFeng
	 * @param url 目标URL
	 * @param ip 代理IP
	 * @param port 代理端口
	 * @return
	 * @throws IOException 
	 * @date 2014年8月20日 下午2:18:26
	 */
	public static byte[] getRspAsByteByGetMethod(String url,String ip,int port) throws IOException {
		String proxyIp=ip;
		int proxyPort=port;
		CloseableHttpClient httpClient = null;
		if(StringUtils.isEmpty(ip)){
			httpClient = HttpClientBuilder.create().build();
		}else{
			HttpHost proxy = new HttpHost(proxyIp, proxyPort); 
			httpClient = HttpClientBuilder.create().setProxy(proxy).build();
		}
		HttpGet get = new HttpGet(url);
		try {
			HttpResponse response = httpClient.execute(get);
			return IOUtils.toByteArray(response.getEntity().getContent());
		} finally {
			httpClient.close();
		}
	}
	
	
	/**
	 * GET请求
	 * @author ChengWenFeng
	 * @param url
	 * @return
	 * @throws IOException 
	 * @date 2014年8月20日 下午2:15:19
	 */
	public static String getResponseAsStringByGetMethod(String url) throws IOException {
		return getResponseAsStringByGetMethod(url, DEFAULT_ENCODING);
	}

	
	/**
	 * GET方式请求--以字符串的形式返回
	 * @author ChengWenFeng
	 * @param url
	 * @param encoder 字符编码
	 * @return
	 * @throws IOException 
	 * @date 2014年8月20日 下午2:15:47
	 */
	public static String getResponseAsStringByGetMethod(String url,String encoder)
	        throws IOException {
		byte[] responseBody = getRspAsByteByGetMethod(url);
		// 处理内容
		if (StringUtils.isEmpty(encoder)) {
			encoder = "utf-8";
		}
		return new String(responseBody, encoder);
	}
	
	
	/**
	 * POST方式请求--以字符串的形式返回
	 * @author ChengWenFeng
	 * @param url 目标url
	 * @param para 参数
	 * @return
	 * @throws IOException 
	 * @date 2014年8月20日 下午2:16:24
	 */
	public static String getResponseAsStringByPostMethod(String url, Map<String, String> para)
	        throws IOException {
		return getResponseAsStringByPostMethod(url,para,null,0);
	}
	
	
	/**
	 * 直接写BodyPOST查询
	 * @author chengwenfeng
	 * @param url
	 * @param body
	 * @param proxyIp
	 * @param proxyPort
	 * @return
	 * @throws Exception 
	 * @return byte[]
	 * @date 2015年5月27日 下午4:55:00
	 */
	public static byte[] getRspAsByteByPostMethod(String url,String body,String proxyIp,int proxyPort) throws Exception{
		CloseableHttpClient httpClient = null;
		if(StringUtils.isEmpty(proxyIp)){
			httpClient = HttpClientBuilder.create().build();
		}else{
			HttpHost proxy = new HttpHost(proxyIp, proxyPort); 
			httpClient = HttpClientBuilder.create().setProxy(proxy).build();
		}
		HttpPost httppost = new HttpPost(url);
		try {
			httppost.setEntity(new StringEntity(body,DEFAULT_ENCODING));
			HttpResponse response = httpClient.execute(httppost);
			return IOUtils.toByteArray(response.getEntity().getContent());
		} finally {
			httpClient.close();
		}
	}
	
	/**
	 * POST直接写Body
	 * @author chengwenfeng
	 * @param url
	 * @param body
	 * @param proxyIp
	 * @param proxyPort
	 * @return 
	 * @return String
	 * @throws Exception 
	 * @date 2015年5月27日 下午4:57:17
	 */
	public static String getResponseAsStringByPostMethod(String url, String body,String proxyIp,int proxyPort) throws Exception{
		byte[] result = getRspAsByteByPostMethod(url,body,proxyIp,proxyPort);
		if(result == null || result.length < 1){
			return null;
		}
		return new String(result,"UTF-8");
		//new String(IOUtils.toByteArray(response.getEntity().getContent()),"UTF-8")
	}
	
	/**
	 * POST直接写Body
	 * @author chengwenfeng
	 * @param url
	 * @param body
	 * @return
	 * @throws Exception 
	 * @return String
	 * @date 2015年5月27日 下午4:59:31
	 */
	public static String getResponseAsStringByPostMethod(String url, String body) throws Exception{
		byte[] result = getRspAsByteByPostMethod(url,body,null,0);
		if(result == null || result.length < 1){
			return null;
		}
		return new String(result,"UTF-8");
		//new String(IOUtils.toByteArray(response.getEntity().getContent()),"UTF-8")
	}
	
	/**
	 * 代理方式访问<br/>
	 * POST方式请求--以字符串的形式返回
	 * @author ChengWenFeng
	 * @param url 目标URL
	 * @param para 参数
	 * @param ip 代理IP
	 * @param port 代理端口
	 * @return
	 * @throws IOException 
	 * @date 2014年8月20日 下午2:47:05
	 */
	public static String getResponseAsStringByPostMethod(String url, Map<String, String> para,String proxyIp,int proxyPort)
	        throws IOException {
		CloseableHttpClient httpClient = null;
		if(StringUtils.isEmpty(proxyIp)){
			httpClient = HttpClientBuilder.create().build();
		}else{
			HttpHost proxy = new HttpHost(proxyIp, proxyPort); 
			httpClient = HttpClientBuilder.create().setProxy(proxy).build();
		}
		HttpPost httppost = new HttpPost(url);
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		for (String key : para.keySet()) {
			formparams.add(new BasicNameValuePair(key, para.get(key)));
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, DEFAULT_ENCODING);
		httppost.setEntity(entity);
		try {
			HttpResponse response = httpClient.execute(httppost);
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity()
			        .getContent(), DEFAULT_ENCODING));
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} finally {
			httpClient.close();
		}
	}


}
