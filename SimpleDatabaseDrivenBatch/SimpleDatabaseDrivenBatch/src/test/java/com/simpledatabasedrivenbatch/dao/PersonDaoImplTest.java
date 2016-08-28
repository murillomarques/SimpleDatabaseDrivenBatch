package com.simpledatabasedrivenbatch.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.simpledatabasedrivenbatch.config.SimpleDatabaseDrivenBatchConfig;
import com.simpledatabasedrivenbatch.person.Person;
import com.simpledatabasedrivenbatch.person.PersonDao;
import com.simpledatabasedrivenbatch.person.Person.Status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SimpleDatabaseDrivenBatchConfig.class)
public class PersonDaoImplTest {
	
	@Autowired
	private PersonDao personDao;
	
	@Test
	public void shouldCreatePerson() {
		Person givenSimplePerson = new Person("Marcos o Terceiro", Status.COMPLETED, 1.85);
		
		Person savedPerson = personDao.create(givenSimplePerson);
		
		assertNotNull("Your Person does not have an ID, Are you sure was He saved?", savedPerson.getId());
	}
	
	@Test
	public void shouldFindPersonById() {
		Long givenCreatedPersonId =  givenCreatedSimplePersonId();
		
		Person savedPerson = personDao.findByID(givenCreatedPersonId);
		
		assertEquals("The IDs are not the same! ", givenCreatedPersonId, savedPerson.getId());
	}
	
	@Test
	public void shouldUpdatePerson() {
		Person givenCreatedPerson =  givenCreatedSimplePerson();
		
		Person updatedPerson = whenUpdatingPerson(givenCreatedPerson);
		
		thenPersonFoundInDataBaseMustBeEqualToThis(updatedPerson);
	}

	private void thenPersonFoundInDataBaseMustBeEqualToThis(Person updatedPerson) {
		Person foundPerson = personDao.findByID(updatedPerson.getId());
		assertTrue("Ops, looks like you got a problem with your update.", updatedPerson.equals(foundPerson));
	}

	private Person whenUpdatingPerson(Person givenCreatedPerson) {
		givenCreatedPerson.setName("John Updated");
		givenCreatedPerson.setStatus(Status.CANCELLED);
		givenCreatedPerson.setHight(1.51);
		return personDao.update(givenCreatedPerson);
	}

	private Long givenCreatedSimplePersonId() {
		Person savedPerson = givenCreatedSimplePerson();
		return savedPerson.getId();
	}
	
	private Person givenCreatedSimplePerson() {
		Person givenSimplePerson = new Person("Marcos o Terceiro", Status.COMPLETED, 1.85);
		Person savedPerson = personDao.create(givenSimplePerson);
		return savedPerson;
	}
	

}
