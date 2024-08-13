package com.cathaybk.practice.nt50353.b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Cars {

	public static void main(String[] args) {
		String urlIn = "c:\\Users\\Admin\\Java評量_第6題cars.csv";
		String urlOut = "c:\\Users\\Admin\\cars2.csv";
		List<Map<String, String>> carList = new ArrayList<>();

		String carHeader = null;

		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(urlIn));
			BufferedReader br = new BufferedReader(isr);
			{

				carHeader = br.readLine();
				String[] header = carHeader.split(",");

				String carLine;
				while ((carLine = br.readLine()) != null) {
					String[] carData = carLine.split(",");

					Map<String, String> carMap = new LinkedHashMap<>();
					for (int i = 0; i < header.length; i++) {
						carMap.put(header[i], carData[i]);
					}
					carList.add(carMap);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Collections.sort(carList, new Comparator<Map<String, String>>() {
			@Override
			public int compare(Map<String, String> o1, Map<String, String> o2) {
				return o1.get("Price").toString().compareTo(o2.get("Price").toString());
			}
		});
		Collections.reverse(carList);
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < carList.size(); j++) {
			sb.append(carList.get(j)).append("\n");
		}

		Map<String, List<Map<String, String>>> sortedMap = new TreeMap<>();

		for (Map<String, String> manufMap : carList) {
			String manuf = manufMap.get("Manufacturer");
			List<Map<String, String>> map = sortedMap.get(manuf);
			if (map == null) {
				map = new ArrayList<>();
				sortedMap.put(manuf, map);
			}
			map.add(manufMap);
		}

		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringBuilder sb3 = new StringBuilder();

		BigDecimal ttlMPrice = new BigDecimal("0");
		BigDecimal ttlPrice = new BigDecimal("0");

		System.out.println("Manufacturer Type       Min.Price     Price");
		for (String key : sortedMap.keySet()) {

			BigDecimal subttlMPrice = new BigDecimal("0");
			BigDecimal subttlPrice = new BigDecimal("0");

			// car data
			for (int k = 0; k < sortedMap.get(key).size(); k++) {
				BigDecimal price = new BigDecimal(sortedMap.get(key).get(k).get("Price"));
				BigDecimal mPrice = new BigDecimal(sortedMap.get(key).get(k).get("Min.Price"));

				subttlMPrice = mPrice.add(subttlMPrice);
				subttlPrice = price.add(subttlPrice);

				sb1.append(String.format("%-13s", sortedMap.get(key).get(k).get("Manufacturer")))
						.append(String.format("%-10s", sortedMap.get(key).get(k).get("Type")))
						.append(String.format("%10s", sortedMap.get(key).get(k).get("Min.Price")))
						.append(String.format("%10s", sortedMap.get(key).get(k).get("Price"))).append("\n");
			}
			System.out.print(sb1);
			sb1.setLength(0); // reset sb
			sb2.append(String.format("%-12s", "小計")).append(String.format("%-10s", " "))
					.append(String.format("%10s", subttlMPrice)).append(" ").append(String.format("%9s", subttlPrice));
			System.out.println(sb2);
			sb2.setLength(0); // reset

			ttlMPrice = subttlMPrice.add(ttlMPrice);
			ttlPrice = subttlPrice.add(ttlPrice);
		}
		sb3.append(String.format("%-12s", "總計")).append(String.format("%-10s", " "))
				.append(String.format("%10s", ttlMPrice)).append(" ").append(String.format("%9s", ttlPrice));
		System.out.println(sb3);

		try {
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(urlOut), "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);

			bw.write(String.join(",", carList.get(0).keySet()));
			bw.newLine();

			for (Map<String, String> car : carList) {
				bw.write(String.join(",", car.values()));
				bw.newLine();
			}
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
