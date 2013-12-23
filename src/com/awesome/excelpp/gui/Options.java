package com.awesome.excelpp.gui;

public class Options {
	private int newKeybinding;
	
	public Options(){
		newKeybinding = 78;
	}
	
	public int getNewKeybinding(){
		return newKeybinding;
	}
	
	public void setNewKeybinding(int key){
		newKeybinding = key;
	}
}
