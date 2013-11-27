package com.awesome.excelpp.models;

public class Cel {
	private String content; // =2+2
	
	public Cel(String content) {
		this.content = content;
	}
	
	public Cel() {
		this("");
	}
	
	public String getFormule() {
		return content;
	}
	
	public String getValue() {
		return content; // 4
	}
	
	public void setFormule(String content) {
		this.content = content;
	}
}
