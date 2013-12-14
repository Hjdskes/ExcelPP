package com.awesome.excelpp.exprtree;

public class AdditionExpressionNode implements ExpressionNode {
	private ExpressionNode a, b;
	
	public AdditionExpressionNode(ExpressionNode a, ExpressionNode b) {
		this.a = a;
		this.b = b;
	}

	public int getType() {
		return ExpressionNode.ADDITION_NODE;
	}

	public double getValue() {
		return a.getValue() + b.getValue();
	}
}