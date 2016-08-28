package com.simpledatabasedrivenbatch.person.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.simpledatabasedrivenbatch.person.Person;
import com.simpledatabasedrivenbatch.person.PersonDao;
import com.simpledatabasedrivenbatch.person.PersonService;

@Transactional(isolation = Isolation.DEFAULT, readOnly = false, propagation = Propagation.REQUIRED)
@Service
public class PersonServiceImpl implements PersonService {
	
	private final PersonDao personDao;
	
	@Autowired
	public PersonServiceImpl(PersonDao personDao) {
		super();
		this.personDao = personDao;
	}


	@Override
	public Person save(Person person) {
		if(person.newPerson()){
			person = personDao.create(person);
		}
		else{
			person = personDao.update(person);
			
		}
		return person;
	}

	
	@Transactional(readOnly = true)
	@Override
	public Person findById(Long personId) {
		Person foundPerson = null;
		foundPerson = personDao.findByID(personId);
		return foundPerson;
	}

}
