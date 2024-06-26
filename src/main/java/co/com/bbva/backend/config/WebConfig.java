package co.com.bbva.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import co.com.bbva.backend.interceptor.ApiKeyInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private ApiKeyInterceptor apiKeyInterceptor;

	@Value("${cors.allowed.origins}")
	private String allowedOrigins;

	@Value("${cors.allowed.methods}")
	private String allowedMethods;

	@Value("${cors.allowed.headers}")
	private String allowedHeaders;

	@Value("${cors.allow.credentials}")
	private boolean allowCredentials;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(apiKeyInterceptor).addPathPatterns("/api/**");
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins(allowedOrigins.split(","))
						.allowedMethods(allowedMethods.split(",")).allowedHeaders(allowedHeaders.split(","))
						.allowCredentials(allowCredentials);
			}
		};
	}
}
