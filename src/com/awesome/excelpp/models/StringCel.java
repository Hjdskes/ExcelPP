package com.awesome.excelpp.models;

public class StringCel extends Cel {
	private String content;
	
	public StringCel(int col, int rw, String cont) {
		super(col, rw);
		this.content = cont;
	}
}
