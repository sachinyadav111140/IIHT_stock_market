package dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.example.demo.entity.Sector;
import com.example.demo.entity.StockExchange;

public class CompanyResponse {
	private long id;
	private String name;
	private float turnover;
	
	private String	ceo;
	private String directors;
    
	
	private List<String> stockExchanges= new ArrayList<>();
	
    
    private String sector;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public float getTurnover() {
		return turnover;
	}


	public void setTurnover(float turnover) {
		this.turnover = turnover;
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


	public void setDirectors(String directors) {
		this.directors = directors;
	}


	public List<String> getStockExchanges() {
		return stockExchanges;
	}


	public void setStockExchanges(List<String> stockExchanges) {
		this.stockExchanges = stockExchanges;
	}
public void addStockExchange(String stockexchange)
{
	this.stockExchanges.add(stockexchange);
}

	public String getSector() {
		return sector;
	}


	public void setSector(String sector) {
		this.sector = sector;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}
    
}
