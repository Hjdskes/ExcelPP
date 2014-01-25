package com.awesome.excelpp.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.awesome.excelpp.junit.math.AddTest;
import com.awesome.excelpp.junit.math.AndTest;
import com.awesome.excelpp.junit.math.AverageTest;
import com.awesome.excelpp.junit.math.CountTest;
import com.awesome.excelpp.junit.math.IntTest;
import com.awesome.excelpp.junit.math.IsEvenTest;
import com.awesome.excelpp.junit.math.IsNumberTest;
import com.awesome.excelpp.junit.math.LowerTest;
import com.awesome.excelpp.junit.math.MaxTest;
import com.awesome.excelpp.junit.math.MedianTest;
import com.awesome.excelpp.junit.math.MinTest;
import com.awesome.excelpp.junit.math.ModTest;
import com.awesome.excelpp.junit.math.NotTest;
import com.awesome.excelpp.junit.math.PowerTest;
import com.awesome.excelpp.junit.math.ProductTest;
import com.awesome.excelpp.junit.math.RounddownTest;
import com.awesome.excelpp.junit.math.RoundupTest;
import com.awesome.excelpp.junit.math.SqrtTest;
import com.awesome.excelpp.junit.math.SubtractTest;
import com.awesome.excelpp.junit.math.UpperTest;

@RunWith(Suite.class)
@SuiteClasses({	AddTest.class,
				AndTest.class,
				AverageTest.class,
				CountTest.class,
				IntTest.class,
				IsEvenTest.class,
				IsNumberTest.class,
				LowerTest.class,
				MaxTest.class,
				MedianTest.class,
				MinTest.class,
				ModTest.class,
				PowerTest.class,
				ProductTest.class,
				RounddownTest.class,
				RoundupTest.class,
				SqrtTest.class,
				SubtractTest.class,
				UpperTest.class,
				NotTest.class,
				CountTest.class })

public class MathTest {
}