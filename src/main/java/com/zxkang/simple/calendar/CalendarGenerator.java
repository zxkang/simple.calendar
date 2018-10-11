package com.zxkang.simple.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class CalendarGenerator {

	private static final String REG = "^(\\d+)[^\\d]+((0?[1-9])|(1[012]))$";
	private static final String PROMPTS = "输入年月(年和月用非数字隔开:如2018.10)(什么都不输入直接退出)";
	public static final String ERROR_NULL = "输入不能为空!";
	public static final String ERROR_BAD_FORMAT = "输入格式不符合要求！";
	private static String TITLE = "日\t一\t二\t三\t四\t五\t六\r\n";

	public CalendarGenerator() {
		super();
	}

	public String generator(String line) {
		StringBuilder sb = new StringBuilder();
		if ("".equals(line)) {
			return ERROR_NULL;
		}
		if (!line.matches(REG)) {
			return ERROR_BAD_FORMAT;
		}
		int year = Integer.parseInt(line.replaceAll(REG, "$1"));
		if (year == 0) {
			return ERROR_BAD_FORMAT;
		}
		int month = Integer.parseInt(line.replaceAll(REG, "$2"));
		sb.append(TITLE);
		Calendar calendar = Calendar.getInstance();
		// 这个月的1号是星期几
		calendar.set(year, month - 1, 1);
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		int start = Calendar.SUNDAY;
		if (year != 1) {
			calendar.add(Calendar.DATE, -day + start);
		} else {
			calendar.add(Calendar.DATE, 0);
		}
		while (start < day) {
			sb.append(" \t");
			start++;
		}
		calendar.set(year, month - 1, 1);
		Date now = calendar.getTime();
		calendar.set(year, month, 1);
		Date next = calendar.getTime();
		for (Date cur = now; cur.before(next);) {
			calendar.setTime(cur);
			int x = calendar.get(Calendar.DATE);
			String tmp = x < 10 ? " " + x : x + "";
			sb.append(tmp + "\t");
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				sb.append("\r\n");
			}
			calendar.add(Calendar.DATE, 1);
			cur = calendar.getTime();
		}
		calendar.add(Calendar.DATE, -1);
		int to = calendar.get(Calendar.DAY_OF_WEEK);
		int end = Calendar.SATURDAY;
		while (to < end) {
			sb.append(" \t");
			to++;
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		CalendarGenerator cg = new CalendarGenerator();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println(PROMPTS);
			String line = scanner.nextLine().trim();
			String result = cg.generator(line);
			// scanner.close();
			System.out.println(result);
		}
	}
}

