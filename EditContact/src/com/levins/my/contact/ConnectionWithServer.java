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
	public List<ContactRecord> getAllGroup(String className, String column,
			String searchValue) throws ClassNotFoundException {

		String stringQuery = String.format(
				"from %s s where (s.%s) like (:arg1)", className, column);
		Query query = entityManager.createQuery(stringQuery);
		query.setParameter("arg1", searchValue);
		List<ContactRecord> list = query.getResultList();

		return list;
	}

	public static void main(String[] args) throws ClassNotFoundException {
		ConnectionWithServer<ContactRecord> a = new ConnectionWithServer<ContactRecord>(
				"contact");

		String className = "Agent";
		String column = "name";
		String searchValue = "КЪРДЖАЛИ";
		List<ContactRecord> allGroup = a.getAllGroup(className, column,
				searchValue);

		System.out.println(allGroup.size());

		for (ContactRecord contactRecord : allGroup) {
			System.out.println(contactRecord.toString());
		}

	}

}
