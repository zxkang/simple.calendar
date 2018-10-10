package com.zxkang.simple.calendar;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CalendarGeneratorManualTest extends TestCase {

	CalendarGenerator cg;

	@Override
	protected void setUp() throws Exception {
		cg = new CalendarGenerator();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGenerator() {
		//输入不能为空
		String line = "";
		String result = cg.generator(line);
		Assert.assertEquals(CalendarGenerator.ERROR_NULL, result);

		//月份1~12，不在此范围时返回格式异常
		String line1 = "2018 asd13";
		String result1 = cg.generator(line1);
		Assert.assertEquals(CalendarGenerator.ERROR_BAD_FORMAT, result1);

		//支持的范围为Int型的正数部分(1~2147483647)，输入公元前年份，返回格式异常
		String line2 = "-2018 12";
		String result2 = cg.generator(line2);
		Assert.assertEquals(CalendarGenerator.ERROR_BAD_FORMAT, result2);
		String line3 = "0 12";
		String result3 = cg.generator(line3);
		Assert.assertEquals(CalendarGenerator.ERROR_BAD_FORMAT, result3);		

		//边界值
		String line4 = "1 12";
		String result4 = cg.generator(line4);
		Assert.assertNotSame("", result4);
		Assert.assertNotSame(CalendarGenerator.ERROR_NULL, result4);
		Assert.assertNotSame(CalendarGenerator.ERROR_BAD_FORMAT, result4);
		//边界值
		String line5 = "2147483647 12";
		String result5 = cg.generator(line5);
		Assert.assertNotSame("", result5);
		Assert.assertNotSame(CalendarGenerator.ERROR_NULL, result5);
		Assert.assertNotSame(CalendarGenerator.ERROR_BAD_FORMAT, result5);
		
		//正常输入时，返回内容无异常，且不为空
		String lineLast = "2018 10";
		String resultLast = cg.generator(lineLast);
		Assert.assertNotSame("", resultLast);
		Assert.assertNotSame(CalendarGenerator.ERROR_NULL, resultLast);
		Assert.assertNotSame(CalendarGenerator.ERROR_BAD_FORMAT, resultLast);
	}
	       
	public static void main(String[] args)      {  	
		junit.textui.TestRunner.run(CalendarGeneratorManualTest.class);      
	}	
}
