package com.example.demo.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.configuration.GlobalConfig;
import com.example.demo.configuration.http.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "파일API")
public class FileController {

	@Autowired
	private GlobalConfig config;
	
	@GetMapping
	@ApiOperation(value = "업로드", notes = "")
	public BaseResponse<Boolean> save() {
		String uploadFilePath = config.getUploadFilePath();
		return new BaseResponse<Boolean>(true);
	}
}
