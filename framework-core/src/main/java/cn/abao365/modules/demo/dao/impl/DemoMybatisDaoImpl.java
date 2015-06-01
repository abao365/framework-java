package cn.abao365.modules.demo.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.rubyeye.xmemcached.MemcachedClient;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cn.abao365.common.mongodb.MongoDBManager;
import cn.abao365.modules.demo.dao.DemoDao;
import cn.abao365.modules.demo.entity.Demo;
import cn.abao365.modules.demo.vo.DemoVO;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


/**
 * MyBatis实现
 * @author chengwenfeng
 * @date 2015年5月11日 下午8:29:50
 *
 */
@Repository("demoMybatisDao")
public class DemoMybatisDaoImpl implements DemoDao{
	
	

	@Resource(name="demoMemcachedClient")
	private MemcachedClient demoMemcachedClient;
	
	@Resource(name="demoMongoDBManager")
	private MongoDBManager demoMongoDBManager;
	
	@Resource(name="demoMybatisSqlSession")
	protected SqlSession demoSqlSession;
	
	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.dao.DemoDao#findDemoByVO(cn.abao365.modules.demo.vo.DemoVO)
	 */
	@Override
	public List<Demo> findDemoByVO(DemoVO vo) throws Exception {
		return demoSqlSession.selectList(PREFIX+".findDemoByVO", vo);
	}

	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.dao.DemoDao#findDemoTotalRowsByVO(cn.abao365.modules.demo.vo.DemoVO)
	 */
	@Override
	public long findDemoTotalRowsByVO(DemoVO vo) throws Exception {
		return demoSqlSession.selectOne(PREFIX+".findDemoTotalRowsByVO",vo);
	}
	
	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.dao.DemoDao#findDemoTotalRows()
	 */
	@Override
	public long findDemoTotalRows() throws Exception {
		return demoSqlSession.selectOne(PREFIX+".findDemoTotalRows");
	}

	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.dao.DemoDao#findAllDemo()
	 */
	@Override
	public List<Demo> findAllDemo() {
		return demoSqlSession.selectList(PREFIX+".findAllDemo");
	}

	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.dao.DemoDao#deleteDemoByVO(cn.abao365.modules.demo.vo.DemoVO)
	 */
	@Override
	public void deleteDemoByVO(DemoVO vo) throws Exception {
		
	}

	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.dao.DemoDao#saveDemo(cn.abao365.modules.demo.vo.DemoVO)
	 */
	@Override
	public void saveDemo(DemoVO vo) throws Exception {
		demoSqlSession.insert(PREFIX+".saveDemo",vo);
	}

	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.dao.DemoDao#updateDemoByVO(cn.abao365.modules.demo.vo.DemoVO)
	 */
	@Override
	public void updateDemoById(DemoVO vo) throws Exception {
		demoSqlSession.update(PREFIX+".updateDemoById", vo);
	}

	
	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.dao.DemoDao#getDemoById(java.lang.String)
	 */
	@Override
	public Demo getDemoById(String uuid) {
		return demoSqlSession.selectOne(PREFIX+".getDemoById", uuid);
	}   

	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.dao.DemoDao#deleteDemoById(java.lang.String)
	 */
	@Override
	public void deleteDemoById(String uuid) throws Exception {
		demoSqlSession.delete(PREFIX+".deleteDemoById", uuid);
	}

	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.dao.DemoDao#findDemoPageByVO(cn.abao365.modules.demo.vo.DemoVO)
	 */
	@Override
	public List<Demo> findDemoPageByVO(DemoVO vo) throws Exception {
		return demoSqlSession.selectList(PREFIX+".findDemoPageByVO", vo);
	}

	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.dao.DemoDao#getCache(java.lang.String)
	 */
	@Override
	public Object getCache(final String key) throws Exception{
		return demoMemcachedClient.get(key);
	}

	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.dao.DemoDao#setCache(java.lang.String,java.lang.Integer,java.lang.Object)
	 */
	@Override
	public void setCache(final String key, final int exp, final Object value) throws Exception {
		demoMemcachedClient.set(key, exp, value);
	}

	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.dao.DemoDao#findImageHome()
	 */
	@Override
	public JSONArray findImageHome() {
		DBObject orderBy = new BasicDBObject();
		orderBy.put("idx",1);
		DBCursor cursor = demoMongoDBManager.getCollection("images_homepage").find().sort(orderBy);
		JSONArray data = new JSONArray();
		while (cursor.hasNext()) {
			DBObject tmp = cursor.next();
			JSONObject t = JSONObject.parseObject(tmp.toString());
			data.add(t);
		}
		return data;
	}
	
	
	
}
