package cn.abao365.common.listener;


import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

//import cn.abao365.common.cache.ApplicationContextManager;
import cn.abao365.common.init.SystemInit;

public class SystemInitListener extends ContextLoaderListener {
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		SystemInit.initStart();
		super.contextInitialized(sce);
	//	ApplicationContextManager.initApplicationContext(sce.getServletContext());
		SystemInit.initEnd();
	}
}
