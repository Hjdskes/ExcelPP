package com.awesome.excelpp.exprtree;

public class MultiplicationExpressionNode implements ExpressionNode {
	private ExpressionNode a, b;
	boolean multiply;
	
	public MultiplicationExpressionNode(ExpressionNode a, ExpressionNode b, boolean multiply) {
		this.a = a;
		this.b = b;
		this.multiply = multiply;
	}
	
	public MultiplicationExpressionNode(ExpressionNode a, ExpressionNode b) {
		this(a, b, true);
	}

	public int getType() {
		return ExpressionNode.ADDITION_NODE;
	}

	public double getValue() {
		if (multiply)
			return a.getValue() * b.getValue();
		return a.getValue() / b.getValue();
	}
}