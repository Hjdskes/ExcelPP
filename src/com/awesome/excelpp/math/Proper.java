package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Capitalizes the first letter in a text string and any other letters in text that follow any character other than a letter.
 * <p>Converts all other letters to lowercase letters.
 * </br>Syntax: =Proper(text)</p> 
 * @author Team Awesome
 */
public class Proper extends Formula {
	@Override
	public String getValue(Object... args) throws MathException {
		if(args.length != 1)
			throw new MathException();

		String res = "";
		String arg = getString(args[0]);
		if(arg.length() == 0)
			return res;

		String[] words = arg.split("(?<=[^a-zA-Z])");
		for (int i = 0; i < words.length; i++) {
			if(words[i].length() != 0)
				res += words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
		}

		return res;
	}
}
