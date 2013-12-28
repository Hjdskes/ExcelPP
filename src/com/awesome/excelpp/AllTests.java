package com.awesome.excelpp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.awesome.excelpp.models.CellTest;
import com.awesome.excelpp.models.SpreadSheetTest;
import com.awesome.excelpp.parser.LexerTest;
import com.awesome.excelpp.parser.ParserTest;

@RunWith(Suite.class)
@SuiteClasses({ MainTest.class,
				CellTest.class,
				SpreadSheetTest.class,
				LexerTest.class,
				ParserTest.class })
public class AllTests {

}
