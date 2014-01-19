package com.awesome.excelpp.math;

/**
 * The count function counts the number of cells that contain numbers, and counts numbers within the list of arguments.
 * <p>Use the COUNT function to get the number of entries in a number field that is in a range or array of numbers.</p>
 * @author Team Awesome
 */
public class Count extends Formula {
	@Override
	public Integer getValue(Object... args) {
		/* double count = 0;
		for(int i=0; i < args.length; i++) {
			count = i;
		}*/
		return args.length;
	}
	
}
