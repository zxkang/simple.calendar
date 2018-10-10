package ${basePackage}.calendar;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ${classNameUpperCamel}Test extends TestCase {

	${classNameUpperCamel} ${classNameLowerCamel};

	@Override
	protected void setUp() throws Exception {
		${classNameLowerCamel} = new ${classNameUpperCamel}();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGenerator() {
		//输入不能为空
		String line = "";
		String result = ${classNameLowerCamel}.generator(line);
		Assert.assertEquals(${classNameUpperCamel}.ERROR_NULL, result);

		//月份1~12，不在此范围时返回格式异常
		String line1 = "2018 asd13";
		String result1 = ${classNameLowerCamel}.generator(line1);
		Assert.assertEquals(${classNameUpperCamel}.ERROR_BAD_FORMAT, result1);

		//支持的范围为Int型的正数部分(1~2147483647)，输入公元前年份，返回格式异常
		String line2 = "-2018 12";
		String result2 = ${classNameLowerCamel}.generator(line2);
		Assert.assertEquals(${classNameUpperCamel}.ERROR_BAD_FORMAT, result2);
		String line3 = "0 12";
		String result3 = ${classNameLowerCamel}.generator(line3);
		Assert.assertEquals(${classNameUpperCamel}.ERROR_BAD_FORMAT, result3);		

		//边界值
		String line4 = "1 12";
		String result4 = ${classNameLowerCamel}.generator(line4);
		Assert.assertNotSame("", result4);
		Assert.assertNotSame(${classNameUpperCamel}.ERROR_NULL, result4);
		Assert.assertNotSame(${classNameUpperCamel}.ERROR_BAD_FORMAT, result4);
		//边界值
		String line5 = "2147483647 12";
		String result5 = ${classNameLowerCamel}.generator(line5);
		Assert.assertNotSame("", result5);
		Assert.assertNotSame(${classNameUpperCamel}.ERROR_NULL, result5);
		Assert.assertNotSame(${classNameUpperCamel}.ERROR_BAD_FORMAT, result5);
		
		//正常输入时，返回内容无异常，且不为空
		String lineLast = "2018 10";
		String resultLast = ${classNameLowerCamel}.generator(lineLast);
		Assert.assertNotSame("", resultLast);
		Assert.assertNotSame(${classNameUpperCamel}.ERROR_NULL, resultLast);
		Assert.assertNotSame(${classNameUpperCamel}.ERROR_BAD_FORMAT, resultLast);
	}
	       
	public static void main(String[] args)      {  	
		junit.textui.TestRunner.run(${classNameUpperCamel}Test.class);      
	}	
}