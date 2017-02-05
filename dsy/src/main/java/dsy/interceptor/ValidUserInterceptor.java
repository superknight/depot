package dsy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 拦截非法登录
 * @author fangwk
 * @date 2016-12-12 下午6:08:31
 */
public class ValidUserInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired 
	private ResourceBundleMessageSource messageSource;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		HttpSession session = request.getSession(false);
		if (session == null) {
			request.setAttribute("msg",  messageSource.getMessage("noLogin", null, request.getLocale()));
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return false;
		}else {
			return true;
		}
		
	}

}
