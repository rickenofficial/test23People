package com.rickenofficial.app.entity;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course implements Serializable {
	private static final long serialVersionUID = 3327923455996711047L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 50, unique = true)
	private int id;

	@Column
	@NotNull
	@NotEmpty(message = "This field cannot be empty")
	@Length(min = 2, max = 4, message = "Enter 4 characters, minimum 2")
	private String code;
	
	@NotEmpty(message = "This field cannot be empty")
	private String courseName;

//	@ManyToMany
//	Set<Student> students;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String nameCourse) {
		this.courseName = nameCourse;
	}

}
