package com.levins.my.contact;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
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

			CriteriaBuilder cb = entitymanager.getCriteriaBuilder();
			/**
			 * http://www.tutorialspoint.com/jpa/jpa_criteria_api.htm
			 */
			// CriteriaQuery<Entity class> cq = cb.createQuery(Entity.class);
			// Root<Entity> from = cq.from(Entity.class);
			//
			// cq.select(Entity);
			// TypedQuery<Entity> q = em.createQuery(cq);
			// List<Entity> allitems = q.getResultList();

			String qlString = null;
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
