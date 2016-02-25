package com.levins.my.contact;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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

			Query query = entitymanager.createQuery("SELECT m FROM com.levins.my.contact.Employee m");
			List<Employee> resultList = query.getResultList();

			for (Employee m : resultList) {
				System.out.println(m.getName());
			}

			entitymanager.close();

		} finally {
			factory.close();
		}

	}

}
