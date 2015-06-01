package cn.abao365.common.thread;


/**
 * 线程处理
 * @author ChengWenFeng
 * @date 2014年10月15日 下午4:50:20
 *
 */
public final class SystemContext {

	private SystemContext(){};
	
	/**
	 * 标注当前线程
	 */
	private static ThreadLocal<Long> PID = new ThreadLocal<Long>();
	
	/**
	 * 当前线程开始时间
	 */
	private static ThreadLocal<Long> START_TIME = new ThreadLocal<Long>();
	
	public static void setPID(Long pid){
		PID.set(pid);
	}
	
	public static Long getPID(){
		return PID.get();
	}
	
	public static void removePID(){
		PID.remove();
	}
	
	public static void setSTART_TIME(long time){
		START_TIME.set(time);
	}
	
	public static long getSTART_TIME(){
		return START_TIME.get();
	}
	
	public static void removeSTART_TIME(){
		START_TIME.remove();
	}
	
}
