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
	public List<ContactRecord> getAllRecordsWhere(String className,
			String column, String searchValue) throws ClassNotFoundException {

		String stringQuery = String.format(
				"from %s s where (s.%s) like (:arg1)", className, column);
		Query query = entityManager.createQuery(stringQuery);
		query.setParameter("arg1", searchValue);
		List<ContactRecord> list = query.getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	public int deleteAllRecordsWhere(String className, String column,
			String valueToDelete) throws ClassNotFoundException {

		String stringQuery = String
				.format("DELETE from %s s where (s.%s) like (:arg1)",
						className, column);
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery(stringQuery);
		int deletedCount = query.setParameter("arg1", valueToDelete)
				.executeUpdate();
		entityManager.getTransaction().commit();

		return deletedCount;
	}

	public void insertRecord(ContactRecord newRecord) {
		entityManager.getTransaction().begin();
		entityManager.persist(newRecord);
		entityManager.getTransaction().commit();

	}

	public static void main(String[] args) throws ClassNotFoundException {
		ConnectionWithServer<ContactRecord> a = new ConnectionWithServer<ContactRecord>(
				"contact");
		Employee rec = new Employee();
		rec.setId(500);
		rec.setName("Test testov");
		rec.setPost("SuperHoer");
		rec.setDepartment("");
		rec.setDirector("Batman");
		rec.setSector("specialist");
		rec.setFloor(10);
		rec.setInternal(321);
		rec.setPhone("851117125");
		rec.setGsm("123456789");
		rec.setFax("");
		rec.setEmail("krach@lev-ins.com");
		rec.setUserName("krachunov");

		a.insertRecord(rec);

		String className = "Employee";
		String column = "name";
		String searchValue = "Test testov";
		List<ContactRecord> allGroup = a.getAllRecordsWhere(className, column,
				searchValue);

		System.out.println(allGroup.size());
		for (ContactRecord contactRecord : allGroup) {
			System.out.println(contactRecord.toString());
		}

	}
}
