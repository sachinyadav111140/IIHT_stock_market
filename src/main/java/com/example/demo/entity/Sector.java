package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.OneToMany;
@Entity
public class Sector {
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private String brief;
	@OneToMany(mappedBy="sector")
	private List<Company> companies=new ArrayList<>();
	public List<Company> getCompanies()
	{
		return companies;
	}
	public void addCompany(Company company)
	{
		this.companies.add(company);
	}
	public void removeCompany(Company company)
	{
		this.companies.remove(company);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	
}
