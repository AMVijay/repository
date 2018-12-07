package vijay.poc.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CustomInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {	
//		System.out.println("preHandle Response Content :: " + response.getWriter().toString());		
		return true;
	}
	

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		request.getReader().lines().forEach(System.out::println);	
//		System.out.println("postHandle Response Content :: " + response.getWriter().toString());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		request.getReader().lines().forEach(System.out::println);	
//		System.out.println("afterCompletion Response Content :: " + response.getWriter().toString());
	}

}
