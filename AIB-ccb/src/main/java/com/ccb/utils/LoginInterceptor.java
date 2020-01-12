package com.ccb.utils;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
* @ClassName: LoginInterceptor
* @Description: 登陆拦截器
* @author tx
* @date 2020年01月12日
*
 */
public class LoginInterceptor implements HandlerInterceptor  {
	
	// private final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	 
	// @Autowired 
	 //private ISysLogService iSysLogService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object arg2) throws Exception {		
		//获取请求的URL
        String url = request.getRequestURI();
        
        //URL:login.jsp是公开的;除了login.jsp是可以公开访问的，其它的URL都进行拦截控制  
        if(url.indexOf("/AIB-wms/login/WebLogin") >= 0){
            return true;
        }
        
        
        
        //获取Session
       /** HttpSession session = request.getSession();
        if((String)session.getAttribute("userCode") != null){
        	SysOpLog log = null;
            try {
                log = LoggerUtil.getLog(request,(HandlerMethod) arg2);
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage());
            }
            request.setAttribute(LoggerUtil.LOG_OPERATE, log);
            return true;
        }*/
        
        response.setContentType ("text/html;charset=utf-8");
        response.setHeader ("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();  
        
        StringBuffer outstr = new StringBuffer();
        outstr.append("<script>");
        outstr.append("try {");
        outstr.append("parent.showTimeOutMsg();");
        outstr.append("} catch (e) {");
        outstr.append("location.href = '/AIB-wms/';");
        outstr.append("}");
        outstr.append("</script>");
        
        out.println(outstr.toString());  
       
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,	HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
				
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		 //返回视图时，插入操作日志
		/**SysOpLog log = (SysOpLog) request.getAttribute(LoggerUtil.LOG_OPERATE);
        if (log == null) {
            logger.warn("日志信息为空");
        } else {         	
        	iSysLogService.insert(log);           
        }	*/
		
	}

}
