package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;
import com.awesome.excelpp.parser.Parser;
import com.awesome.excelpp.parser.exception.ParserException;
import com.awesome.excelpp.parser.exception.RecursionException;

/**
 * You use the SumIf function to sum the values in a range that meet criteria that you specify.
 * <p>For example, suppose that in a column that contains numbers,
 * you want to sum only the values that are larger than 5. You can use the following formula: =SumIf(B2:B25,">5")
 * In this example, the criteria is applied the same values that are being summed.
 * If you want, you can apply the criteria to one range and sum the corresponding values in a different range.
 * For example, the formula =SumIf(B2:B5, "John", C2:C5) sums only the values in the range C2:C5,
 * where the corresponding cells in the range B2:B5 equal "John".
 * </br>Syntax: =SumIf(range, criteria, [sum_range])</p>
 * @author Team Awesome
 */
public class SumIf extends Formula {
	@Override
	public Double getValue(Object... args) throws MathException, ParserException, RecursionException {
		Double sum = 0.0;
		String cond = null;
		int index = 0; //The start index of sum_range
		boolean sumRange = true;//Indicates whether we have a sum_range specified or not.
		
		for(int i = 0; i < args.length; i++){
			if(args[i] instanceof String){
				cond = (String) args[i]; // in the end. only the last String will be assigned to "cond"
				index = i + 1;
				if(i == args.length - 1){
					sumRange = false;
				}
			}
		}
		
		//Is dit nodig? De Lexer haalt quotes volgens mij weg
		String condition = ((cond.charAt(0) == '"') ? cond.substring(1, cond.length()-1) : cond ); //Trim quotation marks
		
		if((condition.charAt(0) == '<') || (condition.charAt(0) == '>')){
			for(int i = 0; i < index - 1; i++){
				Parser parse = new Parser("=" + args[i].toString() + condition);
				parse.toPostfix();
				if(((Boolean)parse.eval()) == true){
					if(sumRange){						
						sum += (Double) args[i + index];
					} else {
						sum += (Double) args[i];
					}
				}

			}
			return sum;
		}else{
			for(int i = 0; i < index - 1; i++){
				if(args[i].toString().equals(condition)){
					if(sumRange){						
						sum += (Double) args[i + index];
					} else {
						throw new MathException();
					}
				}
			}
			return sum;
		}
		
	}
}
