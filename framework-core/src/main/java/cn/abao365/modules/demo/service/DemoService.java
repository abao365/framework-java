package cn.abao365.modules.demo.service;

import java.io.ByteArrayOutputStream;

import com.alibaba.fastjson.JSONArray;
import cn.abao365.modules.demo.entity.Demo;
import cn.abao365.modules.demo.vo.DemoVO;
import cn.abao365.modules.demo.vo.PagerVO;


/**
 * 事例：Service接口写法
 * @author chengwenfeng
 * @date 2015年5月8日 上午10:03:54
 *
 */
public interface DemoService {
	
	/**
	 * 添加
	 * @author chengwenfeng
	 * @param vo
	 * @throws Exception 
	 * @return void
	 * @date 2015年5月12日 上午11:34:27
	 */
	public void addDemo(DemoVO vo)throws Exception;

	/**
	 * 根据ID获取数据
	 * @author chengwenfeng
	 * @param uuid
	 * @return 
	 * @return Demo
	 * @throws Exception 
	 * @date 2015年5月12日 下午2:18:20
	 */
	public Demo getDemo(String uuid) throws Exception;

	/**
	 * 根据条件分页查询
	 * @author chengwenfeng
	 * @param vo
	 * @return
	 * @throws Exception 
	 * @return PagerVO
	 * @date 2015年5月12日 下午3:10:48
	 */
	public PagerVO findPager(DemoVO vo) throws Exception;

	/**
	 * 更新
	 * @author chengwenfeng
	 * @param vo
	 * @throws Exception 
	 * @return void
	 * @date 2015年5月12日 下午4:39:17
	 */
	public void updateDemo(DemoVO vo) throws Exception;

	/**
	 * 根据组件删除信息
	 * @author chengwenfeng
	 * @param uuid
	 * @throws Exception 
	 * @return void
	 * @date 2015年5月12日 下午4:39:10
	 */
	public void deleteDemoById(String uuid) throws Exception;

	/**
	 * Mongodb事例
	 * @author chengwenfeng
	 * @param flush
	 * @throws Exception 
	 * @return JSONArray
	 * @date 2015年5月12日 下午4:46:14
	 */
	public JSONArray findHomePageData(String flush) throws Exception;

	/**
	 * 上传文件
	 * @author chengwenfeng
	 * @param baos
	 * @return
	 * @throws Exception 
	 * @return TFSResult
	 * @date 2015年5月13日 上午11:44:25
	 */
	public String storeUploadImage(ByteArrayOutputStream baos) throws Exception ;

}
