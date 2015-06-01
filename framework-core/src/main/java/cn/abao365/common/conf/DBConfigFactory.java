package cn.abao365.common.conf;

/**
 * 关系数据库初始化
 * @author ChengWenFeng
 * @date 2014年8月20日 下午2:57:16
 *
 */
public class DBConfigFactory {
	
	/**
	 * init 配置
	 * 
	 * @param dbName 
	 * @author yaoqingyuan
	 * @date 2012-9-22
	 */
	public static void initConf(String dbName) {
//		System.setProperty("common.db." + dbName + ".driverClassName", ConfigFactory
//		        .getString("common.db." + dbName + ".driverClassName"));
		System.setProperty("common.db." + dbName + ".url", ConfigFactory.getString("common.db." + dbName + ".url"));
		System.setProperty("common.db." + dbName + ".username", ConfigFactory.getString("common.db." + dbName + ".username"));
		System.setProperty("common.db." + dbName + ".password", ConfigFactory.getString("common.db." + dbName + ".password"));
		System.setProperty("common.db." + dbName + ".initialSize", ConfigFactory
		        .getString("common.db." + dbName + ".initialSize"));
		System.setProperty("common.db." + dbName + ".max", ConfigFactory.getString("common.db."
		        + dbName + ".max"));
		System.setProperty("common.db." + dbName + ".min", ConfigFactory.getString("common.db."
		        + dbName + ".min"));
//		System.setProperty("common.db." + dbName + ".increment", ConfigFactory
//		        .getString("common.db." + dbName + ".increment"));
	}

}
