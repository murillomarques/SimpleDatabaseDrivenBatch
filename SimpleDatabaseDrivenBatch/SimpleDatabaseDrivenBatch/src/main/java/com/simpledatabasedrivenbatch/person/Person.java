package com.simpledatabasedrivenbatch.person;

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
	
	public void setStatus(String statusValue) {
		this.status = Status.valueOf(statusValue);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hight == null) ? 0 : hight.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Person other = (Person) obj;
		if (hight == null) {
			if (other.hight != null)
				return false;
		} else if (!hight.equals(other.hight))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	
	
	

}
