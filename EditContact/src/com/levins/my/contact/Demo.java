package com.levins.my.contact;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class Demo {

	public static List findAll(EntityManager entitymanager, String entityName) {
		return entitymanager.createQuery("SELECT e FROM " + entityName + " e")
				.setMaxResults(100).getResultList();
	}

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("contact");
		try {

			EntityManager entitymanager = factory.createEntityManager();

			// entitymanager.getTransaction().begin();

			// Object className = "Employee";
			// Object column = "name";
			// Object searchValue = "ФИНАНСОВО СЧЕТОВОДНА";
			String qlString = String.format("SELECT m FROM Employee m");

			// CriteriaQuery<ContactRecord>q=entitymanager.createQuery(CriteriaQuery.class);

			Query query = entitymanager.createQuery(qlString);
			List<Employee> resultList = query.getResultList();

			for (Employee m : resultList) {
				System.out.println(m.toString());
			}

			entitymanager.close();

		} finally {
			factory.close();
		}

	}
}
