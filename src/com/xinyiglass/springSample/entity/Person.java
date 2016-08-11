package com.xinyiglass.springSample.entity;

public class Person {
	private Integer id;
	private String name;
	private String age;
	private String description;
	public Person(Integer id, String name,String age,String description){
		this.id = id;
		this.name = name;
		this.age =age;
		this.description = description;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
