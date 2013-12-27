package com.awesome.excelpp.parser;

import java.util.LinkedList;

import com.awesome.excelpp.models.SpreadSheet;

public class ParserTest {
	public static void main(String[] args){
		Parser test = new Parser("=2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)", null);
		double startTime = System.currentTimeMillis();
		double result = test.eval(test.toPostfix());
		double endTime = System.currentTimeMillis();
		double totalTime = endTime - startTime;
		System.out.println(result + " (" + totalTime + " ms)");
		
//		SpreadSheet testSheet = new SpreadSheet();
//		testSheet.setValueAt("=3+1", 0, 2);
//		Parser test = new Parser("=2*2+C1", testSheet);
//		System.out.println(test.eval(test.toPostfix()));
	}
}
