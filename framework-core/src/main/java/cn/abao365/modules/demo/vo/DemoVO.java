package cn.abao365.modules.demo.vo;

import cn.abao365.modules.demo.entity.Demo;


/**
 * 用户信息，对应页面字段
 * @author ChengWenFeng
 * @date 2014年8月20日 下午2:01:48
 *
 */
public class DemoVO extends Demo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int currentPage = 1;	//当前页
	private int pageSize = 10; //每页多少条数据
	
	public int getStartRowNum() {
		return (currentPage-1)*pageSize;
	}

	/**
	 * 当前页
	 * @return
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	
	/**
	 * 当前页
	 * @param currentPage
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	/**
	 * 每页多少条数据
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}
	
	/**
	 * 每页多少条数据
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	

}
