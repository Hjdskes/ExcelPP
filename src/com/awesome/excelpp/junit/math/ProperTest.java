package com.awesome.excelpp.junit.math;

<<<<<<< HEAD
import static org.junit.Assert.*;

import org.junit.Test;

public class ProperTest {

	@Test
	public void test() {
		fail("Not yet implemented"); // TODO
	}

=======
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Proper;
import com.awesome.excelpp.math.exception.MathException;

public class ProperTest {
	@Test
	public void Proper() throws MathException {
		String expected = "Dit Is Een Test";
		String result = new Proper().getValue("dit is EEN test");
		assertEquals(expected, result);
	}
	
	@Test
	public void properNoSpaces() throws MathException {
		String expected = "Ditiseentest";
		String result = new Proper().getValue("ditisEENtest");
		assertEquals(expected, result);
	}

	@Test
	public void properOtherChars() throws MathException {
		String expected = "Dit!Is@Een-Moeilijke*Test";
		String result = new Proper().getValue("dit!is@een-moeilijke*test");
		assertEquals(expected, result);
	}

	@Test
	public void properNumbers() throws MathException {
		String expected = "Dit0Is1Een2Getallen3Test";
		String result = new Proper().getValue("dit0is1een2getallen3test");
		assertEquals(expected, result);
	}

	@Test(expected = MathException.class)
	public void properMulArgs() throws MathException {
		@SuppressWarnings("unused")
		String result = new Proper().getValue("ditiseentest", "testtesttest");
	}

	@Test
	public void properInt() throws MathException {
		String expected = "2";
		String result = new Proper().getValue(2);
		assertEquals(expected, result);
	}

	@Test
	public void properDouble() throws MathException {
		String expected = "2.0";
		String result = new Proper().getValue(2.0);
		assertEquals(expected, result);
	}

	@Test
	public void properBool() throws MathException {
		@SuppressWarnings("unused")
		String result = new Proper().getValue(true);
	}
>>>>>>> ea49f0f11a0bf3c406b69a4b7feb8c296d656993
}
