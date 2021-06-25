package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class StockExchange {
	
	@Id
	@GeneratedValue
	private long id;
	
	private	String stockExchange;
	
	private	String brief;
	private	String address;
	private	String remarks;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Company> companies=new ArrayList<>();
	@ManyToMany(mappedBy="stockExchange",cascade = CascadeType.ALL)
	private List<Ipo> ipo=new ArrayList<>();
	 @OneToMany(mappedBy="stockexchange")
	    List<StockPrice> stockPrice=new ArrayList<>();
	
	public List<Ipo> getStockExchange()
	{
		return ipo;
	}
	public void addIpo(Ipo ipo)
	{
		this.ipo.add(ipo);
	}
	public void removeIpo(Ipo ipo)
	{
		this.ipo.remove(ipo);
	}
	
	public String getstockExchange() {
		return stockExchange;
	}
	public void setstockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getAddress() {
		return address;
	}
	public void setAdd(String address) {
		this.address = address;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public List<Company> getcompanies()
	{
		return companies;
	}

	public void addcompany(Company company)
	{
		this.companies.add(company);
	}
public void removecompany(Company company)
{
	this.companies.remove(company);
}
public void addStockPrice(StockPrice stockPrice)
{
	this.stockPrice.add(stockPrice);
}
public List<StockPrice> getStockPrice()
{
	return stockPrice;
}
public void removeStockPrice(StockPrice stockPrice)
{
this.stockPrice.remove(stockPrice);
}


}
