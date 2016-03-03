package com.levins.my.contact;

import javax.persistence.*;

@Entity
@Table(name = "levins_agents")
public class Agent implements ContactRecord{
	@Id
	private String number;
	
	@Column(name = "agents")
	private String name;
	
	@Column(name = "phone")
	private String phone;

	@Column(name = "director")
	private String director;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	@Override
	public String toString(){
		return getNumber()+" "+getName()+" "+getDirector()+" "+getPhone();
		
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEmail(String newValue) {
		// TODO Auto-generated method stub
		
	}

}
