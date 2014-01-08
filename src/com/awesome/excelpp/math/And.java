package com.awesome.excelpp.math;

import java.util.ArrayList;

public class And {
	public boolean getValue(ArrayList<Logic> arguments) {
		boolean res = true;
		for(int i = 0; i< arguments.size()-1; i++) {
			switch(arguments.get(i).opp) {
			case 0:
				res = ((LogicInt) arguments.get(i)).largerThan();
				break;
			case 1:
				res = ((LogicInt) arguments.get(i)).smallerThan();
				break;
			case 2:
				 res = arguments.get(i).equals();
				break;
			}
			if(!res) {
				return false;
			}
		}
		return true;
	}
}
