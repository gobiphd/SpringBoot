package com.kavi.user.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String sessionId = WebUtils.getSessionId(request);
		System.out.println("HttpSession Id : "+sessionId);/** Session Id Created */
		String urlMapping = request.getRequestURI();
		System.out.println("URL Mapping : "+urlMapping);
		if (!urlMapping.endsWith("login") && !urlMapping.endsWith("loginAction")) {
			Object loggedUserId = WebUtils.getSessionAttribute(request, "LOGGEDUSERID");
			if (ObjectUtils.isEmpty(loggedUserId)) {
				String loginForm = request.getContextPath() + "/base/login";
				response.sendRedirect(loginForm);
				return false;
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle Method!!");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
		System.out.println("afterCompletion Method!!!");
	}
}
