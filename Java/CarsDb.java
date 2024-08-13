package com.cathaybk.practice.nt50353.b;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CarsDb {
	public static final String CONN_URL = "jdbc:oracle:thin:@//localhost:1521/XE";
	public static final String QUERYALL_CARS_SQL = "select * from STUDENT.CARS";
	public static final String QUERY_CARS_SQL = "select * from STUDENT.CARS where MANUFACTURER=? and TYPE=?";
	public static final String INSERT_CARS_SQL = "insert into STUDENT.CARS (MANUFACTURER, TYPE, MIN_PRICE, PRICE) values (?, ?, ?, ?)";
	public static final String UPDATE_CARS_SQL = "update STUDENT.CARS set MIN_PRICE=?, PRICE=? where MANUFACTURER=? and TYPE=?";
	public static final String DELETE_CARS_SQL_STRING = "delete from STUDENT.CARS where MANUFACTURER=? and TYPE=?";

	public static void main(String[] args) {
		try (Connection conn = DriverManager.getConnection(CONN_URL, "student", "student123456");
				PreparedStatement pstmt = conn.prepareStatement(QUERYALL_CARS_SQL);) {

			ResultSet rs = pstmt.executeQuery();
			List<Map<String, String>> carList = new ArrayList<>();

			while (rs.next()) {
				Map<String, String> carMap = new HashMap<>();
				carMap.put("MANUFACTURER", rs.getString("MANUFACTURER"));
				carMap.put("TYPE", rs.getString("TYPE"));
				carMap.put("MIN_PRICE", rs.getString("MIN_PRICE"));
				carMap.put("PRICE", rs.getString("PRICE"));
				carList.add(carMap);
			}

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < carList.size(); i++) {
				BigDecimal price = new BigDecimal(carList.get(i).get("PRICE"));
				BigDecimal minPrice = new BigDecimal(carList.get(i).get("MIN_PRICE"));
				sb.append("製造商：").append(carList.get(i).get("MANUFACTURER")).append("，型號：")
						.append(carList.get(i).get("TYPE")).append("，底價：$").append(minPrice.toPlainString())
						.append("，售價：$").append(price.toPlainString()).append("\n");
			}
			System.out.println(sb.toString());
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try (Scanner input = new Scanner(System.in)) {
			System.out.println("請選擇以下指令輸入: select、insert、update、delete");
			String req = input.next();
			display(req);
		}
	}

	private static void display(String req) {

		if (req.equals("select")) {
			doQuery();
		} else if (req.equals("insert")) {
			doInsert();
		} else if (req.equals("update")) {
			doUpdate();
		} else if (req.equals("delete")) {
			doDelete();
		} else {
			System.out.println("不適用");
		}
	}

	private static void doQuery() {
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("請輸入查詢的製造商: ");
			String manufQ = input.next();
			System.out.println("請輸入查詢的類型: ");
			String typeQ = input.next();
			display1(manufQ, typeQ);
		}
	}

	private static void display1(String manufQ, String typeQ) {

		try (Connection conn = DriverManager.getConnection(CONN_URL, "student", "student123456");
				PreparedStatement pstmt = conn.prepareStatement(QUERY_CARS_SQL);) {

			pstmt.setString(1, manufQ);
			pstmt.setString(2, typeQ);
			ResultSet rs = pstmt.executeQuery();

			StringBuilder sb = new StringBuilder();
			sb.append("查詢結果： ").append("\n");
			if (rs.next()) {
				BigDecimal price = new BigDecimal(rs.getString("PRICE"));
				BigDecimal minPrice = new BigDecimal(rs.getString("MIN_PRICE"));

				sb.append("製造商：").append(rs.getString("MANUFACTURER")).append("，型號：").append(rs.getString("TYPE"))
						.append("，底價：$").append(minPrice.toPlainString()).append("，售價：$").append(price.toPlainString())
						.append("\n");
				System.out.println(sb.toString());
				sb.setLength(0);
			} else {
				System.out.println("查詢失敗");
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void doInsert() {
		Map<String, Object> mapI = new HashMap<>();
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("請輸入新增的製造商: ");
			String manufI = input.next();
			mapI.put("MANUFACTURER", manufI);

			System.out.println("請輸入新增的類型: ");
			String typeI = input.next();
			mapI.put("TYPE", typeI);

			System.out.println("請輸入底價: ");
			int mpriceI = input.nextInt();
			mapI.put("MIN_PRICE", mpriceI);

			System.out.println("請輸入售價: ");
			int priceI = input.nextInt();
			mapI.put("PRICE", priceI);

			display2(mapI);
		}
	}

	private static void display2(Map<String, Object> mapI) {
		try (Connection conn = DriverManager.getConnection(CONN_URL, "student", "student123456");
				PreparedStatement pstmt = conn.prepareStatement(INSERT_CARS_SQL);) {
			try {
				conn.setAutoCommit(false);
				pstmt.setString(1, (String) mapI.get("MANUFACTURER"));
				pstmt.setString(2, (String) mapI.get("TYPE"));
				pstmt.setInt(3, (Integer) mapI.get("MIN_PRICE"));
				pstmt.setInt(4, (Integer) mapI.get("PRICE"));
				pstmt.executeUpdate();
				conn.commit();
				System.out.println("新增成功");
			} catch (Exception e) {
				System.out.println("新增失敗，原因：" + e.getMessage());
				try {
					conn.rollback();
				} catch (SQLException sqle) {
					System.out.println("rollback 新增失敗，原因：" + sqle.getMessage());
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	private static void doUpdate() {
		Map<String, Object> mapU = new HashMap<>();
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("請輸入欲更新的製造商: ");
			String manufU = input.next();
			mapU.put("MANUFACTURER", manufU);

			System.out.println("請輸入欲更新的類型: ");
			String typeU = input.next();
			mapU.put("TYPE", typeU);

			System.out.println("請輸入更新後的底價: ");
			int mpriceU = input.nextInt();
			mapU.put("MIN_PRICE", mpriceU);

			System.out.println("請輸入更新後的售價: ");
			int priceU = input.nextInt();
			mapU.put("PRICE", priceU);

			display3(mapU);
		}
	}

	private static void display3(Map<String, Object> mapU) {
		try (Connection conn = DriverManager.getConnection(CONN_URL, "student", "student123456");) {
			try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_CARS_SQL);) {
				conn.setAutoCommit(false);
				pstmt.setString(1, (String) mapU.get("MANUFACTURER"));
				pstmt.setString(2, (String) mapU.get("TYPE"));
				pstmt.setInt(3, (Integer) mapU.get("MIN_PRICE"));
				pstmt.setInt(4, (Integer) mapU.get("PRICE"));
				pstmt.executeUpdate();
				conn.commit();
				System.out.println("更新成功");
			} catch (Exception e) {
				try {
					conn.rollback();
					System.out.println("更新失敗");
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
				e.printStackTrace();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	private static void doDelete() {
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("請輸入製造商: ");
			String manufD = input.next();
			System.out.println("請輸入製類型: ");
			String typeD = input.next();
			display4(manufD, typeD);
		}

	}

	private static void display4(String manufD, String typeD) {
		try (Connection conn = DriverManager.getConnection(CONN_URL, "student", "student123456");
				PreparedStatement pstmt = conn.prepareStatement(DELETE_CARS_SQL_STRING);) {
			try {
				conn.setAutoCommit(false);
				pstmt.setString(1, manufD);
				pstmt.setString(2, typeD);
				pstmt.executeUpdate();
				conn.commit();
				System.out.println("刪除成功");
			} catch (Exception e) {
				System.out.println("刪除失敗，原因：" + e.getMessage());
				try {
					conn.rollback();
				} catch (SQLException sqle) {
					System.out.println("rollback 刪除失敗，原因：" + sqle.getMessage());
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
