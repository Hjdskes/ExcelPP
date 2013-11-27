package com.awesome.excelpp.models;

import java.util.Observable;

public abstract class Cel extends Observable {
	private String content; // =2+2
	
	public Cel(String content) {
		this.content = content;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFormule() {
		return content;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getValue() {
		return content; // 4
	}
	
	/**
	 * 
	 * @param content
	 */
	public void setValue(String content) {
		this.content = content;
	}
	
}
