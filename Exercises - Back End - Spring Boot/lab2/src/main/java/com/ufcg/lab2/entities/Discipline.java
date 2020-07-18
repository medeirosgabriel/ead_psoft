package com.ufcg.lab2.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Discipline {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	
	private double grade;
	
	private String coments;
	
	private int likes;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
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
	
	public String getComents() {
		return coments;
	}
	
	public void setComentarios(String coments) {
		this.coments = coments;
	}
	
	public int getLikes() {
		return likes;
	}
	
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	public Discipline() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Discipline(@JsonProperty("nome") String nome) {
		this.name = nome;
		this.grade = 0.0;
		this.coments = "";
		this.likes = 0;
	}

	public Discipline(String name, float grade, String coments, int likes) {
		super();
		this.name = name;
		this.grade = grade;
		this.coments = coments;
		this.likes = likes;
	}
}
