package com.example.demo.mvc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/*
 * MySQL 페이지 요청 및 계산된 값 
 */

@Data
public class MySQLPageRequest {

	private int page;
	private int size;
	
	@JsonIgnore
	@ApiModelProperty(hidden = true)
	private int limit;
	
	@JsonIgnore
	@ApiModelProperty(hidden = true)
	private int offset;
	
	public MySQLPageRequest(int page, int size, int limit, int offset) {
		this.page = page;
		this.size = size;
		this.limit = limit;
		this.offset = offset;
	}
}
