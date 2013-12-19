package com.awesome.excelpp.exprtree;

public class ConstantNode implements ExpressionNode {
	private double value;
	
	public ConstantNode(String value) {
		this.value = Double.valueOf(value);
	}
	
	public double getValue() {
		return value;
	}
	
	public int getType() {
		return ExpressionNode.CONSTANT_NODE;
	}
}
