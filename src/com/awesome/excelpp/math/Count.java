package com.awesome.excelpp.math;

/**
 * The Count function counts the number of cells that contain numbers and counts numbers within the list of arguments.
 * <p>Use the Count function to get the number of entries in a number field that is in a range or array of numbers.
 * </br>Syntax: =Count(value1, [value2], ...);</p>
 * @author Team Awesome
 */
public class Count extends Formula {
	@Override
	public Integer getValue(Object... args) {
		return args.length;
	}
}
