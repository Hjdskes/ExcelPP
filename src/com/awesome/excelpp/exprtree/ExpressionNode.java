package com.awesome.excelpp.exprtree;

public interface ExpressionNode {
	public static final int VARIABLE_NODE = 1;
	public static final int CONSTANT_NODE = 2;
	public static final int ADDITION_NODE = 3;
	public static final int MULTIPLICATION_NODE = 4;
	public static final int EXPONENTIATION_NODE = 5;
	public static final int FUNCTION_NODE = 6;
	public int getType();
	public double getValue();
}
