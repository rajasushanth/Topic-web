package com.starkinc.wtopic.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.web.client.RestTemplate;

import com.starkinc.wtopic.constants.Constants;
import com.starkinc.wtopic.exception.CustomResponseErrorHandler;
import com.starkinc.wtopic.interceptor.HeaderRequestInterceptor;

/**
 * @author RajaSushanth
 *
 */
@Configuration
public class JavaConfig {
	
	@Bean
	public RestTemplate restTemplate(){
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		restTemplate.setInterceptors(clientHttpRequestInterceptorList());
		restTemplate.setErrorHandler(customResponseErrorHandler());
		//restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		return restTemplate;
	}
	
	@Bean
	public List<ClientHttpRequestInterceptor> clientHttpRequestInterceptorList(){
		List<ClientHttpRequestInterceptor> headerInterceptor = new ArrayList<ClientHttpRequestInterceptor>();
		headerInterceptor.add(new HeaderRequestInterceptor());
		return headerInterceptor;
	}
	
	@Bean
	public TextEncryptor textEncryptor(@Value(Constants.SALT) String salt, @Value(Constants.SECRECT_KEY) String key){
		return Encryptors.text(key, salt);
	}
	
	@Bean
	public CustomResponseErrorHandler customResponseErrorHandler(){
		return new CustomResponseErrorHandler();
	}

}
