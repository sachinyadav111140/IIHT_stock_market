package dto;

import java.util.ArrayList;
import java.util.List;

public class Ipodto {
	private long id;
    private String companyname;
	private long price;
	
    private long number;

	private String date;
	private String time;
	private List <String> stockex=new ArrayList<>();
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public List <String> getStockex() {
		return stockex;
	}
	public void setStockex(List <String> stockex) {
		this.stockex = stockex;
	}
	public void addStockex(String stockex) {
		this.stockex.add( stockex);
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getCompanyname() {
	return this.companyname;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
