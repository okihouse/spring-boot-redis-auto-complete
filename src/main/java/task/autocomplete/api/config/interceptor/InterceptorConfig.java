package task.autocomplete.api.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import task.autocomplete.api.interceptor.SpringInterceptor;

@Configuration
@EnableAutoConfiguration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private SpringInterceptor springInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(springInterceptor);
	}
	
}
