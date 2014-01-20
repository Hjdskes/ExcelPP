package com.awesome.excelpp.math;

<<<<<<< HEAD
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
=======
/**
 * The CountIf function counts the number of cells within a range that meet a single criterion that you specify.
 * <p>For example, you can count all the cells that start with a certain letter,
 * or you can count all the cells that contain a number that is larger or smaller than a number you specify.
 * For example, suppose you have a spreadsheet that contains a list of tasks in column A,
 * and the first name of the person assigned to each task in column B.
 * You can use the CountIf function to count how many times a person's name appears in column B and,
 * in that way, determine how many tasks are assigned to that person. For example: =CountIf(B2:B25,"Nancy");
 * </br>Syntax: =CountIf(range, criteria);</p>
 * @author Team Awesome
 */
public class CountIf {
>>>>>>> 4d5fe631fc3b104b7164735a865d930a5f537078

}
