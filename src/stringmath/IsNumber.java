package stringmath;


public class IsNumber extends StringFormula {
	public String getValue(String... vars) {
		String res = "false";
		try {
			Double.parseDouble(vars[0]);
			res = "true";
		} catch(NumberFormatException e) {
			
		}
		return res;
	}
}
