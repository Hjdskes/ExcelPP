package com.awesome.excelpp.exprtree;

public class MultiplicationExpressionNode implements ExpressionNode {
	private ExpressionNode a, b;
	
	public MultiplicationExpressionNode(ExpressionNode a, ExpressionNode b) {
		this.a = a;
		this.b = b;
	}

	public int getType() {
		return ExpressionNode.ADDITION_NODE;
	}

	public double getValue() {
		return a.getValue() * b.getValue();
	}
}