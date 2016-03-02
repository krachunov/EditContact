package com.levins.my.contact;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.persistence.*;

public class Demo {

	@SuppressWarnings("rawtypes")
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
		@SuppressWarnings("unchecked")
		List<ContactRecord> list = query.getResultList();

		entityManager.close();
		return list;
	}

	public static void main(String[] args) throws UnsupportedEncodingException,
			ClassNotFoundException {
		String regEx = "^\\s*$";
		String sp = "       ";
		System.out.println(sp.matches(regEx));

	}
}
