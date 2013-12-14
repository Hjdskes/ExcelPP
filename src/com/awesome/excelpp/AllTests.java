package com.awesome.excelpp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.awesome.excelpp.models.CellTest;
import com.awesome.excelpp.models.SpreadSheetTest;
import com.awesome.excelpp.scanner.LexerTest;

@RunWith(Suite.class)
@SuiteClasses({ MainTest.class,
				CellTest.class,
				LexerTest.class,
				SpreadSheetTest.class })
public class AllTests {

}
