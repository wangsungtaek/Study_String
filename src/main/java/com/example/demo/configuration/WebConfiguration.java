package com.example.demo.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.example.demo.configuration.servlet.handler.BaseHandlerInterceptor;
import com.example.demo.mvc.domain.BaseCodeLabelEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.net.MediaType;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setBasename("classpath:/messages/message");
		source.setDefaultEncoding("UTF-8");
		source.setCacheSeconds(60);
		source.setDefaultLocale(Locale.KOREAN);
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}
	
//	@Bean
//	public BaseHandlerInterceptor baseHandlerInterceptor() {
//		return new BaseHandlerInterceptor();
//	}
//	
//	@Bean
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(baseHandlerInterceptor());
//	}
	
	@Bean
	public ObjectMapper obejctMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(BaseCodeLabelEnum.class, new BaseCodeLabelEnumJsonSerializer());
		objectMapper.registerModule(simpleModule);
		return objectMapper;
	}
	
	@Bean
	public MappingJackson2JsonView mappingJackson2JsonView() {
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setContentType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE);
		jsonView.setObjectMapper(obejctMapper());
		return jsonView;
	}
	
//	@Bean
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(BaseHandlerInterceptor);
//	}

	
}
