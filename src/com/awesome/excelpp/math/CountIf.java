package com.awesome.excelpp.math;

import com.awesome.excelpp.parser.exception.MathException;

/**
 * The CountIf function counts the number of cells within a range that meet a single criterion that you specify.
 * <p>For example, you can count all the cells that start with a certain letter,
 * or you can count all the cells that contain a number that is larger or smaller than a number you specify.
 * For example, suppose you have a spreadsheet that contains a list of tasks in column A,
 * and the first name of the person assigned to each task in column B.
 * You can use the CountIf function to count how many times a person's name appears in column B and,
 * in that way, determine how many tasks are assigned to that person. For example: =CountIf(B2:B25,"Nancy")
 * </br>Syntax: =CountIf(range, criteria)</p>
 * @author Team Awesome
 */
public class CountIf extends Formula {
	@Override
	public Integer getValue(Object... args) throws MathException {
		if(args.length < 2)
			throw new MathException();

		int count = 0;
		Object lastArg = args[args.length - 1];
		if(lastArg instanceof String) {
			String arg = (String)lastArg;
			int length = arg.length();
			if(length == 1) { //alleen de eerste letter vergelijken
				for(int i = 0; i < args.length - 1; i++) {
					if(getString(args[i]).startsWith(arg))
						count++;
				}
			} else { //de hele string vergelijken
				for(int i = 0; i < args.length - 1; i++) {
					if(getString(args[i]).equals(arg))
						count++;
				}
			}
		} else if (lastArg instanceof Boolean) {
			Boolean arg = (Boolean)lastArg;
			for(int i = 0; i < args.length - 1; i++) {
				if(getBoolean(args[i]) == arg)
					count++;
			}
		} else if (lastArg instanceof Integer) {
			Integer arg = (Integer)lastArg;
			for(int i = 0; i < args.length - 1; i++) {
				if(getInteger(args[i]) == arg)
					count++;
			}
		} else if (lastArg instanceof Double) {
			Double arg = (Double)lastArg;
			for(int i = 0; i < args.length - 1; i++) {
				if(getDouble(args[i]) == arg)
					count++;
			}
		}

		return count;
	}
}