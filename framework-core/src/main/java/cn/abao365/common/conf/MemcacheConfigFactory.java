/**
 *  Copyright (c)  2011-2020 Panguso, Inc.
 *  All rights reserved.
 *
 *  This software is the confidential and proprietary information of Panguso, 
 *  Inc. ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into with Panguso.
 */
package cn.abao365.common.conf;



/**
 * Memcache缓存工厂
 * 
 * @author tongys
 * @date 2013-11-8
 */
public final class MemcacheConfigFactory {

	private MemcacheConfigFactory() {
		/* empty */
	}
	
	/**
	 * 初始化缓存工厂
	 */
	public static void initCacheConf(String name) {
		System.setProperty("common.cache.memcache."+name+".nodelist",ConfigFactory.getString("common.cache.memcache."+name+".nodelist").replaceAll("," ," "));
		System.setProperty("common.cache.memcache."+name+".poolsize",ConfigFactory.getString("common.cache.memcache."+name+".poolsize"));
	}
}
