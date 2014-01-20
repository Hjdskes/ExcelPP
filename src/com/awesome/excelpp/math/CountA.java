package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * The CountA function counts the number of cells that are not empty in a range.
 * <p>Syntax: =CountA(value1, [value2], ...);</p>
 * @author Team Awesome
 */
public class CountA extends Formula {
		@Override
		public Integer getValue(Object... args) throws MathException {
			 int count = 0;
			 	for(int i=0; i < args.length; i++) {
			 		if(args[i] instanceof Double) {
			 			if((double) args[i] !=  0.0) {
			 				count += 1;
			 			}
			 		}
			 		else if(args[i] instanceof String) {
			 			if((String) args[i] !=  "") {
			 				count += 1;
			 			}
			 		}
			 		
			 		else if(args[i] instanceof Integer) {
			 			if((int) args[i] !=  0) {
			 				count += 1;
			 			}
			 		}
			 		
			 		else if(args[i] instanceof Boolean) {
			 			if((Boolean) args[i] == true || (Boolean) args[i] == false ) {
			 				count += 1;
			 			}
			 		}
			}
			return count;
		}

}
