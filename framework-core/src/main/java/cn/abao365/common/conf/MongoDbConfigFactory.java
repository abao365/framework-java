package cn.abao365.common.conf;


/**
 * MongoDb缓存工厂
 * 
 * @author tongys
 * @date 2013-11-8
 */
public final class MongoDbConfigFactory {

	private MongoDbConfigFactory() {
		/* empty */
	}
	
	/**
	 * 初始化缓存工厂
	 * @param name 
	 */
	public static void initCacheConf(String name) {
		System.setProperty("common.cache.mongodb."+name+".nodelist",ConfigFactory.getString("common.cache.mongodb."+name+".nodelist"));
		System.setProperty("common.cache.mongodb."+name+".dbname",ConfigFactory.getString("common.cache.mongodb."+name+".dbname"));
	}
}
