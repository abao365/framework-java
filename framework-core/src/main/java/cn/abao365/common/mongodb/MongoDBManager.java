package cn.abao365.common.mongodb;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceCommand.OutputType;
import com.mongodb.MapReduceOutput;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

/**
 * Mongodb工具类
 * @author ChengWenFeng
 * @date 2014年8月20日 下午2:59:16
 *
 */
public class MongoDBManager {
	
	
	public MongoClient mongo = null;
	
	public DB db = null;
	
	private List<ServerAddress> addresses;
	
	private String dbName;
	
	
	public MongoDBManager(){}
	
	/**
	 * 构造函数初始化
	 * @param addressList
	 * @param dbName
	 */
	public MongoDBManager(String addressList,String dbName){
		this.setAddresses(addressList);
		this.setDbName(dbName);
	}
	
	/**
	 * 设置链接地址格式为：10.10.34.43:11211,10.10.34.43:11212,10.10.34.42:11211,10.10.34.42:11212
	 * @author chengwenfeng
	 * @param addressList 
	 * @return void
	 * @date 2015年4月28日 上午10:27:37
	 */
	public void setAddresses(String addressList) {
		String[] address  = addressList.split(",");
		if(address != null){
			addresses = new ArrayList<ServerAddress>();
			for (String str : address) {
				int idx = str.indexOf( ":" );
			    if ( idx > 0 ){
			            try {
			            	int s_port = Integer.parseInt(str.substring( idx + 1 ) );
					        String s_host = str.substring(0 , idx ).trim();
							addresses.add(new ServerAddress(s_host, s_port));
						} catch (UnknownHostException e) {
							e.printStackTrace();
						}
			    }
			}
		}
	}
	 

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	/**
	 * 初始化接口，基于 replicset 的方式连接mongodb
	 * @author chengwenfeng 
	 * @return void
	 * @date 2015年4月28日 上午10:28:46
	 */
	public void initialize(){ 
		try {		 
			mongo = new MongoClient(this.addresses);
			mongo.setWriteConcern(WriteConcern.SAFE);			
			db = mongo.getDB(dbName);
		} catch ( Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据表
	 * @param table
	 * @return
	 */
	public  DBCollection getCollection(String table){
		DBCollection coll = db.getCollection(table);
		return coll;
	}
	
	/**
	 * save函数实际就是根据参数条件,调用了insert或update函数.
	 * 如果想插入的数据对象存在,insert函数会报错,而save函数是改变原来的对象;
	 * 如果想插入的对象不存在,那么它们执行相同的插入操作.
	 * @param dbObject
	 * @param saveTo
	 */
	public  void save(DBObject dbObject, String saveTo){		
		DBCollection coll = getCollection(saveTo);	
		coll.save(dbObject);
	}
	
	/**
	 * 返回符合查询对象的第一条记录
	 * @param id
	 * @param table
	 * @return
	 */
	public  DBObject findOne(String _id, String table){
		DBCollection coll = getCollection(table);		
		DBObject item = new BasicDBObject();			
		item.put("_id", _id);
		DBObject exist = coll.findOne(item);
		return exist;
	}
	
	/**
	 * 删除一条数据
	 * @param _id
	 * @param table
	 */
	public  void delete(String _id, String table){
		DBCollection coll = getCollection(table);		
		DBObject item = new BasicDBObject();			
		item.put("_id", _id);
		coll.remove(item);
	}
	
	/**
	 * 返回符合查询对象的第一条记录
	 * @param obj
	 * @param table
	 * @return
	 */
	public  DBObject findOne(DBObject obj, String table){
		DBCollection coll = getCollection(table);		
		DBObject exist = coll.findOne(obj);
		return exist;
	}
	
	/**
	 * 返回数据表中的所有记录
	 * @param fromTable
	 * @return
	 * @throws DBAccessException
	 * @throws InterruptedException 
	 */
	public  DBCursor find(String fromTable) {
		DBCollection fromColl = getCollection(fromTable);
		DBCursor cursor = fromColl.find();
		return cursor;
	}
	
	/**
	 * 查询条件
	 * @param fromTable
	 * @param con
	 * @return
	 */
	public  DBCursor find(String fromTable, DBObject con){
		DBCollection fromColl = getCollection(fromTable);
		DBCursor cursor = fromColl.find(con);
		return cursor;
	}
		
	/**
	 * 删除数据表
	 * @param table
	 * @throws DBAccessException
	 */
	public  void drop(String table) {
		DBCollection coll = getCollection(table);
		coll.drop();
	}
	
	/**
	 * 分组操作
	 * @param table
	 * @param key
	 * @param con
	 * @param initial
	 * @param reduce
	 * @return
	 */
	public  BasicDBList group(String table, DBObject key, DBObject con, DBObject initial, String reduce){
		DBCollection coll = getCollection(table); 
		BasicDBList returnList = (BasicDBList)coll.group(key,con,initial,reduce); 
		return returnList;
	}
	
	/**
	 * 删除指定查询条件的记录。
	 * @param dbObject
	 * @param table
	 */
	public  void remove(DBObject dbObject, String table){
		DBCollection coll = getCollection(table); 
		DBObject item = new BasicDBObject();
		Object _id = dbObject.get("_id");
		item.put("_id", _id);		
		DBCursor cursor = coll.find(item); 
		while(cursor.hasNext()){
			DBObject removeObject = cursor.next();
			coll.remove(removeObject);
		}
	}
	
	/**
	 * 
	 * @param table
	 * @param map
	 * @param reduce
	 * @param outputCollection
	 * @param type
	 * @param query
	 * @return
	 */
	public  MapReduceOutput mapReduce(String table, String map, String reduce, String outputCollection, OutputType type, DBObject query){
		DBCollection coll = getCollection(table); 
		MapReduceCommand command = new MapReduceCommand(coll, map, reduce, outputCollection, type, query);
		MapReduceOutput output = coll.mapReduce(command);
		return output;
	}
	
	/**
	 * 创建索引
	 * @param table
	 * @param keys
	 * @param optionIN
	 */
	public  void ensureIndex(String table, DBObject keys, DBObject optionIN){
		DBCollection coll = getCollection(table); 		
		List<DBObject> indexList= coll.getIndexInfo();
		boolean isExist = false;
		for (DBObject item : indexList) {
			String name = (String) item.get("name");
			if(name.equals("_"+keys.keySet().toArray()[0])){
				isExist = true;
				break;
			}
		}
		if(!isExist){
			coll.createIndex(keys, optionIN);
		}
	}
	
	public  CommandResult runCommond(DBObject cmd){
		CommandResult result = db.command(cmd);
		return result;		
	}
	
	/**
	 * 获取数据库记录的节点值
	 * 如果不存在节点，返回null
	 * 如果存在节点，则返回对应的节点值
	 * @param path
	 * @return
	 */
	public  Object getNodeValue(DBObject obj, String path){		
		String[] pathList = path.split("/");
		DBObject robj = null;
		Object result = null;
		for(int i=0;i<pathList.length;i++){
			if(i == pathList.length-1){
				result = obj.get(pathList[pathList.length-1]);
			}else{
				if(obj.containsField(pathList[i])){
					robj = (DBObject) obj.get(pathList[i]);		
					obj = robj;
				}
				else{
					break;
				}
			}						
		}
		return result;
	}
	
	
	/**
	 * 分页查询符合条件的条数
	 * @author ChengWenFeng
	 * @param query  查询条件
	 * @param fromTable 查询表
	 * @return 
	 * @date 2014年9月15日 上午9:18:41
	 */
	public long findPagerCount(DBObject query,String fromTable) {
		DBCollection fromColl = getCollection(fromTable);
		return fromColl.count(query);
	}
	
	
	/**
	 * 分页查询
	 * @author ChengWenFeng
	 * @param query  查询条件
	 * @param fromTable 查询表
	 * @param orderBy 排序
	 * @param pageSize 每页多少条数据
	 * @return 
	 * @date 2014年9月15日 上午9:18:41
	 */
	public DBCursor findPager(DBObject query,String fromTable,DBObject orderBy,int pageSize) {
		DBCollection fromColl = getCollection(fromTable);
		DBCursor cursor = fromColl.find(query).sort(orderBy).limit(pageSize);
		return cursor;
	}
	
	
	/**
	 * 分页查询
	 * @author ChengWenFeng
	 * @param query  查询条件
	 * @param fromTable 查询表
	 * @param orderBy 排序
	 * @param skip 从那条开始
	 * @param pageSize 每页多少条数据
	 * @return 
	 * @date 2014年9月15日 上午9:18:41
	 */
	public DBCursor findPager(DBObject query,String fromTable,DBObject orderBy,int skip,int pageSize) {
		DBCollection fromColl = getCollection(fromTable);
		DBCursor cursor = fromColl.find(query).sort(orderBy).skip(skip).limit(pageSize);
		return cursor;
	}
	
	/**
	 * 插入数据到指定节点
	 * @param obj
	 * @param path
	 * @param value
	 */
	public  void putNodeValue(DBObject obj, String path, Object value){
		String[] pathList = path.split("/");
		DBObject robj = obj;
		for(int i=0;i<pathList.length;i++){
			if(i == pathList.length-1){
				robj.put(pathList[pathList.length-1], value);
			}else{
				robj = (DBObject) obj.get(pathList[i]);		
				obj = robj;
			}						
		}
	}
	
	
	/**
	 * 删除指定节点
	 * @param obj
	 * @param path
	 */
	public  void removeNode(DBObject obj, String path){
		String[] pathList = path.split("/");
		DBObject robj = obj;
		for(int i=0;i<pathList.length;i++){
			if(i == pathList.length-1){
				robj.removeField(pathList[pathList.length-1]);
			}else{
				robj = (DBObject) obj.get(pathList[i]);		
				obj = robj;
			}						
		}
	}
}
