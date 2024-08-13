package com.cathaybk.practice.nt50353.b;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

public class Calendar {

	public static void main(String[] args) {
		try (Scanner input = new Scanner(System.in)) {
			int year = 2024;
			System.out.print("輸入介於 1－12 的整數m: ");
			int month = input.nextInt();
			System.out.println(year + "年" + month + "月");
			display(year, month);
		}
	}

	static void display(int year, int month) {
		System.out.println("----------------------");
		String[] dayArr = { "日", "一", "二", "三", "四", "五", "六" };
		int i = 0;
		while (i < dayArr.length) {
			System.out.printf("%-3s", dayArr[i]);
			i++;
		}
		System.out.println("\n======================");

		YearMonth ym = YearMonth.of(year, month);
		int day = LocalDate.of(year, month, 1).getDayOfWeek().getValue();
		int week = 1;
		if (day != 7)
			for (int j = 1; j <= day; j++, week++) {
				System.out.printf("%3s", "");
			}

		for (int k = 1; k <= ym.getMonth().length(ym.isLeapYear()); k++, week++) {

			System.out.printf("%3d", k);
			if (week % 7 == 0) {
				System.out.println();
			}
		}
	}
}
