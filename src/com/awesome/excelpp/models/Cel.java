package com.awesome.excelpp.models;

import java.util.Observable;

public abstract class Cel extends Observable {
	private int column;
	private int row;
	
	public Cel(int rw, int col) {
		this.column = col;
		this.row = rw;
	}
}
