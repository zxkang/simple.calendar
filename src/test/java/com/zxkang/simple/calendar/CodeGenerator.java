package com.zxkang.simple.calendar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Configuration;

public class CodeGenerator {

	private static final String PROJECT_PATH = System.getProperty("user.dir");// 项目在硬盘上的基础路径
	private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/test/resources";// 模板位置

	private static final String TESTCASE_PATH = "/src/test/java"; // java文件路径

	/**
	 * 基础测试包名称
	 */
	public static final String BASE_PACKAGE = "com.zxkang.simple";

	public static final String CALENDAR_PACKAGE = BASE_PACKAGE + ".calendar";

	private static final String PACKAGE_PATH_CALENDAR = packageConvertPath(CALENDAR_PACKAGE);// 生成的测试类的存放路径

	private static String packageConvertPath(String packageName) {
		return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
	}

	private static Configuration getConfiguration() throws IOException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		return cfg;
	}

	public static void genTestCase(String className) {
		try {
			Configuration cfg = getConfiguration();

			Map<String, Object> data = new HashMap<String, Object>();
			String classNameUpperCamel = classNameConvertUpperCamel(className);
			data.put("classNameUpperCamel", classNameUpperCamel);
			data.put("classNameLowerCamel", classNameConvertLowerCamel(className));
			data.put("basePackage", BASE_PACKAGE);

			File file = new File(
					PROJECT_PATH + TESTCASE_PATH + PACKAGE_PATH_CALENDAR + classNameUpperCamel + "Test.java");
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			cfg.getTemplate("testcase.ftl").process(data, new FileWriter(file));

			System.out.println(classNameUpperCamel + "Test.java 生成成功");
		} catch (Exception e) {
			throw new RuntimeException("生成TestCase失败", e);
		}

	}

	private static String classNameConvertLowerCamel(String className) {
		StringBuilder result = new StringBuilder();
		if (className != null && className.length() > 0) {
			className = className.toLowerCase();// 兼容使用大写的表名
			boolean flag = false;
			for (int i = 0; i < className.length(); i++) {
				char ch = className.charAt(i);
				if ("_".charAt(0) == ch) {
					flag = true;
				} else {
					if (flag) {
						result.append(Character.toUpperCase(ch));
						flag = false;
					} else {
						result.append(ch);
					}
				}
			}
		}
		return result.toString();
	}

	private static String classNameConvertUpperCamel(String className) {
		String camel = classNameConvertLowerCamel(className);
		return camel.substring(0, 1).toUpperCase() + camel.substring(1);

	}

	public static void main(String[] args) {
		genTestCase("calendar_generator");
	}
}
