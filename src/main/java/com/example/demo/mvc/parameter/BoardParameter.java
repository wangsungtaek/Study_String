package com.example.demo.mvc.parameter;

import lombok.Data;

@Data
public class BoardParameter {

	private int boardSeq;
	private String title;
	private String contents;
	
	public BoardParameter() {
		
	}
	
	public BoardParameter(int count, String title, String contents) {
		this.boardSeq = count;
		this.title = title;
		this.contents = contents;
	}
}
