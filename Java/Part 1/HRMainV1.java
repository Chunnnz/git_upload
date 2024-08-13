package com.cathaybk.practice.nt50353.b;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class HRMain {

	public static void main(String[] args) throws IOException {
		List<Employee> empList = new ArrayList<>();
		empList.add(new Sales("張志誠", "信用卡部", 35000, 6000));
		empList.add(new Sales("林大鈞", "保代部", 38000, 4000));
		empList.add(new Supervisor("李中白", "資訊部", 65000));
		empList.add(new Supervisor("林小中", "理財部", 80000));

		for (Employee emp : empList) {
			emp.printinfo();

			String url = "c:\\Users\\Admin\\output.csv"; // 用記事本開

			try {
				OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(url), "UTF-8");
				BufferedWriter bw = new BufferedWriter(out);
//				for (Employee value : empList) {
//					bw.write(value.toString());
//					bw.newLine();

				for (int i = 0; i < empList.size(); i++) {
					bw.write(empList.get(i).getName().toString());
					bw.write(", ");
					bw.write(String.valueOf(empList.get(i).getPayment()));
					// BufferedWriter makes the integer mean the relative words
					bw.newLine();
//					System.out.println(empList.get(i).getPayment());
				}
				bw.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
