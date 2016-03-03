package com.levins.my.contact;

import javax.persistence.*;

public interface ContactRecord {

	public abstract String getName();

	public abstract String getPhone();

	public abstract String getEmail();

	public abstract String toString();

	public abstract void setEmail(String newValue);

}
