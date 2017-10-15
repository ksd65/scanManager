/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/bms">bms</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.service.BaseService;

/**
 * 请求过滤拦截器
 * @author 
 * @version 2017-03-01
 */
public class SameUrlDataInterceptor implements HandlerInterceptor {

	private static final ThreadLocal<Long> startTimeThreadLocal =
			new NamedThreadLocal<Long>("ThreadLocal StartTime");
	
	/** 
     * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，SpringMVC中的Interceptor拦截器是链式的，可以同时存在 
     * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行，而且所有的Interceptor中的preHandle方法都会在 
     * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的，这种中断方式是令preHandle的返 
     * 回值为false，当preHandle的返回值为false的时候整个请求就结束了。 
     */  
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		
		 if(repeatDataValidator(request)){//如果重复相同数据  
			 response.sendRedirect("redirect:");  
//			 response.sendRedirect("redirect:/a/base/ajaxOper/xiaohangTest");  
//			 System.out.println("数据被拦截啦！");
             return false;  
		 }else{
			 return true;  
		 }
	}
	

/** 
 * 验证同一个url数据是否相同提交  ,相同返回true 
 * 添加时间限制  同一个url数据  3秒内只允许点击一次
 * @param httpServletRequest 
 * @return 
 */  
public boolean repeatDataValidator(HttpServletRequest httpServletRequest)  
{  
    String params=JsonMapper.toJsonString(httpServletRequest.getParameterMap());  
    String url=httpServletRequest.getRequestURI();  
    Map<String,String> map=new HashMap<String,String>();  
    map.put(url, params);  
    String nowUrlParams=map.toString();//  
    Long nowTime=System.currentTimeMillis();
      
    Object preUrlParams=httpServletRequest.getSession().getAttribute("repeatData");  
    if(preUrlParams==null)//如果上一个数据为null,表示还没有访问页面  
    {  
        httpServletRequest.getSession().setAttribute("repeatData", nowUrlParams);  
        httpServletRequest.getSession().setAttribute("repeatTime", nowTime);  
        return false;  
    }  
    else//否则，已经访问过页面  
    {  
        if(preUrlParams.toString().equals(nowUrlParams))//如果上次url+数据和本次url+数据相同，则表示城府添加数据  
        {  
            /*限制为每3秒内允许点击一次*/     //repeatTime
        	Long oldTime=(Long) httpServletRequest.getSession().getAttribute("repeatTime");  
        	Long differ = nowTime - oldTime;
        	if (differ < 3000) {
        		return true;  
			}else {
				httpServletRequest.getSession().setAttribute("repeatData", nowUrlParams);  
		        httpServletRequest.getSession().setAttribute("repeatTime", nowTime);  
		        return false;
			}
        }  
        else//如果上次 url+数据 和本次url加数据不同，则不是重复提交  
        {  
            httpServletRequest.getSession().setAttribute("repeatData", nowUrlParams);  
            httpServletRequest.getSession().setAttribute("repeatTime", nowTime);  
            return false;  
        }  
          
    }  
}  


	/** 
     * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。
     * postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之后，也就是在Controller的方法调用之后执行，
     * 但是它会在DispatcherServlet进行视图的渲染之前执行，也就是说在这个方法中你可以对ModelAndView进行操作。
     * 这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用，这跟Struts2里面的拦截器的执行过程有点像， 
     * 只是Struts2里面的intercept方法中要手动的调用ActionInvocation的invoke方法，
     * Struts2中调用ActionInvocation的invoke方法就是调用下一个Interceptor或者是调用action，
     * 然后要在Interceptor之前调用的内容都写在调用invoke之前，要在Interceptor之后调用的内容都写在调用invoke方法之后。 
     */  
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub  
	}

	 /** 
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。
     * 该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行， 
     * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。 
     */  
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub  
	}
	
	/********************************  参考代码     ********************************************************************/
	/*如果需要注解进行限制拦截的话  使用一下自定义注解*/
//	/** 
//	 * 一个用户 相同url 同时提交 相同数据 验证 
//	 * @author Administrator 
//	 * 
//	 */  
////	@Target(ElementType.METHOD)  
////	@Retention(RetentionPolicy.RUNTIME)  
////	public @interface SameUrlData {  
////		// TODO Auto-generated method stub  
////	}  
	
//	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  
//         if (handler instanceof HandlerMethod) {  
//             HandlerMethod handlerMethod = (HandlerMethod) handler;  
//             Method method = handlerMethod.getMethod();  
//             SameUrlData annotation = method.getAnnotation(SameUrlData.class);  
//             if (annotation != null) {  
//                 if(repeatDataValidator(request))//如果重复相同数据  
//                     return false;  
//                 else   
//                     return true;  
//             }  
//             return true;  
//         } else {  
//             return super.preHandle(request, response, handler);  
//         }  
//     }  
	/********************************  参考代码     ********************************************************************/
}
