package com.awesome.excelpp.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.awesome.excelpp.junit.math.AddTest;
import com.awesome.excelpp.junit.math.AverageTest;
import com.awesome.excelpp.junit.math.IntTest;
import com.awesome.excelpp.junit.math.IsEvenTest;
import com.awesome.excelpp.junit.math.IsNumberTest;
import com.awesome.excelpp.junit.math.LowerTest;
import com.awesome.excelpp.junit.math.MaxTest;
import com.awesome.excelpp.junit.math.MinTest;
import com.awesome.excelpp.junit.math.PowerTest;
import com.awesome.excelpp.junit.math.RounddownTest;
import com.awesome.excelpp.junit.math.RoundupTest;
import com.awesome.excelpp.junit.math.SqrtTest;
import com.awesome.excelpp.junit.math.UpperTest;

@RunWith(Suite.class)
@SuiteClasses({	AddTest.class,
				AverageTest.class,
				IntTest.class,
				IsEvenTest.class,
				IsNumberTest.class,
				LowerTest.class,
				MaxTest.class,
				MinTest.class,
				PowerTest.class,
				RounddownTest.class,
				RoundupTest.class,
				SqrtTest.class,
				UpperTest.class })

public class MathTest {
}