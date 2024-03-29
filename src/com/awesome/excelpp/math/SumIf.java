package com.awesome.excelpp.math;

import com.awesome.excelpp.parser.Parser;
import com.awesome.excelpp.parser.exception.MathException;
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
 * </br>Syntax: =SumIf(range, "criteria", [sum_range])</p>
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
			try {
				args[i] = getDouble(args[i]);
			} catch (MathException e) {
				cond = (String) args[i];
				index = i + 1;
				if(index == args.length){
					sumRange = false;
				}
			}
		}

		if(((index-1)*2 != args.length - 1) && sumRange){
			throw new MathException();
		}
		
		if((cond.charAt(0) == '<') || (cond.charAt(0) == '>') || (cond.charAt(0) == '=')){
			if(cond.charAt(0) == '=' && cond.charAt(1) != '='){
				cond = "=" + cond;
			}
			for(int i = 0; i < index - 1; i++){
				Parser parse = new Parser("=" + args[i].toString() + cond);
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
				if(args[i].toString().equals(cond)){
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
