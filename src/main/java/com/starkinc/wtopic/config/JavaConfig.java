package com.starkinc.wtopic.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import com.starkinc.wtopic.interceptor.HeaderRequestInterceptor;

@Configuration
public class JavaConfig {
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}
	
	@Bean
	public List<ClientHttpRequestInterceptor> clientHttpRequestInterceptorList(){
		List<ClientHttpRequestInterceptor> headerInterceptor = new ArrayList<ClientHttpRequestInterceptor>();
		headerInterceptor.add(new HeaderRequestInterceptor());
		return headerInterceptor;
	}

}
