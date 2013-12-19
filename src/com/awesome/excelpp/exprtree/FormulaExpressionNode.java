package com.awesome.excelpp.exprtree;

import java.lang.reflect.Constructor;

import com.awesome.excelpp.math.Formula;

public class FormulaExpressionNode implements ExpressionNode {
	private ExpressionNode nodes[];
	private Formula formula;
	
	public FormulaExpressionNode(String formulaName, ExpressionNode ... nodes) {
		this.nodes = nodes;
		
		String packageName = "com.awesome.excelpp.math";
		String formulaNameFull = packageName + '.' + formulaName;
		
		try {
			Class<?> formulaClass = Class.forName(formulaNameFull);
	        Constructor<?> opConstructor = formulaClass.getConstructor();
	        this.formula = (Formula)opConstructor.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getType() {
		return ExpressionNode.FORMULA_NODE;
	}

	public double getValue() {
		//TODO: Should we change all formulas to nodes?
		double numbers[] = new double[nodes.length];
		for (int i = 0; i < nodes.length; i++) {
			numbers[i] = nodes[i].getValue();
		}
		
		return formula.getValue(numbers);
	}
}
