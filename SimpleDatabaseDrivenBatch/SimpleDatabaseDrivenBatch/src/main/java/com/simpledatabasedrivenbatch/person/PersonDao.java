package com.simpledatabasedrivenbatch.person;

public interface PersonDao {
	
	Person create(Person person);
	
	Person findByID(Long Id);
	
	Person update(Person person);
	
	

}
