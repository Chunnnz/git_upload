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
		String urlOut = "c:\\Users\\Admin\\cars2_revised.csv";
		List<Map<String, Object>> carList = new ArrayList<>();

		String carHeader = null;

		try (InputStreamReader isr = new InputStreamReader(new FileInputStream(urlIn));) {
			BufferedReader br = new BufferedReader(isr);

			carHeader = br.readLine();
			String[] header = carHeader.split(",");

			String carLine;
			while ((carLine = br.readLine()) != null) {
				Object[] carData = carLine.split(",");
				Map<String, Object> carMap = new LinkedHashMap<>(); 

				for (int i = 0; i < header.length; i++) {
					carMap.put(header[i], carData[i]);
				}
				carList.add(carMap);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Collections.sort(carList, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				BigDecimal price1 = new BigDecimal((String) o1.get("Price"));
				BigDecimal price2 = new BigDecimal((String) o2.get("Price"));
				return price1.compareTo(price2) * (-1); 
			}
		});

		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < carList.size(); j++) {
			sb.append(carList.get(j)).append("\n");
		}

		Map<String, List<Map<String, Object>>> sortedMap = new TreeMap<>(); 
			String manuf = (String) manufMap.get("Manufacturer"); 
			List<Map<String, Object>> carList1 = sortedMap.get(manuf);
			if (carList1 == null) {
				carList1 = new ArrayList<>();
				sortedMap.put(manuf, carList1);
			}
			carList1.add(manufMap);
		}

		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringBuilder sb3 = new StringBuilder();

		BigDecimal ttlMPrice = BigDecimal.ZERO;
		BigDecimal ttlPrice = BigDecimal.ZERO;

		System.out.println("Manufacturer Type       Min.Price     Price");
		for (String key : sortedMap.keySet()) {

			BigDecimal subttlMPrice = BigDecimal.ZERO;
			BigDecimal subttlPrice = BigDecimal.ZERO;

			for (int k = 0; k < sortedMap.get(key).size(); k++) {
				Map<String, Object> getKey = sortedMap.get(key).get(k);
				BigDecimal price = new BigDecimal((String) getKey.get("Price"));
				BigDecimal mPrice = new BigDecimal((String) getKey.get("Min.Price"));

				subttlMPrice = mPrice.add(subttlMPrice);
				subttlPrice = price.add(subttlPrice);

				sb1.append(String.format("%-13s", getKey.get("Manufacturer")))
						.append(String.format("%-10s", getKey.get("Type")))
						.append(String.format("%10s", getKey.get("Min.Price")))
						.append(String.format("%10s", getKey.get("Price"))).append("\n");
			}
	
			System.out.print(sb1);
			sb1.setLength(0);

			sb2.append(String.format("%-20s", "小計")).append(String.format("\t%9s", subttlMPrice)).append(" ")
					.append(String.format("%9s", subttlPrice));
			System.out.println(sb2);
			sb2.setLength(0);

			ttlMPrice = subttlMPrice.add(ttlMPrice);
			ttlPrice = subttlPrice.add(ttlPrice);
		}

		sb3.append(String.format("%-20s", "總計")).append(String.format("\t%9s", ttlMPrice)).append(" ")
				.append(String.format("%9s", ttlPrice));
		System.out.println(sb3);

		try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(urlOut), "UTF-8");) {
			BufferedWriter bw = new BufferedWriter(osw);

			bw.write(String.join(",", carList.get(0).keySet()));
			bw.newLine();

			for (Map<String, Object> carMap : carList) {
				StringBuilder sbCVS = new StringBuilder();
				for (Object value : carMap.values()) {
					if (sbCVS.length() > 0) {
						sbCVS.append(",");
					}
					sbCVS.append(value.toString());
				}
				bw.write(sbCVS.toString());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
