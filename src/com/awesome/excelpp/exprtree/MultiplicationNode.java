package com.awesome.excelpp.exprtree;

public class MultiplicationNode implements ExpressionNode {
	private ExpressionNode a, b;
	private boolean multiply;
	
	public MultiplicationNode(ExpressionNode a, ExpressionNode b, boolean multiply) {
		this.a = a;
		this.b = b;
		this.multiply = multiply;
	}
	
	public MultiplicationNode(ExpressionNode a, ExpressionNode b) {
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
