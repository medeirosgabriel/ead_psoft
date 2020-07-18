package com.ufcg.lab1.entities;

public class Discipline implements Comparable<Discipline> {
	
	private int id;
	private String name;
	private double grade;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public Discipline(int id, String name, double grade) {
		this.id = id;
		this.name = name;
		this.grade = grade;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public double getGrade() {
		return grade;
	}
	
	public void setGrade(double grade) {
		this.grade = grade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Discipline other = (Discipline) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int compareTo(Discipline d) {
		return -1 * (new Double(this.grade)).compareTo(d.getGrade());
	}
}
