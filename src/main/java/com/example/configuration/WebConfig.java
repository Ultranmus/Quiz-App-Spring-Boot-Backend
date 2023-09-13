package com.example.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import com.example.authentication.CustomAuthenticationFilter;


@Configuration
public class WebConfig {

    @Bean
	FilterRegistrationBean<CustomAuthenticationFilter> loginRegistrationBean() {
	  FilterRegistrationBean<CustomAuthenticationFilter> filterRegistrationBean = new FilterRegistrationBean<>();
	  filterRegistrationBean.setFilter(new CustomAuthenticationFilter());
	  filterRegistrationBean.addUrlPatterns("/*");
	  filterRegistrationBean.setOrder(1);
	  return filterRegistrationBean;
	}

	@Bean
	  FilterRegistrationBean<CorsFilter> corsRegistrationBean() {
	  FilterRegistrationBean<CorsFilter> filterRegistrationBean = new FilterRegistrationBean<>();
	  filterRegistrationBean.setFilter(corsFilter());
	  filterRegistrationBean.addUrlPatterns("/*");
	  filterRegistrationBean.setOrder(0);
	  return filterRegistrationBean;
	}


	 @Bean
     CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true); // Allow cookies and authentication headers in requests
        corsConfiguration.addAllowedOriginPattern("*"); // Allow requests from any origin
        corsConfiguration.addAllowedHeader("*"); // Allow all headers
        corsConfiguration.addAllowedMethod("*"); // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
        
    }

}
    
