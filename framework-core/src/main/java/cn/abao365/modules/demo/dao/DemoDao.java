package cn.abao365.modules.demo.dao;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import cn.abao365.modules.demo.entity.Demo;
import cn.abao365.modules.demo.vo.DemoVO;



/**
 * 事例：DAO层接口写法
 * @author chengwenfeng
 * @date 2015年5月8日 上午10:04:28
 *
 */
public interface DemoDao {
	
	/**
	 * MyBatis命名空间
	 */
	final String PREFIX = "cn.abao365.modules.demo.dao.DemoDao";
	
	/**
	 * 根据查询条件查询数据
	 * @author ChengWenFeng
	 * @param vo 查询条件
	 * @return 用户信息列表
	 * @throws Exception 
	 * @date 2014年8月20日 下午2:02:51
	 */
	public List<Demo> findDemoByVO(DemoVO vo)  throws Exception ;
	
	
	/**
	 * 根据查询条件分页查询数据
	 * @author ChengWenFeng
	 * @param vo 查询条件
	 * @return 用户信息列表
	 * @throws Exception 
	 * @date 2014年8月20日 下午2:02:51
	 */
	public List<Demo> findDemoPageByVO(DemoVO vo)  throws Exception ;
	
	/**
	 * 查询符合条件的数据行数
	 * @author ChengWenFeng
	 * @param vo 查询条件
	 * @return 行数
	 * @throws Exception 
	 * @date 2014年8月20日 下午2:03:19
	 */
	public long findDemoTotalRowsByVO(DemoVO vo)  throws Exception;
	
	/**
	 * 查询共多少数据
	 * @author chengwenfeng
	 * @return
	 * @throws Exception 
	 * @return int
	 * @date 2015年5月12日 上午10:04:03
	 */
	public long findDemoTotalRows()  throws Exception;
	
	/**
	 * 查询全部
	 * @author ChengWenFeng
	 * @return 用户列表
	 * @date 2014年8月20日 下午2:03:40
	 */
	public List<Demo> findAllDemo();
	
	/**
	 * 根据条件删除信息
	 * @author ChengWenFeng
	 * @param vo 查询条件
	 * @throws Exception 
	 * @date 2014年8月20日 下午2:03:55
	 */
	public void deleteDemoByVO(DemoVO vo)  throws Exception ;
	
	/**
	 * 根据ID删除信息
	 * @author chengwenfeng
	 * @param uuid
	 * @throws Exception 
	 * @return void
	 * @date 2015年5月12日 下午3:42:30
	 */
	public void deleteDemoById(String uuid)  throws Exception ;
	
	/**
	 * 添加用户信息
	 * @author ChengWenFeng
	 * @param vo 查询条件
	 * @throws Exception 
	 * @date 2014年8月20日 下午2:04:21
	 */
	public void saveDemo(DemoVO vo)  throws Exception ;
	
	/**
	 * 根据用户id更新用户信息
	 * @author ChengWenFeng
	 * @param vo 新的用户信息
	 * @throws Exception 
	 * @date 2014年8月20日 下午2:05:04
	 */
	public void updateDemoById(DemoVO vo) throws Exception;


	/**
	 * 根据主键获取数据
	 * @author chengwenfeng
	 * @param uuid
	 * @return Demo
	 * @throws Exception 
	 * @date 2015年5月12日 下午2:23:08
	 */
	public Demo getDemoById(String uuid) throws Exception;


	/**
	 * 获取缓存数据
	 * @author chengwenfeng
	 * @param key
	 * @return Object
	 * @throws Exception 
	 * @date 2015年5月12日 下午5:00:42
	 */
	public Object getCache(final String key) throws Exception;


	/**
	 * 设置缓存
	 * @author chengwenfeng
	 * @param key 缓存key
	 * @param exp 缓存时间
	 * @param value 缓存数据
	 * @return void
	 * @throws Exception 
	 * @date 2015年5月12日 下午5:02:59
	 */
	public void setCache(final String key, final int exp, final Object value)  throws Exception;


	/**
	 * 从Mongodb获取数据
	 * @author chengwenfeng
	 * @return JSONArray
	 * @throws Exception 
	 * @date 2015年5月12日 下午5:08:01
	 */
	public JSONArray findImageHome() throws Exception;
	

}
