package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Product;
import com.awesome.excelpp.parser.exception.MathException;

public class ProductTest {
	@Test
	public void Product() throws MathException {
		assertEquals(6.0, new Product().getValue(2.0,3.0), .001);
	}
}
