package cn.abao365.modules.demo.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import cn.abao365.common.util.StringUtil;
import cn.abao365.modules.demo.constants.DemoConstants;
import cn.abao365.modules.demo.dao.DemoDao;
import cn.abao365.modules.demo.entity.Demo;
import cn.abao365.modules.demo.service.DemoService;
import cn.abao365.modules.demo.vo.DemoVO;
import cn.abao365.modules.demo.vo.PagerVO;

/**
 * 
 * @author chengwenfeng
 * @date 2015年5月14日 上午10:47:42
 *
 */
@Service("demoService")
public class DemoServiceImpl implements DemoService{
	
	@Resource(name="demoMybatisDao")
	private DemoDao demoDao;
	
	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.service.DemoService#addDemo(cn.abao365.modules.demo.vo.DemoVO)
	 */
	@Override
	public void addDemo(DemoVO vo) throws Exception {
		vo.setUuid(StringUtil.getUUID2());
		vo.setCreateTime(new Date());
		demoDao.saveDemo(vo);
	}

	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.service.DemoService#getDemo(java.lang.String)
	 */
	@Override
	public Demo getDemo(String uuid) throws Exception {
		return demoDao.getDemoById(uuid);
	}

	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.service.DemoService#findPager(cn.abao365.modules.demo.vo.DemoVO)
	 */
	@Override
	public PagerVO findPager(DemoVO vo) throws Exception {
		PagerVO pagerVO = new PagerVO();
		long totalRows = demoDao.findDemoTotalRowsByVO(vo);
		if(totalRows > 0){
			pagerVO.setTotalRows(totalRows);
			pagerVO.setData(demoDao.findDemoPageByVO(vo));
		}
		pagerVO.setCurrentPage(vo.getCurrentPage());
		pagerVO.setPageSize(vo.getPageSize());
		return pagerVO;
	}

	
	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.service.DemoService#updateDemo(cn.abao365.modules.demo.vo.DemoVO)
	 */
	@Override
	public void updateDemo(DemoVO vo) throws Exception {
		demoDao.updateDemoById(vo);
	}

	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.service.DemoService#deleteDemoById(java.lang.String)
	 */
	@Override
	public void deleteDemoById(String uuid) throws Exception{
		demoDao.deleteDemoById(uuid);
	}

	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.service.DemoService#findHomePageData(java.lang.String)
	 */
	@Override
	public JSONArray findHomePageData(String flush) throws Exception{
		//需要刷新
		if(StringUtils.isEmpty(flush) || !flush.equals(DemoConstants.FLUSH)){
			Object tmpObject = demoDao.getCache("image_home_page_data");
					//memcachedClient.get("image_home_page_data");
			if(tmpObject != null){
				return (JSONArray)tmpObject;
			}
		}
		JSONArray result  = demoDao.findImageHome();
		if(result != null && !result.isEmpty()){
			demoDao.setCache("image_home_page_data",60*5,result);
		}
		return result;

	}

	/*
	 * (non-Javadoc)
	 * @see cn.abao365.modules.demo.service.DemoService#storeUploadImage(java.io.ByteArrayOutputStream)
	 */
	@Override
	public String storeUploadImage(ByteArrayOutputStream baos) throws Exception {
		return null;
	}

}
