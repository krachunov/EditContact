package com.levins.my.contact;

import javax.persistence.*;

@Entity
@Table(name = "levins_centrala")
public class Employee implements ContactRecord{

	@Id
	private Integer id;
	// private Long id = System.currentTimeMillis();

	@Column(name = "employees")
	private String name;

	@Column(name = "post")
	private String post;

	@Column(name = "director")
	private String director;

	@Column(name = "department")
	private String department;

	@Column(name = "sector")
	private String sector;

	@Column(name = "floor")
	private Integer floor;

	@Column(name = "internal")
	private Integer internal;

	@Column(name = "phone")
	private String phone;

	@Column(name = "GSM")
	private String gsm;

	@Column(name = "Fax")
	private String fax;

	@Column(name = "email")
	private String email;

	@Column(name = "user")
	private String userName;

	public Employee() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public Integer getInternal() {
		return internal;
	}

	public void setInternal(Integer internal) {
		this.internal = internal;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGsm() {
		return gsm;
	}

	public void setGsm(String gsm) {
		this.gsm = gsm;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return getName() + " " + getEmail();
	}
}
