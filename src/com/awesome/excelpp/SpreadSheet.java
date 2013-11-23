package com.awesome.excelpp;

import com.awesome.excelpp.xml.*;

public class SpreadSheet {
	
	private enum Parser {
		JAVAX, W3C
	}
	
	public static void main(String[] args) {
		
		Parser whichParser = Parser.JAVAX;	//Verander deze line om een andere parser uit te proberen.
		
		switch(whichParser){
			case JAVAX:
				JavaxParser javaxParse = new JavaxParser();
				break;
			case W3C:
				W3CParser w3cParse = new W3CParser();
				break;
			default:
				System.out.println("Welke parser wil je uitproberen?");
		}
	}
}
