package com.cathaybk.practice.nt50353.b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomLotto {

	public static void main(String[] args) {
		int i = 6;
		int result = 0;
		Random random = new Random();

		List<Integer> loList = new ArrayList<>();
		while (loList.size() < i) {
			result = 1 + random.nextInt(48);
			if (loList.contains(result)) {
				continue;
			}
			loList.add(result);
		}
		System.out.print("排序前: ");
		for (int j = 0; j < i; j++) {
			System.out.printf("%2d ", loList.get(j));
		}

		System.out.println();

		System.out.print("排序後: ");
		Collections.sort(loList);
		for (int k = 0; k < i; k++) {
			System.out.printf("%2d ", loList.get(k));
		}
	}
}
