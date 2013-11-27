package com.awesome.excelpp.models;

import java.util.Observable;

public abstract class Cel extends Observable {
	private String content; // =2+2
	
	public Cel(int row, int column, String content) {
		this.content = content;
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
