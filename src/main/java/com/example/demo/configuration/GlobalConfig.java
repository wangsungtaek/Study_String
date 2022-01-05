package com.example.demo.configuration;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class GlobalConfig {

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	private String uploadFilePath;
	private String schedulerCronExample1;
	
	private boolean local;
	
	@PostConstruct
	public void init() {
		String[] activeProfiles = context.getEnvironment().getActiveProfiles();
		String activeProfile = "local";
		if(ObjectUtils.isNotEmpty(activeProfile)) {
			activeProfile = activeProfiles[0];
		}
		String resourcePath = String.format("classpath:globals/global-%s.properties", activeProfiles);
		try {
			Resource resource = resourceLoader.getResource(resourcePath);
			Properties properties =  PropertiesLoaderUtils.loadProperties(resource);
			
			this.uploadFilePath = properties.getProperty("uploadFile.path");
			this.local = properties.equals("local");
			
			
		} catch (Exception e) {
			System.out.println("e : " + e);
		}
		
	}
	
	public String getUploadFilePath() {
		return uploadFilePath;
	}
	
	public String getSchedulerCronExample1() {
		return schedulerCronExample1;
	}
}
