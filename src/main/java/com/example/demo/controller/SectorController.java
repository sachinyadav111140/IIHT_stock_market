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

import com.example.demo.entity.Company;
import com.example.demo.entity.Sector;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.IpoRepository;
import com.example.demo.repository.SectorRepository;
import com.example.demo.repository.StockExchangeRepository;

import dto.Companydto;
import dto.Sectordto;

@RestController
public class SectorController {
	@Autowired
	SectorRepository sectorrepo;
	
	
	@CrossOrigin(origins ="http://localhost:3000")
	@Transactional
	@PostMapping("/addsector")
	public void addsector( @RequestBody  Sectordto sectordto) {
		Sector sector=new Sector();
		sector.setName(sectordto.getName());
		sector.setBrief(sectordto.getBrief());
		sectorrepo.save(sector);
	}
	@CrossOrigin(origins ="http://localhost:3000")
	@Transactional
	@PostMapping("/deletesector")
	public void deletesector( @RequestBody  Sectordto sectordto) {
		sectorrepo.deleteByName(sectordto.getName());
	}
	@CrossOrigin(origins ="http://localhost:3000")
	@Transactional
	@GetMapping("/getsector")
	public List<Sectordto> getsector( ) {
		List<Sector> sectors=sectorrepo.findAll();
		List<Sectordto> list=new ArrayList<>();
		
		for(Sector sector:sectors)
		{
			Sectordto dto=new Sectordto();
			dto.setName(sector.getName());
			dto.setBrief(sector.getBrief());
			list.add(dto);
		}
	return list;
	}
	
	
	@Transactional
	@GetMapping("/displayCompanyinSector/{name}")
	public  List<String> displayCompanyinSector(@PathVariable("name") String name)
	{
		Sector sec=sectorrepo.findByName(name);
		List<String> list=new ArrayList<>();
		for(Company company : sec.getCompanies())
		{
			list.add(company.getName());
		}
	return list;
	}
	

}