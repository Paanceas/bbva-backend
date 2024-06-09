package co.com.bbva.backend.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import co.com.bbva.backend.util.ApiKeyValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			System.out.println("1 - preHandle() : Before sending request to the Controller");
			System.out.println("Method Type: " + request.getMethod());
			System.out.println("Request URL: " + request.getRequestURI());
			String apiKey = request.getHeader("API-KEY");
			ApiKeyValidator.validate(apiKey);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("Unauthorized: Invalid API Key");
			return false;
		}
		return true;
	}
}
