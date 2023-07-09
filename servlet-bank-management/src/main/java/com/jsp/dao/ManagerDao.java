package com.jsp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jsp.dto.Manager;

public class ManagerDao {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("nikhil");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();

	public Manager saveManager(Manager manager) {
		entityTransaction.begin();
		entityManager.persist(manager);
		entityTransaction.commit();
		return manager;

	}

//	get by id
	public Manager getManagerById(int id) {
		Manager m1 = entityManager.find(Manager.class, id);
		return m1;

	}

	public List<Manager> getAllManagers() {
		String s = "SELECT b FROM Manager b";
		Query q = entityManager.createQuery(s);
		List<Manager> li = q.getResultList();
		return li;
	}

	// Update manager
	public Manager updateManager(int manager_id, Manager manager) {

		Manager manager2 = getManagerById(manager_id);
		if (manager2 != null) {
			entityTransaction.begin();
			if (manager.getManager_name() != null)
				manager.setManager_name(manager.getManager_name());
			if (manager.getBank() != null)
				manager.setBank(manager2.getBank());
			if(manager.getEmail()!=null)
				manager.setEmail(manager.getEmail());

			entityManager.persist(manager2);
			entityTransaction.commit();
			return manager2;
		} else {
			return null;
		}

	}

	// delete manager By Id
	public boolean deleteManagerById(int id) {
		entityTransaction.begin();
		Manager bm = getManagerById(id);
		if (bm != null) {
			entityManager.remove(bm);
			entityTransaction.commit();
			return true;
		} else {
			return false;
		}
	}

	// save multiple manager
	public boolean saveMultipleManager(List<Manager> managers) {
		entityTransaction.begin();

		for (Manager bm : managers) {
			entityManager.persist(bm);
		}
		entityTransaction.commit();
		return true;
	}
}

