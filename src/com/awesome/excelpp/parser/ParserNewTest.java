package com.awesome.excelpp.parser;

public class ParserNewTest {
	public static void main(String[] args){
		ParserNew test = new ParserNew("=3+7/(4*5-6)");
		
		while(!test.output.isEmpty()){
			System.out.println(test.output.getFirst().data);
		}
	}
}
