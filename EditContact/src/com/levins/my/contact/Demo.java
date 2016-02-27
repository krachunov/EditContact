package com.levins.my.contact;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

public class Demo {

	public static List findAll(EntityManager entitymanager, String entityName) {
		return entitymanager.createQuery("SELECT e FROM " + entityName + " e")
				.setMaxResults(100).getResultList();
	}

	public static List<ContactRecord> getAllGroup(String className,
			String column, String searchValue) throws ClassNotFoundException {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("contact");
		EntityManager entityManager = factory.createEntityManager();

		String stringQuery = String.format(
				"from %s s where (s.%s) like (:arg1)", className, column);
		System.out.println(stringQuery);
		Query query = entityManager.createQuery(stringQuery);
		query.setParameter("arg1", searchValue);
		List<ContactRecord> list = query.getResultList();

		entityManager.close();
		return list;
	}

	public static void main(String[] args) throws UnsupportedEncodingException,
			ClassNotFoundException {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("contact");
		try {

			EntityManager entityManager = factory.createEntityManager();

			String s = "ПОИ";
			String field = "department";
//			String stringQuery = String.format(
//					"from Employee s where (s.%s) like (:arg1)", field);
//			 Query query = entityManager.createQuery(stringQuery);
//			 query.setParameter("arg1", s);
//			 List<ContactRecord> list = query.getResultList();
//			 for (ContactRecord employee : list) {
//			 System.out.println(employee);
//			 }
			
			
			List<ContactRecord> list = getAllGroup("Employee", field, s);
			for (ContactRecord employee : list) {
				System.out.println(employee);
			}

			entityManager.close();

		} finally {
			factory.close();
		}

	}
}
