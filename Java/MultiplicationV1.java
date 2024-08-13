package com.cathaybk.practice.nt50353.b;

public class Multiplication {

	public static void main(String[] args) {

		for (int i = 1; i <= 9; i++) {
			for (int j = 2; j <= 9; j++) {
				int mul = i * j;
				System.out.print(j + "*" + i + "=");
				System.out.printf("%2d ", mul);
			}
			System.out.println();
		}
	}

}
