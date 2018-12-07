package vijay.poc.interceptors;

import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

public class CustomWebRequestInterceptor implements WebRequestInterceptor {

	@Override
	public void preHandle(WebRequest request) throws Exception {
//		System.out.println("In preHandle");
//		
//		String testParameter = request.getParameter("test");
//		if(testParameter != null) {
//			System.out.println("Test Parameter :: " + testParameter);
//		}
	}

	@Override
	public void postHandle(WebRequest request, ModelMap model) throws Exception {
//		System.out.println("In postHandle");
	}

	@Override
	public void afterCompletion(WebRequest request, Exception ex) throws Exception {
//		System.out.println("In afterCompletion");
	}

}
