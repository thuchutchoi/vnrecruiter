package com.spr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "Shop.timBoiTen", query = "SELECT sh FROM Shop sh WHERE LOWER(sh.name) = LOWER(?1)")
@Table(name = "shops")
public class Shop {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@Column(name = "employees_number")
	private Integer emplNumber;

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

	public Integer getEmplNumber() {
		return emplNumber;
	}

	public void setEmplNumber(Integer emplNumber) {
		this.emplNumber = emplNumber;
	}
}
