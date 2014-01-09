package com.awesome.excelpp.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.awesome.excelpp.junit.math.MathTest;
import com.awesome.excelpp.junit.models.CellTest;
import com.awesome.excelpp.junit.models.SpreadSheetTest;
import com.awesome.excelpp.junit.parser.LexerTest;
import com.awesome.excelpp.junit.parser.ParserTest;

@RunWith(Suite.class)
@SuiteClasses({ MainTest.class,
				CellTest.class,
				SpreadSheetTest.class,
				LexerTest.class,
				ParserTest.class,
				MathTest.class })
public class AllTests {

}
