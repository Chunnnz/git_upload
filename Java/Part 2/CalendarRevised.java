package com.cathaybk.practice.nt50353.b;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

public class Calendar {

	public static void main(String[] args) {
		try (Scanner input = new Scanner(System.in)) {
			int year = LocalDate.now().getYear();
			System.out.print("輸入介於 1－12 的整數m: ");
			int month = input.nextInt();
			System.out.println("       " + year + "年" + month + "月");
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
		LocalDate firstDay = LocalDate.of(year, month, 1);
		int ttlDays = ym.lengthOfMonth();
		int firstWeekDay = firstDay.getDayOfWeek().getValue();
		int DayOne = (firstWeekDay % 7);
		
		for (int j = 0; j < DayOne; j++) {
			System.out.printf("%3s", " ");
		}

		for (int k = 1; k <= ttlDays; k++) {
			System.out.printf("%3d", k);
			if ((DayOne + k) % 7 == 0) {
				System.out.println();
			}
		}

		if ((DayOne + ttlDays) % 7 != 0) {
			System.out.println();
		}
	}
}
