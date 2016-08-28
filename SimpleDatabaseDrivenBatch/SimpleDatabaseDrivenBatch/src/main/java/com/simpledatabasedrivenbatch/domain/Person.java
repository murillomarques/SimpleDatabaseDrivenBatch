package com.simpledatabasedrivenbatch.domain;

public class Person {
	
	public enum Status {GOING, CANCELLED, COMPLETED}
	
	
	public Person() {
		
	}
	
	public Person(String name, Status status, Double hight) {
		super();
		this.name = name;
		this.status = status;
		this.hight = hight;
	}

	private Long id;
	private String name;
	private Status status;
	private Double hight;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}

	public Double getHight() {
		if(this.hight == null){
			setHight(0.0);
		}
		return hight;
	}

	public void setHight(Double hight) {
		this.hight = hight;
	}
	
	public void plusHight(Double extraHight){
		this.hight = getHight() + extraHight;
		
	}
	

}
