package com.awesome.excelpp.exprtree;

import static org.junit.Assert.*;

import org.junit.Test;

public class FormulaExpressionNodeTest {

	@Test
	public void test() {
		ExpressionNode node = new FormulaExpressionNode("Add", new ConstantExpressionNode("20"), new ConstantExpressionNode("5"));
		System.out.println(node.getValue());
	}

}
