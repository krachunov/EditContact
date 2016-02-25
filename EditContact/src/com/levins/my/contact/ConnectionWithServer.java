package com.levins.my.contact;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ConnectionWithServer<T> {

	private EntityManagerFactory factory;
	private EntityManager entityManager;

	public ConnectionWithServer(String persistenceName) {
		this.factory = Persistence.createEntityManagerFactory(persistenceName);
		this.entityManager = factory.createEntityManager();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAllrecords(String className)
			throws ClassNotFoundException {
		String qlString = String.format("SELECT m FROM %s m", className);
		Query query = entityManager.createQuery(qlString);
		List<T> resultList = query.getResultList();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public List<T> getAllGroup(String className, String column,
			String searchValue) throws ClassNotFoundException {
		String qlString = String.format(
				"SELECT m FROM %s m WHERE m.%s like '%s'", className, column,
				searchValue);
		Query query = entityManager.createQuery(qlString);

		List<T> resultList = query.getResultList();
		return resultList;
	}

	public static void main(String[] args) throws ClassNotFoundException {
		ConnectionWithServer<ContactRecord> a = new ConnectionWithServer<ContactRecord>(
				"contact");

		List<ContactRecord> allGroup = a.getAllGroup("Employee", "name","ФИНАНСОВО СЧЕТОВОДНА");
		System.out.println(allGroup.size());
		for (ContactRecord contactRecord : allGroup) {
			System.out.println(contactRecord.getName());
		}

	}

}
