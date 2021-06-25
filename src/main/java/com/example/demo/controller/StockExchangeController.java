package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.StockExchange;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.IpoRepository;
import com.example.demo.repository.SectorRepository;
import com.example.demo.repository.StockExchangeRepository;
import com.example.demo.service.StockExchangeService;

import dto.CompanyResponse;
import dto.Companydto;
import dto.Perioddto;
import dto.StockExchangedto;
import dto.StockPricedto;

@RestController
public class StockExchangeController{
	
	
	@Autowired
	StockExchangeRepository stockrepo;
	
	@Autowired
	StockExchangeService stockservice;
	@CrossOrigin(origins ="http://localhost:3000")
	@Transactional
	@PostMapping("/addStockExchange")
	public void addStockExchange( @RequestBody StockExchangedto stockexchangedto) {
		StockExchange stockexchange=new StockExchange();
		stockexchange.setstockExchange(stockexchangedto.getStockExchange());
		stockexchange.setAdd(stockexchangedto.getAddress());
		stockexchange.setBrief(stockexchangedto.getBrief());
		stockexchange.setRemarks(stockexchangedto.getRemarks());
		stockrepo.save(stockexchange);
	}
	@CrossOrigin(origins ="http://localhost:3000")
	@Transactional
	@GetMapping("/getStockExchange")
	public List<StockExchangedto> getStockExchange() {
		List<StockExchange> stockexchanges=stockrepo.findAll()	;
	List<StockExchangedto> list=new ArrayList<>();

	for(StockExchange stockexchange: stockexchanges)
	{StockExchangedto dto=new StockExchangedto();
		dto.setStockExchange(stockexchange.getstockExchange());
		dto.setAddress(stockexchange.getAddress());
		dto.setBrief(stockexchange.getBrief());
		dto.setRemarks(stockexchange.getRemarks());
		list.add(dto);
	}
	return list;
	}
	
	@Transactional
	@GetMapping("/displayCompanyinStockExchange/{name}")
	public  List<String> displayCompanyinStockExchange(@PathVariable("name") String name)
	{
		return stockservice.displayCompanyinSE(name);
	}
	@Transactional
	@PostMapping("/displayStockPriceinExchange")
	public List<StockPricedto> displayStockPriceinExchange(@RequestBody Perioddto perioddto)
	{
		return stockservice.displaystockpriceinExchange(perioddto.getName(),perioddto.getSd(),perioddto.getSt(),
				perioddto.getEd(),perioddto.getEt());
	}	
	
}