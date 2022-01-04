package com.example.demo.web;

import java.lang.annotation.Native;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.mybatis.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.example.demo.mvc.domain.MySQLPageRequest;

/*
 * MySQL 쿼리 페이징 LIMIT, OFFSET 값을 자동계산하여 MysqlPageRequest 클래스 담아서 컨트롤러에서 받을 수 있게함.
 */

public class MySQLPageRequestHandleMethodArgumentResolver implements HandlerMethodArgumentResolver {
	
	private static final String DEFUALT_PARAMETER_PAGE = "page";
	private static final String DEFUALT_PARAMETER_SIZE = "size";
	private static final int DEFAULT_SIZE = 20;

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		// 현재 페이지
		int page = NumberUtils.toInt(request.getParameter(DEFUALT_PARAMETER_PAGE), 1);
		// 리스트 갯수
		int offset = NumberUtils.toInt(request.getParameter(DEFUALT_PARAMETER_SIZE), DEFAULT_SIZE);
		// 시작지점
		int limit = (offset * page) - offset;
		System.out.println("pagae : " + page);
		System.out.println("limit : " + limit);
		System.out.println("offset : " + offset);
		return new MySQLPageRequest(page, offset, limit, offset);
	}
	
	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		return MySQLPageRequest.class.isAssignableFrom(methodParameter.getParameterAnnotation(null));
	}


	
}
