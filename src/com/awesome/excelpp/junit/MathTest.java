package com.awesome.excelpp.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.awesome.excelpp.junit.math.QuotientTest;
import com.awesome.excelpp.junit.math.SignTest;
import com.awesome.excelpp.junit.math.SumIfTest;
import com.awesome.excelpp.junit.math.SumTest;
import com.awesome.excelpp.junit.math.AndTest;
import com.awesome.excelpp.junit.math.AverageTest;
import com.awesome.excelpp.junit.math.CountTest;
import com.awesome.excelpp.junit.math.IfTest;
import com.awesome.excelpp.junit.math.IntTest;
import com.awesome.excelpp.junit.math.IsEvenTest;
import com.awesome.excelpp.junit.math.IsNumberTest;
import com.awesome.excelpp.junit.math.IsLogicalTest;
import com.awesome.excelpp.junit.math.LowerTest;
import com.awesome.excelpp.junit.math.MaxTest;
import com.awesome.excelpp.junit.math.MedianTest;
import com.awesome.excelpp.junit.math.MinTest;
import com.awesome.excelpp.junit.math.ModTest;
import com.awesome.excelpp.junit.math.NotTest;
import com.awesome.excelpp.junit.math.OrTest;
import com.awesome.excelpp.junit.math.PowerTest;
import com.awesome.excelpp.junit.math.ProductTest;
import com.awesome.excelpp.junit.math.ProperTest;
import com.awesome.excelpp.junit.math.RounddownTest;
import com.awesome.excelpp.junit.math.RoundupTest;
import com.awesome.excelpp.junit.math.SqrtTest;
import com.awesome.excelpp.junit.math.SubtractTest;
import com.awesome.excelpp.junit.math.UpperTest;

@RunWith(Suite.class)
@SuiteClasses({	AndTest.class,
				AverageTest.class,
				CountTest.class,
				IfTest.class,
				IntTest.class,
				IsEvenTest.class,
				IsLogicalTest.class,
				IsNumberTest.class,
				LowerTest.class,
				MaxTest.class,
				MedianTest.class,
				MinTest.class,
				ModTest.class,
				NotTest.class,
				OrTest.class,
				PowerTest.class,
				ProductTest.class,
				ProperTest.class,
				QuotientTest.class,
				RounddownTest.class,
				RoundupTest.class,
				SignTest.class,
				SqrtTest.class,
				SubtractTest.class,
				SumTest.class,
				SumIfTest.class,
				UpperTest.class })

public class MathTest {
}