package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Company;
import com.example.demo.entity.Ipo;
import com.example.demo.entity.StockExchange;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.IpoRepository;
import com.example.demo.repository.StockExchangeRepository;

import dto.Ipodto;





@Service
public class IpoService {
	@Autowired
	CompanyRepository companyrepo;
	
	@Autowired
	StockExchangeRepository stockrepo;
	@Autowired
	IpoRepository iporepo;

	public void updateipodetails(Ipodto ipodto) {
		Company company=companyrepo.findById(ipodto.getId());
		Ipo ipo=company.getipo();
		company.setName(ipodto.getCompanyname());
		ipo.setDate(ipodto.getDate());
		ipo.setNumber(ipodto.getNumber());
		ipo.setTime(ipodto.getTime());
		ipo.setPrice(ipo.getPrice());
	for(StockExchange se : company.getStockExchanges())
	{
		se.removecompany(company);
		se.removeIpo(ipo);
	}
	List<StockExchange> list=new ArrayList<>();
	for(String stockex: ipodto.getStockex())
	{
		StockExchange stockexchange=stockrepo.findBystockExchange(stockex);
		stockexchange.addcompany(company);
		stockexchange.addIpo(ipo);
		list.add(stockexchange);
	}
		ipo.addListStockExchange(list);
		company.addListStockexchanges(list);
		
	}

	public Ipodto displayCompanyIpo(long id) {
		Company company=companyrepo.findById(id);
		Ipodto ipodto=new Ipodto();
		ipodto.setId(id);
		ipodto.setCompanyname(company.getName());
		ipodto.setDate((company.getipo()).getDate());
		ipodto.setTime((company.getipo()).getTime());
		ipodto.setPrice((company.getipo()).getPrice());
		ipodto.setNumber((company.getipo()).getNumber());
		List<StockExchange> stockexchanges=company.getStockExchanges();
		for(StockExchange se: stockexchanges)
		{
	ipodto.addStockex(se.getstockExchange());
		}
		return ipodto;
		
	}

	public List<Ipodto> displayUpcomingIpo() {
		List<Order> orders = new ArrayList<>();
		String f1="date";
		String f2="time";
		orders.add(new Order(Direction.ASC, f1));
		orders.add(new Order(Direction.ASC, f2));
		 List<Ipo> ipo =iporepo.findAll(Sort.by(orders));
		

		List<Ipodto> list=new ArrayList<>(); 
		LocalTime time = LocalTime.now();
		String t = time.format(DateTimeFormatter.ofPattern("HH:mm"));
		 Date date = new Date();
		 String modifiedDate= new SimpleDateFormat("yyyy/MM/dd").format(date);
		 for(Ipo ip : ipo)
		 {
			 if((ip.getDate().compareTo(modifiedDate)>0)||((ip.getDate().compareTo(modifiedDate)==0)&&(ip.getTime().compareTo(t))>=0))
					 {
				Ipodto dt=new Ipodto();
				dt.setCompanyname((ip.getcompany()).getName());
				dt.setDate(ip.getDate());
				dt.setTime(ip.getTime());
				dt.setNumber(ip.getNumber());
				dt.setPrice(ip.getPrice());
				list.add(dt);
					 }
		 }
return list;	
	}

}
