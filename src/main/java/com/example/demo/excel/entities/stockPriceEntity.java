package com.example.demo.excel.entities;



import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


@Entity
@Table(name="stock_price_entity")
public class stockPriceEntity
{
	  @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(name="company_code")
    private long companyCode;
    @Column(name="stock_exchange")
    private String stockExchange;
    @Column(name="current_price")
    private Double currentPrice;
    @Column(name="Date")
    private Date date;
    @Column(name="Time")
    private Time time;

    public stockPriceEntity() {
    }

    public stockPriceEntity(Long companyCode, String stockExchange, Double currentPrice, Date date, Time time) {
        this.companyCode = companyCode;
        this.stockExchange = stockExchange;
        this.currentPrice = currentPrice;
        this.date = date;
        this.time = time;
    }

    public Long getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(Long companyCode) {
        this.companyCode = companyCode;
    }

    public String getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(String stockExchange) {
        this.stockExchange = stockExchange;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
