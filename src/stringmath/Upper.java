package stringmath;

import com.awesome.excelpp.math.Formula;

public class Upper extends Formula{
	public String upper() {
		String string = "";
		string.toUpperCase();
		return string;
	}

	@Override
	public double getValue(double... numbers) {
		// TODO Auto-generated method stub
		return 0;
	}
}
