package cn.abao365.modules.demo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
 * 
 * @author chengwenfeng
 * @date 2015年5月11日 下午8:29:50
 *
 */
@Repository("demoDao")
public class DemoDaoImpl implements DemoDao{

	@Resource(name="demoMemcachedClient")
	private MemcachedClient demoMemcachedClient;
	
	@Resource(name="demoMongoDBManager")
	private MongoDBManager demoMongoDBManager;
	
	@Resource(name="demoJdbcTemplate")
	private JdbcTemplate demoJdbcTemplate;
	
	@Resource(name="demoNamedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate demoNamedParameterJdbcTemplate;

	
	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.dao.DemoDao#findDemoByVO(cn.abao365.modules.demo.vo.DemoVO)
	 */
	@Override
	public List<Demo> findDemoByVO(DemoVO vo) throws Exception {
		String sql = "SELECT _uuid,_user_name,_password,_age,_sex,_create_time from demo_user "+getWhereSql(vo)+"ORDER BY _create_time desc";
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(vo);
		return demoNamedParameterJdbcTemplate.query(sql, paramSource,new DemoMapper());
	}

	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.dao.DemoDao#findDemoTotalRowsByVO(cn.abao365.modules.demo.vo.DemoVO)
	 */
	@Override
	public long findDemoTotalRowsByVO(DemoVO vo) throws Exception {
		//SELECT * from demo_user d where 1=1 ORDER BY _create_time desc LIMIT 0,1
		String sql = "SELECT count(*) from demo_user "+getWhereSql(vo);
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(vo);
		return demoNamedParameterJdbcTemplate.queryForObject(sql, paramSource,Long.class);
	}
	
	
	/**
	 * 拼装sql
	 * @author chengwenfeng
	 * @param vo
	 * @return 
	 * @return String
	 * @date 2015年5月12日 下午3:33:20
	 */
	private static String getWhereSql(DemoVO vo){
		StringBuilder sb = new StringBuilder(" where 1=1 ");
		if(StringUtils.isNotEmpty(vo.getUserName())){
			sb.append(" and _user_name like :userName ");
		}
		if(StringUtils.isNotEmpty(vo.getSex())){
			sb.append(" and _sex = sex ");
		}
		return sb.toString();
	}
	

	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.dao.DemoDao#findDemoTotalRows()
	 */
	@Override
	public long findDemoTotalRows() throws Exception {
		//count(*)
		String sql = "SELECT count(*) from demo_user";
		return demoJdbcTemplate.queryForObject(sql, Long.class);
	}

	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.dao.DemoDao#findAllDemo()
	 */
	@Override
	public List<Demo> findAllDemo() {
		String sql = "SELECT * from demo_user ";
		return demoNamedParameterJdbcTemplate.query(sql,new DemoMapper());
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
		final	String sql = "INSERT INTO demo_user(_uuid,_user_name,_password,_age,_sex,_create_time) VALUES(:uuid,:userName,:password,:age,:sex,:createTime)";
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(vo);
		demoNamedParameterJdbcTemplate.update(sql, paramSource);
	}

	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.dao.DemoDao#updateDemoByVO(cn.abao365.modules.demo.vo.DemoVO)
	 */
	@Override
	public void updateDemoById(DemoVO vo) throws Exception {
		final String sql = "UPDATE demo_user SET _user_name=:userName,_password=:password,_age=:age,_sex=:sex WHERE _uuid=:uuid";
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(vo);
		demoNamedParameterJdbcTemplate.update(sql, paramSource);
	}

	
	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.dao.DemoDao#getDemoById(java.lang.String)
	 */
	@Override
	public Demo getDemoById(String uuid) {
		final String sql = "SELECT * from demo_user where _uuid = ?";
		return demoJdbcTemplate.queryForObject(sql, new DemoMapper(), uuid);
	}   


	/**
	 * 定义一个静态内部类，在Dao的方法中被共享  
	 * @author chengwenfeng
	 * @date 2015年5月12日 下午2:34:03
	 *
	 */
	private static final class DemoMapper implements RowMapper<Demo>{   
        public Demo mapRow(ResultSet rs, int rowNum) throws SQLException {   
        	Demo demo = new Demo();
        	demo.setAge(rs.getInt("_age"));
        	demo.setCreateTime(rs.getTimestamp("_create_time"));
        	demo.setPassword(rs.getString("_password"));
        	demo.setUserName(rs.getString("_user_name"));
        	demo.setUuid(rs.getString("_uuid"));
        	demo.setSex(rs.getString("_sex"));
            return demo;   
        }   
           
    }


	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.dao.DemoDao#deleteDemoById(java.lang.String)
	 */
	@Override
	public void deleteDemoById(String uuid) throws Exception {
		String sql = "delete from demo_user where _uuid=?";
		demoJdbcTemplate.update(sql, uuid);
	}

	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.dao.DemoDao#findDemoPageByVO(cn.abao365.modules.demo.vo.DemoVO)
	 */
	@Override
	public List<Demo> findDemoPageByVO(DemoVO vo) throws Exception {
		String sql = "SELECT _uuid,_user_name,_password,_age,_sex,_create_time from demo_user "+getWhereSql(vo)+"ORDER BY _create_time desc LIMIT :startRowNum,:pageSize";
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(vo);
		return demoNamedParameterJdbcTemplate.query(sql, paramSource,new DemoMapper());
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
