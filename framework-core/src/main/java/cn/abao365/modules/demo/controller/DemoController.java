package cn.abao365.modules.demo.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import cn.abao365.common.json.JSONPObject;
import cn.abao365.common.util.StringUtil;
import cn.abao365.modules.demo.service.DemoService;
import cn.abao365.modules.demo.vo.DemoVO;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;


/**
 * 事例，控制层写法
 * @author chengwenfeng
 * @date 2015年5月8日 上午9:17:39
 *
 */
@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping("/demo")
public class DemoController {
	
	
	@Resource(name="demoService")
	private DemoService demoService;
	
	@Resource(name="captchaProducer")
	private Producer captchaProducer;
	
	/**
	 * 跳转到默认首页
	 * @author chengwenfeng
	 * @return 
	 * @return ModelAndView
	 * @date 2015年5月12日 上午9:55:35
	 */
	@RequestMapping
	public ModelAndView defaultPage(){
		ModelAndView mv = new ModelAndView("/demo/default");
		return mv;
	}
	
	/**
	 * GET请求跳转到添加页码
	 * @author chengwenfeng
	 * @return 
	 * @return ModelAndView
	 * @date 2015年5月12日 下午2:14:36
	 */
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public ModelAndView toAdd(){
		ModelAndView mv = new ModelAndView("/demo/add");
		
		return mv;
	}
	
	/**
	 * POST请求，跳转到详情页码
	 * @author chengwenfeng
	 * @param vo
	 * @return
	 * @throws Exception 
	 * @return ModelAndView
	 * @date 2015年5月12日 下午2:17:19
	 */
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public ModelAndView addDemo(DemoVO vo) throws Exception{
		ModelAndView mv = new ModelAndView();
		demoService.addDemo(vo);
		String viewName = "redirect:/demo/get/"+vo.getUuid();
		mv.setViewName(viewName);
		return mv;
	}
	
	
	/**
	 * GET请求跳转到更新页码
	 * @author chengwenfeng
	 * @return 
	 * @return ModelAndView
	 * @throws Exception 
	 * @date 2015年5月12日 下午2:14:36
	 */
	@RequestMapping(value="/update/{uuid}",method = RequestMethod.GET)
	public ModelAndView toUpdate(@PathVariable("uuid")String uuid) throws Exception{
		ModelAndView mv = new ModelAndView("/demo/update");
		mv.addObject("demo", demoService.getDemo(uuid));
		return mv;
	}
	
	/**
	 * POST请求，跳转到详情页码
	 * @author chengwenfeng
	 * @param vo
	 * @return
	 * @throws Exception 
	 * @return ModelAndView
	 * @date 2015年5月12日 下午2:17:19
	 */
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public ModelAndView updateDemo(DemoVO vo) throws Exception{
		ModelAndView mv = new ModelAndView();
		String viewName = "redirect:/demo/find/";
		if(StringUtils.isNotEmpty(vo.getUuid())){
			demoService.updateDemo(vo);
			viewName = "redirect:/demo/get/"+vo.getUuid();
		}
		mv.setViewName(viewName);
		return mv;
	}
	
	/**
	 * 根据ID展示
	 * @author ChengWenFeng
	 * @param uuid ID
	 * @return
	 * @throws Exception 
	 * @date 2014年8月20日 下午2:07:53
	 */
	@RequestMapping("/get/{uuid}")
	public ModelAndView get(@PathVariable("uuid")String uuid) throws Exception{
		ModelAndView mv = new ModelAndView("/demo/detail");
		mv.addObject("demo", demoService.getDemo(uuid));
		return mv;
	}
	
	
	/**
	 * 列表页面
	 * @author chengwenfeng
	 * @return 
	 * @return ModelAndView
	 * @throws Exception 
	 * @date 2015年5月12日 上午9:55:24
	 */
	@RequestMapping("/find")
	public ModelAndView findDemo(DemoVO vo) throws Exception{
		ModelAndView mv = new ModelAndView("/demo/list");
		mv.addObject("page", demoService.findPager(vo));
		mv.addObject("query", vo);
		return mv;
	}
	
	
	/**
	 * 根据ID删除
	 * @author ChengWenFeng
	 * @param uuid ID
	 * @return
	 * @throws Exception 
	 * @date 2014年8月20日 下午2:08:46
	 */
	@RequestMapping("/del/{uuid}")
	public ModelAndView delByUuid(@PathVariable("uuid")String uuid) throws Exception{
		ModelAndView mv = new ModelAndView();
		demoService.deleteDemoById(uuid);
		String viewName = "redirect:/demo/find";
		mv.setViewName(viewName);
		return mv;
	}
	
	
	/**
	 *  图片首页数据
	 * @author ChengWenFeng
	 * @param flush
	 * @return
	 * @throws Exception 
	 * @date 2014年12月3日 上午10:00:30
	 */
	@RequestMapping("/home/data")
	public @ResponseBody Object homePage(@RequestParam(value="flush",required = false)String flush,
			@RequestParam(value = "callback", defaultValue = "") String callback) throws Exception{
		JSONArray result = demoService.findHomePageData(flush);
		if(StringUtil.filterCallback(callback)){
			return new JSONPObject(callback, result);
		}
		return result;
	}
	
	
	@RequestMapping("/image/create")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// Set to expire far in the past.
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// return a jpeg
		response.setContentType("image/jpeg");
		// create the text for the image
		String capText = captchaProducer.createText();
		// store the text in the session
		request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY,
				capText);
		// create the image with the text
		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		// write the data out
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
		return null;
	}
	
	/**
	 * 单文件上传
	 * @author chengwenfeng
	 * @param name
	 * @param file
	 * @return
	 * @return String
	 * @throws Exception 
	 * @date 2015年5月13日 上午11:39:59
	 */
	@RequestMapping("/upload"  )  
    public ModelAndView upload(@RequestParam("file")MultipartFile file) throws Exception {  
        ModelAndView mv = new ModelAndView("/demo/uploadDetail");
		if(!file.isEmpty()){
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			IOUtils.copy(file.getInputStream(), baos);
		//	TFSResult tfsResult =  demoService.storeUploadImage(baos);
		//	mv.addObject("tfsResult", tfsResult);
		}
        return mv;  
    }

	/**
	 * 多文件上传
	 * @author chengwenfeng
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * @return ModelAndView
	 * @date 2015年5月13日 下午2:25:56
	 */
	@RequestMapping("/upload3"  )  
    public ModelAndView upload3(HttpServletRequest request) throws Exception {  
		ModelAndView mv = new ModelAndView("/demo/uploadDetail");
        //创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
//            List<TFSResult> tFSResults = new ArrayList<TFSResult>();
//            while(iter.hasNext()){  
//                //记录上传过程起始时的时间，用来计算上传时间  
//               // int pre = (int) System.currentTimeMillis();  
//                //取得上传文件  
//                MultipartFile file = multiRequest.getFile(iter.next());  
//                if(!file.isEmpty()){
//        			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        			IOUtils.copy(file.getInputStream(), baos);
//        			TFSResult tfsResult =  demoService.storeUploadImage(baos);
//        			tFSResults.add(tfsResult);
//        		}
//                //记录上传该文件后的时间  
//                //int finaltime = (int) System.currentTimeMillis();  
//            }  
//            mv.addObject("tFSResults",tFSResults);
              
        }  
        return mv;  
    }
	
}
