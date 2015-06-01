package cn.abao365.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import cn.abao365.common.conf.ConfigFactory;
import cn.abao365.common.thread.SystemContext;
import cn.abao365.common.util.InetUtils;
import cn.abao365.common.util.StringUtil;
import cn.abao365.common.util.WEBUtil;

/**
 * 日志拦截器
 * @author ChengWenFeng
 * @date 2014年8月20日 下午2:54:35
 *
 */
public class LogInterceptor implements HandlerInterceptor{
	
	private static Logger log = LoggerFactory.getLogger(LogInterceptor.class);
	

	/**
	 * 在DispatcherServlet完全处理完请求后被调用 
	 *   生成视图之后执行
	 *   当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception exception)
			throws Exception {
		//==============执行顺序: 3、afterCompletion================
		long pid = SystemContext.getPID();
		long startTime = SystemContext.getSTART_TIME();
		long btime = System.currentTimeMillis();
		if(exception != null){
			String method = request.getMethod();
			String path = request.getServletPath();
			String ip = WEBUtil.getIP(request);
			JSONObject objs =WEBUtil.getRequestParameters(request);
			//System.out.println("path:"+path+"    method:"+method+"    ip:"+ip+"    objs:"+objs);
			
			if(!ConfigFactory.getBoolean("debug",false)){
				//发送邮件
			}
			log.error("pid:{}\tbtime:{}\tetime:{}\tpath:{}\tmethod:{}\tip:{}\tobjs:{}",pid,btime,(btime-startTime),path,method,ip,objs,exception);
		}else{
			log.info("pid:{}\tbtime:{}\tetime:{}",pid,btime,(btime-startTime));
		}
		SystemContext.removePID();
		SystemContext.removeSTART_TIME();
	}

	
	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作   
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj, ModelAndView mv) throws Exception {
		//==============执行顺序: 2、postHandle================
	}

	/**
	 * 在业务处理器处理请求之前被调用<br/>
	 * 如果返回false<br/>
	 *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链<br/>
	 * 如果返回true<br/>
	 *    执行下一个拦截器,直到所有的拦截器都执行完毕<br/>
	 *    再执行被拦截的Controller<br/>
	 *    然后进入拦截器链,<br/>
	 *    从最后一个拦截器往回执行所有的postHandle()<br/>
	 *    接着再从最后一个拦截器往回执行所有的afterCompletion()<br/>
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Exception {
		//==============执行顺序: 1、preHandle================
		long pid = StringUtil.getPID();
		long btime = System.currentTimeMillis();
		SystemContext.setPID(pid);
		SystemContext.setSTART_TIME(btime);
		String method = request.getMethod();
		String path = request.getServletPath();
		String ip = WEBUtil.getIP(request);
		JSONObject objs =WEBUtil.getRequestParameters(request);
		log.info("pid:{}\tbtime:{}\tetime:{}\tpath:{}\tmethod:{}\tip:{}\tobjs:{}",pid,btime,0,path,method,ip,objs);
		return true;
	}

}
