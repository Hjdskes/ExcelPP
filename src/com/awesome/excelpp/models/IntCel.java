package com.awesome.excelpp.models;

public class IntCel extends Cel {
	private int content;
	
	public IntCel(int col, int rw, int cont) {
		super(col, rw);
		this.content = cont;
	}
}
