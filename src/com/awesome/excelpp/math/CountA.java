package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * The CountA function counts the number of cells that are not empty in a range.
 * <p>Syntax: =CountA(value1, [value2], ...);</p>
 * @author Team Awesome
 */
public class CountA extends Formula {
		@Override
		public String getValue(Object... args) throws MathException {
			 int count = 0;
			 	for(int i=0; i < args.length; i++) {
			 		if(args[i] instanceof Double) {
			 			if((double) args[i] !=  0.0) {
			 				count += 1;
			 				System.out.println(args[i]);
			 			}
			 		}
			 		else if(args[i] instanceof String) {
			 			if((String) args[i] !=  "") {
			 				count += 1;
			 				System.out.println(args[i]);
			 			}
			 		}
			 		
			 		else if(args[i] instanceof Integer) {
			 			if((int) args[i] !=  0) {
			 				count += 1;
			 				System.out.println(args[i]);
			 			}
			 		}
			}
			return String.valueOf(count);
		}

}
