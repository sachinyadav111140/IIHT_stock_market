package com.example.demo.entity;

import java.util.*;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Company {
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private float turnover;
	
	private String	ceo;
	private String directors;
    
	@ManyToMany(mappedBy="companies")
	private List<StockExchange> stockExchanges= new ArrayList<>();
	
    @ManyToOne
    private Sector sector;
    @OneToOne(cascade = CascadeType.ALL)
	private Ipo ipo;
    @OneToMany(mappedBy="company")
    List<StockPrice> stockPrice=new ArrayList<>();
	private String writeup;
	public long getId()
	{
		return id;
	}
	public Ipo getipo()
	{
		return this.ipo;
	}
	public void setipo(Ipo ipo)
	{
		this.ipo=ipo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCeo() {
		return ceo;
	}
	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
	public String getDirectors() {
		return directors;
	}
	public void setDirectors(String director) {
		this.directors= director;
	}
	
	
	public List<StockExchange> getStockExchanges() {
		return stockExchanges;
	}
	public void addStockexchanges(StockExchange stockExchange) {
		this.stockExchanges.add ( stockExchange);
	}
	public void addListStockexchanges(List<StockExchange> stockExchange) {
		this.stockExchanges= stockExchange;
	}
	
	public void removeStockexchanges(StockExchange stockExchange) {
		this.stockExchanges.remove ( stockExchange);
	}
	
	public List<StockPrice> getstockPrice() {
		return stockPrice;
	}
	public void addstockPrice(StockPrice stockPrice) {
		this.stockPrice.add ( stockPrice);
	}
	public void removestockPrice(StockPrice stockPrice) {
		this.stockPrice.remove ( stockPrice);
	}
	
	public Sector getSector() {
		return sector;
	}
	public Sector setSector(Sector sector) {
		return this.sector=sector;
	}
	
	
	public String getWriteup() {
		return writeup;
	}
	public void setWriteup(String writeup) {
		this.writeup = writeup;
	}
	public float getTurnover() {
		return turnover;
	}
	public void setTurnover(float turnover) {
		this.turnover = turnover;
	}
	

}
