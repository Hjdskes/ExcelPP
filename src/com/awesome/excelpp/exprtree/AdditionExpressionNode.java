package com.awesome.excelpp.exprtree;

public class AdditionExpressionNode implements ExpressionNode {
	private ExpressionNode a, b;
	boolean addition;
	
	public AdditionExpressionNode(ExpressionNode a, ExpressionNode b, boolean addition) {
		this.a = a;
		this.b = b;
		this.addition = addition;
	}
	
	public AdditionExpressionNode(ExpressionNode a, ExpressionNode b) {
		this(a, b, true);
	}

	public int getType() {
		return ExpressionNode.ADDITION_NODE;
	}

	public double getValue() {
		if (addition)
			return a.getValue() + b.getValue();
		return a.getValue() - b.getValue();
	}
}