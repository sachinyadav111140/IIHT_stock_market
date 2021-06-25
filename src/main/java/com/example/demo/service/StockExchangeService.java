package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Company;
import com.example.demo.entity.StockExchange;
import com.example.demo.entity.StockPrice;
import com.example.demo.repository.StockExchangeRepository;


import dto.CompanyResponse;
import dto.StockPricedto;

@Service
public class StockExchangeService {
 @Autowired
 StockExchangeRepository stockrepo;
	public List<String> displayCompanyinSE(String name) {
		List<Company> se=new ArrayList<>();
		List<String> list=new ArrayList<>();
		se=(stockrepo.findBystockExchange(name)).getcompanies();
		for(Company company : se)
		{
			list.add(company.getName());
		}
		return list;
	}
	public List<StockPricedto> displaystockpriceinExchange(String name, String sd, String st, String ed, String et) {
		StockExchange stockex=stockrepo.findBystockExchange(name);
		List<StockPrice> stockprice=stockex.getStockPrice();
		List<StockPricedto> list=new ArrayList<>();
		boolean f,s;
		for(StockPrice sp: stockprice)
		{
			f=(sp.getDate().compareTo(sd)>0)||(sp.getDate().compareTo(sd)==0&&sp.getTime().compareTo(st)>=0);
	s=(sp.getDate().compareTo(ed)<0)||(sp.getDate().compareTo(ed)==0&&sp.getTime().compareTo(et)<=0);	
	if(f&&s)
	{
	StockPricedto spd=new StockPricedto();
			spd.setName(sp.getCompany().getName());
			spd.setDate(sp.getDate());
			spd.setTime(sp.getTime());
			spd.setStockexchange(name);
	spd.setPrice(sp.getPrice());
	list.add(spd);
	}
		
	}
	    return list;
	}

}
