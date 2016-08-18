package com.atguigu.survey.admin.component.intecept;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.atguigu.survey.admin.component.service.i.ResourceService;
import com.atguigu.survey.admin.entity.Resource;
import com.atguigu.survey.utils.DataProcessUtils;
import com.atguigu.survey.utils.ValidateUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ResourceInteceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invoke) throws Exception {
		//1.获取Action代理类
		ActionProxy proxy = invoke.getProxy();
		//2.获取到actionName
		String actionName = proxy.getActionName();
		//如果ActionName为空，则继续调用下一个拦截器
		if(!ValidateUtils.stringValidate(actionName)) return invoke.invoke();
		
		//3.向数据库中保存resource
		//①在数据库中获取权限位 
		//首先获取到ioc
		ServletContext servletContext = ServletActionContext.getServletContext();
		WebApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		ResourceService resourceService = ioc.getBean(ResourceService.class);
		
		Resource resourceDB = resourceService.getResourceByActionName(actionName);
		if(resourceDB != null) return invoke.invoke();
		
		//①.在数据库中获取resPos
		Integer resPos= resourceService.getMaxResPos();
		Long resCode = null;
		//如果等于null说明数据库中为空， 没有资源
		if(resPos == null){
			resPos = 0;
			resCode = 1L;
		}else{
			//如果不为null说明数据库中有数据。
			//②在数据库获取权限码
			long maxCode = resourceService.getMaxResCode(resPos);
			if((maxCode << 1) > 2305843009213693952L ){
				//超出了long型能表示的范围， 进行过度
				resPos ++;
				resCode = 1L;
			}else{
				resCode = maxCode << 1;
			}
		}
		
		String resourceName = DataProcessUtils.translateActionName(actionName);
		
		Resource resource = new Resource(resourceName, actionName, resPos, resCode);
		resourceService.saveEntity(resource);
	
		return invoke.invoke();
	}

}
