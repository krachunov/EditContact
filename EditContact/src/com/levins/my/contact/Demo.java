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

	public static void main(String[] args) throws UnsupportedEncodingException {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("contact");
		try {

			EntityManager entityManager = factory.createEntityManager();
			CriteriaBuilder criteriaBuilder = entityManager
					.getCriteriaBuilder();
			CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
			Root<Employee> from = (Root) criteriaQuery.from(Employee.class);

			 //select all records
//			   System.out.println("Select all records");
//			   CriteriaQuery<Object> select = criteriaQuery.select(from);
//			   TypedQuery<Object> typedQuery = entityManager.createQuery(select);
//			   List<Object> resultlist = typedQuery.getResultList();
//			   for(Object o:resultlist) {
//				   Employee e = (Employee)o;
//				   System.out.println("EID : " + e.getId() + " name : " + e.getName());
//			   }

			   
			   String s = "Ириян Марков";
			   Query query = entityManager.createQuery("from Employee s where (s.name) like (:arg1)");
			   query.setParameter("arg1", s);
			   List<Employee> list = query.getResultList();
			   for (Employee employee : list) {
				System.out.println(employee);
			}
			   
			
			
			   //Ordering the records 
			// System.out.println("Select all records by follow ordering");
			// CriteriaQuery<Object> select1 = criteriaQuery.select(from);
			// select1.orderBy(criteriaBuilder.asc(from.get("name")));
			// TypedQuery<Object> typedQuery1 =
			// entityManager.createQuery(select);
			// List<Object> resultlist1 = typedQuery1.getResultList();
			//
			// // for(Object o:resultlist1){
			// // Employee e=(Employee)o;
			// // System.out.println("EID : " + e.getId() + " Ename : " +
			// e.getName());
			// // }

			entityManager.close();

		} finally {
			factory.close();
		}

	}
}
