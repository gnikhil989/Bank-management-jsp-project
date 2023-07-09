package com.jsp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jsp.dto.Person;

public class PersonDao {

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("nikhil");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();

//	save person 
	public Person savePersonDetails(Person person) {
		if (person.getBankAccount() == null) {
			person.setStatus("unapproved");
			entityTransaction.begin();
			entityManager.persist(person);
			entityTransaction.commit();
			return person;
		} else {
			return null;
		}

	}

	public Person getPersonById(int id) {
		Person person = entityManager.find(Person.class, id);
		return person;

	}

//	get all person
	public List<Person> getAllPerson() {
		String s = "SELECT p FROM Person p";
		Query q = entityManager.createQuery(s);
		List<Person> li = q.getResultList();
		return li;
	}

// update person
	public Person updatePersonById(int person_id, Person person) {
		Person p1 = getPersonById(person_id);
		if (p1 != null) {
			entityTransaction.begin();
			if (person.getEmail() != null) {
				p1.setEmail(person.getEmail());
			}
			if (person.getGender() != null) {
				p1.setGender(person.getGender());
			}
			if (person.getName() != null) {
				p1.setName(person.getName());
			}if (person.getStatus() != null) {
				p1.setStatus(p1.getStatus());
			}
			entityManager.persist(p1);
			entityTransaction.commit();
			return p1;

		} else {
			return null;
		}
	}

	// delete Person By Id
	public boolean deletePersonById(int id) {
		entityTransaction.begin();
		Person p = getPersonById(id);
		if (p != null) {
			entityManager.remove(p);
			entityTransaction.commit();
			return true;
		} else {
			return false;
		}
	}

	// save multiple person
	public boolean saveMultiplePerson(List<Person> persons) {
		entityTransaction.begin();

		for (Person p : persons) {
			p.setStatus("UnApproved");
			entityManager.persist(p);
		}
		entityTransaction.commit();
		return true;
	}
}
