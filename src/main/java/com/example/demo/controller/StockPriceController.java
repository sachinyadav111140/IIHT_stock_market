package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Company;
import com.example.demo.entity.StockExchange;
import com.example.demo.entity.StockPrice;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.StockExchangeRepository;
import com.example.demo.repository.StockPriceRepository;

import dto.Companydto;
import dto.StockPricedto;
@RestController
public class StockPriceController {

	@Autowired
	StockPriceRepository sprepo;
	@Autowired
	CompanyRepository companyrepo;
	@Autowired
	StockExchangeRepository stockrepo;
	
	@Transactional
	@PostMapping("/addStockPrice")
	public void addStockPrice( @RequestBody StockPricedto spdto) {
	System.out.print("rai   rai -------");
		StockPrice sp=new StockPrice();
		Company company =companyrepo.findByName(spdto.getName());
		StockExchange stockexchange=stockrepo.findBystockExchange(spdto.getStockexchange()); 
		sp.setCompany(company);
		sp.setStockexchange(stockexchange);
		company.addstockPrice(sp);
		stockexchange.addStockPrice(sp);
	sp.setDate(spdto.getDate())	;
	sp.setTime(spdto.getTime())	;
	sp.setPrice(spdto.getPrice())	;
	sprepo.save(sp);
	}
	
}
