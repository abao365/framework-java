package cn.abao365.common.trs.util;

import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

import cn.abao365.common.conf.ConfigFactory;




/**
 * 图片完整URL生成
 * @author chengwenfeng
 * @date 2014年9月9日 上午9:48:06
 *
 */
public final class ImageUtil {
	
	private ImageUtil() {}
	
	
	/**
	 * 图片域名地址
	 */
	public static final List<?> IMG_PREFIX_LIST_01 = ConfigFactory.getList("sui.urls.imgs.img.u");
	public static final List<?> IMG_PREFIX_LIST_02 = ConfigFactory.getList("sui.urls.imgs.map.u");
	
	
	/**
	 * 随即获取 图片url
	 * @author ChengWenFeng
	 * @param type
	 * @return 
	 * @date 2014年9月22日 上午11:13:46
	 */
	public static String getRandomUrl(int type) {
		Random rd = new Random();		
		String url = "";
		if(type == 1){
			int size = IMG_PREFIX_LIST_01.size();
			int i = rd.nextInt(size - 1);
			url = (String) IMG_PREFIX_LIST_01.get(i);
		}else if(type == 2){
			int size = IMG_PREFIX_LIST_02.size();
			int i = rd.nextInt(size - 1);
			url = (String) IMG_PREFIX_LIST_02.get(i);
		}
		return url;
	}
	
	
	/**
	 * 
	 * @author ChengWenFeng
	 * @param key
	 * @param type
	 * @param suffix
	 * @return 
	 * @date 2014年9月22日 上午11:08:45
	 */
	public static String getImg(String key,int type,String suffix){
		if(StringUtils.isEmpty(suffix)){
			suffix = "";
		}
		return getRandomUrl(type)+key+suffix;
	}

}
