package cn.abao365.modules.demo.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页工具类
 * @author ChengWenFeng
 * @date 2014年8月20日 下午2:51:27
 *
 */
public class PagerVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long totalRows; // 总行数
	private int pageSize = 30; // 每页显示的行数
	private int currentPage; // 当前页号
	private List<?> data = new ArrayList<Object>(); //数据存放
	
	public PagerVO(){}
	
	/**
	 * 
	 * @param totalRows 记录总条数
	 * @param pageSize 每页多岁条
	 * @param currentPage 当前第几页
	 * @param data 数据
	 */
	public PagerVO(int totalRows,int pageSize,int currentPage,List<?> data){
		this.totalRows = totalRows;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.data = data;
	}
	
	/**
	 * 总行数
	 * @author ChengWenFeng
	 * @return 
	 * @date 2014年8月20日 下午2:52:46
	 */
	public long getTotalRows() {
		return totalRows;
	}


	/**
	 * @author ChengWenFeng
	 * @param totalRows  总行数
	 * @date 2014年8月20日 下午2:52:52
	 */
	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}


	/**
	 * @author ChengWenFeng
	 * @return 每页多少条数据
	 * @date 2014年8月20日 下午2:53:04
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 
	 * @author ChengWenFeng
	 * @param pageSize  每页多少条数据
	 * @date 2014年8月20日 下午2:53:14
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 
	 * @author ChengWenFeng
	 * @return  当前页
	 * @date 2014年8月20日 下午2:53:28
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 
	 * @author ChengWenFeng
	 * @param currentPage  当前页
	 * @date 2014年8月20日 下午2:53:37
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * 数据
	 * @author ChengWenFeng
	 * @return 
	 * @date 2014年8月20日 下午2:52:30
	 */
	public List<?> getData() {
		return data;
	}

	/**
	 * 
	 * @author ChengWenFeng
	 * @param data  数据
	 * @date 2014年8月20日 下午2:52:22
	 */
	public void setData(List<?> data) {
		this.data = data;
	}

	/**
	 * 总页数
	 * @author ChengWenFeng
	 * @return 
	 * @date 2014年8月20日 下午2:54:20
	 */
	public long getTotalPages() {
		return (totalRows+pageSize-1)/pageSize;
	}

	/**
	 * 是否有数据
	 * @author ChengWenFeng
	 * @return 
	 * @date 2014年8月20日 下午2:54:00
	 */
	public boolean isPageState() {
		if(totalRows > 0){
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		//11 5 
		int a = (1+5-1)/5;
		System.out.println(a);
	}
}
