package com.simpledatabasedrivenbatch.person;

public interface PersonService {
	
	Person save(Person person);
	
	Person findById(Long personId);

}
