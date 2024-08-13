package com.cathaybk.practice.nt50353.b;

public class Supervisor extends Employee {

	private int payment;

	public Supervisor(String name, String dep, int sal) {
		super(name, dep, sal);
		this.payment = getSalary();
	}

	@Override
	public void printinfo() {
		super.printinfo();
		System.out.println("總計: " + payment);
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int pay) {
		this.payment = pay;
	}
}
/