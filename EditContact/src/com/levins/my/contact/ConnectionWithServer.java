package com.levins.my.contact;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ConnectionWithServer<T> {

	private EntityManagerFactory factory;
	private EntityManager entitymanager;

	public ConnectionWithServer(String persistenceName) {
		this.factory = Persistence.createEntityManagerFactory(persistenceName);
		this.entitymanager = factory.createEntityManager();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAllrecords(String className)
			throws ClassNotFoundException {
		String qlString = String.format("SELECT m FROM %s m", className);
		Query query = entitymanager.createQuery(qlString);
		List<T> resultList = query.getResultList();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public List<T> getAllDepartment(String className)
			throws ClassNotFoundException {
		String qlString = String.format("SELECT m FROM %s m", className);
		Query query = entitymanager.createQuery(qlString);

		List<T> resultList = query.getResultList();
		return resultList;
	}

	public static void main(String[] args) throws ClassNotFoundException {
		ConnectionWithServer<Agent> a = new ConnectionWithServer<Agent>("contact");

		List<Agent> allrecords = a.getAllrecords("Employee");
		for (ContactRecord record : allrecords) {
			System.out.println(record);
		}
	}

}
