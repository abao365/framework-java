package cn.abao365.common.init;

import java.io.File;

import cn.abao365.common.conf.ConfigFactory;
import cn.abao365.common.conf.DBConfigFactory;
import cn.abao365.common.conf.MemcacheConfigFactory;
import cn.abao365.common.conf.MongoDbConfigFactory;
import cn.abao365.common.log.LogConfig;

/**
 * 程序初始化准备
 * @author ChengWenFeng
 * @date 2014年8月20日 下午2:54:55
 *
 */
public class SystemInit {

	/**
	 * Spring初始化前初始化内容
	 * @author ChengWenFeng 
	 * @date 2014年8月20日 下午2:55:28
	 */
	public static void initStart() {
		
		System.out.println("APP BIGIN......................");
		/***************************固定写法开始***************************/
		String path = System.getProperty("app");
		if (path == null) { // 测试路径
			path = "/Users/chengwenfeng/Documents/java_workspace/framework-demo/src/work/dev"; //需要修改的地方
			System.setProperty("app", path);
			System.out.println("debug Path=" + path);
		} else {
			System.out.println("Path=" + path);
		}
		
		// 初始化日志
		LogConfig.config(path + File.separator + "conf/logback.xml");
		System.out.println("logback.xml loaded");
		
		//加载配置文件
		ConfigFactory.init(path + File.separator + "conf/config.xml");
		System.out.println("config.xml loaded");
		
		/***************************固定写法结束***************************/
		
		// 初始化Memcache
		MemcacheConfigFactory.initCacheConf("demo");
		//初始化Mongodb
		MongoDbConfigFactory.initCacheConf("demo");
		
		//初始化关系行数据库
		DBConfigFactory.initConf("demo");
		
		
	}

	/**
	 * Spring初始化后，初始化内容
	 * @author ChengWenFeng 
	 * @date 2014年8月20日 下午2:55:52
	 */
	public static void initEnd() {
		System.out.println("init end");
	}

}
