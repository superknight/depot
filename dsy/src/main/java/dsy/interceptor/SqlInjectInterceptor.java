package dsy.interceptor;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import util.ValidationUtil;
/**
 * 拦截SQL注入
 */
public class SqlInjectInterceptor extends HandlerInterceptorAdapter {

	@Autowired 
	private ResourceBundleMessageSource messageSource;

	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		@SuppressWarnings("unchecked")
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String[] values = request.getParameterValues(name);
			for (int i = 0; i < values.length; i++) {
				
				System.err.println(name+":"+values[i]);
				if (ValidationUtil.isSqlInject(values[i].toLowerCase())) {
					request.setAttribute("msg", messageSource.getMessage("sqlInject", null, request.getLocale()));
					request.getRequestDispatcher("/error.jsp").forward(request,response);
					return false;
				}
			}
		}
		return true;
	}

}
