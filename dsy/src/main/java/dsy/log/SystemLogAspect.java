package dsy.log;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import dsy.module.SecLog;
import dsy.module.SecUser;
import dsy.service.log.LogService;
import util.DateUtil;

@Aspect
@Component
public class SystemLogAspect {

	@Autowired
	private LogService logService;
	
	@Pointcut("@annotation(dsy.log.SystemLog)")
	public void log(){
		
	}
	
	@After("log()")
	public void afterExec(JoinPoint joinPoint,HttpServletRequest request) {
		MethodSignature ms = (MethodSignature) joinPoint.getSignature();
		Method method = ms.getMethod();		
		try {
			// 获取session
			HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession(false);
			if ( session != null && session.getAttribute("user") != null) {
				System.out.println("--log start--");
				String ip = ",ip:"+getIpAddr(request);
				String operator=((SecUser) session.getAttribute("user")).getUsername();
				String operate = method.getAnnotation(SystemLog.class).log()+ip;
				this.logService.saveToDB(new SecLog(operator, operate, DateUtil.getDateTime2(new Date())));
				System.out.println("--log end--");
			}else {
				System.out.println("--log fail by session expire--");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("--log fail by Exception--");
		}
		
		
	}
	
	
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getRemoteAddr();
		}
		return ip;
		}
}
