package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

public class CountIf extends Formula {

	@Override
	public String getValue(Object... args) throws MathException {
		 int count = 0;
		 	for(int i=1; i < args.length; i++) {
		 		if(args[i] instanceof Double) {
		 			if((double) args[i] !=  0.0) {
		 				if(args[0] == args[i]){
			 				count += 1;
			 				System.out.println(args[i]);
		 				}
		 			}
		 		}
		 		else if(args[i] instanceof String) {
		 			if((String) args[i] !=  "") {
		 				if(args[0].equals(args[i])){
			 				count += 1;
			 				System.out.println(args[i]);
		 				}
		 			}
		 		}
		 		
		 		else if(args[i] instanceof Integer) {
		 			if((int) args[i] !=  0) {
		 				if(args[0] == args[i]){
		 				count += 1;
		 				System.out.println(args[i]);
		 				}
		 			}
		 		}
		}
		return String.valueOf(count);
	}

}
