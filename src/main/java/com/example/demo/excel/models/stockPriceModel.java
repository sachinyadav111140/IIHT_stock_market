package com.example.demo.excel.models;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;
import java.util.Date;



public class stockPriceModel
{

    private Long id;

    private Long companyCode;
    private String stockExchange;
    private Double currentPrice;
    private Date date;
    private Time time;

    public stockPriceModel() {
    }

    public stockPriceModel(Long companyCode, String stockExchange, Double currentPrice, Date date, Time time) {
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
