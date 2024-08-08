package com.cathaybk.practice.nt50353.b;

public class Supervisor extends Employee {

	private int payment;


	public Supervisor(String name, String dep, int sal) {
		super(name, dep, sal);
	}

	@Override
	public void printinfo() {
		int j = 2;
		super.printinfo();
		for (int i = 0; i < j; i++) {
			payment = getSalary();
		}
		System.out.println("總計: " + payment);
	}

	public int getPayment() {
		return payment;
	}

	public void getPayment(int pay) {
		this.payment = pay;
	}
}
