package com.awesome.excelpp.exprtree;

import static org.junit.Assert.*;

import org.junit.Test;

public class FormulaNodeTest {

	@Test
	public void test() {
		ExpressionNode node = new FormulaNode("Add", new ConstantNode("20"), new ConstantNode("5"));
		System.out.println(node.getValue());
	}

}
