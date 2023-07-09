package com.jsp.dao;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jsp.dto.BankAccount;
import com.jsp.dto.Person;
import com.jsp.service.PersonService;

public class BankAccountDao {

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("nikhil");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();

	public BankAccount saveBankAccountDetails(BankAccount bankAccount) {
		entityTransaction.begin();
		entityManager.persist(bankAccount);
		entityTransaction.commit();
		return bankAccount;
	}

	PersonService personService = new PersonService();
	Random r = new Random();

// open account by id
	public BankAccount saveBankAccountById(int person_id, BankAccount bankAccount) {
		Person p = personService.getPersonById(person_id);
		BankAccount bank = p.getBankAccount();
		if (p != null && bank == null && p.getStatus().equalsIgnoreCase("approved")) {

			entityTransaction.begin();
			BankAccount ba = new BankAccount();

			long account_no = r.nextLong(10000000000l);
			ba.setBalance(bankAccount.getBalance());
			ba.setAccount_number(account_no);
			ba.setPerson(p);
			ba.setType(bankAccount.getType());
			p.setBankAccount(ba);
			ba.setBank(bankAccount.getBank());
			entityManager.merge(ba);
			entityTransaction.commit();
			return ba;
		} else {
			return null;
		}

	}
	

// get account details by id
	public BankAccount getAccountDetailsById(int person_id) {
		Person p1 = personService.getPersonById(person_id);
		if (p1.getStatus().equalsIgnoreCase("approved") && p1.getBankAccount() != null) {
			BankAccount ba = p1.getBankAccount();

			return ba;
		} else {
			return null;
		}
	}

	// Get All Account Details
	public List<BankAccount> getAllAccountDetails() {
		String s = "SELECT b FROM BankAccount b";
		Query q = entityManager.createQuery(s);
		List<BankAccount> li = q.getResultList();
		return li;
	}

	// delete Account By Id
	public boolean deleteBankAccountById(int acc_id) {
		entityTransaction.begin();
		BankAccount ba = entityManager.find(BankAccount.class, acc_id);
		if (ba != null) {
			entityManager.remove(ba);
			entityTransaction.commit();
			return true;
		} else {
			return false;
		}
	}

	// deposit money

	public BankAccount depositMoney(int acc_id, int p_id, long depositAmmout) {
		if (acc_id > 0 && p_id > 0) {
			entityTransaction.begin();
			Person p = personService.getPersonById(p_id);
			BankAccount ba = entityManager.find(BankAccount.class, acc_id);
			if (p != null && ba != null) {
				ba.setBalance(ba.getBalance() + depositAmmout);
				entityManager.persist(ba);
				entityTransaction.commit();

				return ba;
			} else {entityTransaction.commit();
				return null;
			}
		} else {
			return null;
		}
	}

	// widthraw money
	public BankAccount widthrawMoney(int acc_id, int p_id, long withdrawlMoney) {

		if (acc_id > 0 && p_id > 0) {
			BankAccount ba = entityManager.find(BankAccount.class, acc_id);
			Person p = personService.getPersonById(p_id);
			if (ba.getBalance() >= withdrawlMoney) {
				entityTransaction.begin();
				ba.setBalance(ba.getBalance() - withdrawlMoney);
				entityManager.merge(ba);
				entityTransaction.commit();
				return ba;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public BankAccount transferMoney(int senderAcc_id, int receiverAcc_id, long transferAmmount) {
		if (senderAcc_id > 0 && receiverAcc_id > 0) {
			BankAccount senderAccount = entityManager.find(BankAccount.class, senderAcc_id);
			BankAccount receiverAccount = entityManager.find(BankAccount.class, receiverAcc_id);
			if (senderAccount != null && receiverAccount != null && senderAccount.getBalance() >= transferAmmount) {
				entityTransaction.begin();
				senderAccount.setBalance(senderAccount.getBalance() - transferAmmount);
				receiverAccount.setBalance(receiverAccount.getBalance() + transferAmmount);
				entityManager.persist(senderAccount);
				entityManager.persist(receiverAccount);
				entityTransaction.commit();
				return senderAccount;
			} else {
				return null;
			}

		} else {
			return null;
		}
	}

}
