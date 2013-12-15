package com.awesome.excelpp.exprtree;

public class ConstantExpressionNode implements ExpressionNode {
	private double value;
	
	public ConstantExpressionNode(String value) {
		this.value = Double.valueOf(value);
	}
	
	public double getValue() {
		return value;
	}
	
	public int getType() {
		return ExpressionNode.CONSTANT_NODE;
	}
}