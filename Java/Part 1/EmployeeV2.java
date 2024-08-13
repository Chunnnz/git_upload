package com.cathaybk.practice.nt50353.b;

public abstract class Employee implements IWork {

	private String name;
	private String department;
	private int salary;

	public Employee() {
	}

	public Employee(String name, String dep, int sal) {
		this.name = name;
		this.department = dep;
		this.salary = sal;
	}

	@Override
	public void printinfo() {
		System.out.print(String.format("薪資單\n姓名: %-6s工作部門: %-6s\n月薪: %-6d\n", name, department, salary));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String dep) {
		this.department = dep;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int sal) {
		this.salary = sal;
	}

	protected abstract int getPayment();

}
