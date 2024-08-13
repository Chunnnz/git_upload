package com.cathaybk.practice.nt50353.b;

public class Sales extends Employee {

	private int bonus;
	private int payment;


	public Sales(String name, String dep, int sal, int sales) {
		super(name, dep, sal);
		this.bonus = (int) (sales * 0.05);
	}

	@Override
	public void printinfo() {
		int j = 2;
		super.printinfo();
		System.out.println("業績獎金: " + bonus);
		for (int i = 0; i < j; i++) {
			payment = getSalary() + getBonus();
		}
		System.out.println("總計: " + payment);
	}

	public int getBonus() {
		return bonus;
	}

	public void getBonus(int bon) {
		this.bonus = bon;
	}

	public int getPayment() {
		return payment;
	}

	public void getPayment(int pay) {
		this.payment = pay;
	}
}
