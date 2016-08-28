package com.simpledatabasedrivenbatch.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.simpledatabasedrivenbatch.config.SimpleDatabaseDrivenBatchConfig;
import com.simpledatabasedrivenbatch.domain.Person;
import com.simpledatabasedrivenbatch.domain.Person.Status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SimpleDatabaseDrivenBatchConfig.class)
public class PersonDaoImplTest {
	
	@Autowired
	private PersonDao personDao;
	
	@Test
	public void shouldSaveSimplePerson(){
		Person givenSimplePerson = new Person("Marcos o Terceiro", Status.COMPLETED, 1.85);
		personDao.savePerson(givenSimplePerson);
		
	}

}
