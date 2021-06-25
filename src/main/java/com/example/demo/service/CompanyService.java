package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Company;
import com.example.demo.entity.Ipo;
import com.example.demo.entity.Sector;
import com.example.demo.entity.StockExchange;
import com.example.demo.entity.StockPrice;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.IpoRepository;
import com.example.demo.repository.SectorRepository;
import com.example.demo.repository.StockExchangeRepository;

import dto.CompanyResponse;
import dto.Companydto;
import dto.Companyupdatedto;
import dto.StockPricedto;
@Service
public class CompanyService {
	@Autowired 
	SectorRepository sectorrepo;
	@Autowired
	CompanyRepository companyrepo;
	@Autowired
	StockExchangeRepository stockrepo;
	@Autowired
	IpoRepository iporepo;

public	List<CompanyResponse> displayallCompanies()
{
	List<Company> companies=companyrepo.findAll();
	List<CompanyResponse> companyresponse=new ArrayList<>();
	
	
	for(Company company : companies)
	{
	CompanyResponse companyres=new CompanyResponse();
	companyres.setId(company.getId());	
	companyres.setName(company.getName());
		companyres.setCeo(company.getCeo());
		companyres.setDirectors(company.getDirectors());
		companyres.setTurnover(company.getTurnover());
		companyres.setSector((company.getSector()).getName());
	for(StockExchange se: company.getStockExchanges())	
	{
		companyres.addStockExchange(se.getstockExchange());
	}
	companyresponse.add(companyres);
	}
	
	return companyresponse;
}
public CompanyResponse displayCompany(long id) {
	Company company=companyrepo.findById(id);
	
	CompanyResponse companyres=new CompanyResponse();
	companyres.setId(id);
	companyres.setName(company.getName());
	companyres.setCeo(company.getCeo());
	companyres.setDirectors(company.getDirectors());
	companyres.setTurnover(company.getTurnover());
	companyres.setSector((company.getSector()).getName());
	for(StockExchange se: company.getStockExchanges())	
	{
		companyres.addStockExchange(se.getstockExchange());
	}
	return companyres;

}
public List<String> displayMatchingCompanies(String name) {
	List<Company> companies=companyrepo.findByNameStartsWith(name);
	List<String> response=new ArrayList<>();
	
	
	for(Company company : companies)
	{
		response.add(company.getName());
	}
	return response;
}
public void updateBsicCompany(long id,Companyupdatedto companyupdto) {
	Company company=companyrepo.findById(id);
	company.setName(companyupdto.getName());
	company.setTurnover(companyupdto.getTurnover());
	company.setDirectors(companyupdto.getDirectors());
	company.setCeo(companyupdto.getCeo());
	Sector sector=company.getSector();
	sector.removeCompany(company);
	Sector sectornew=sectorrepo.findByName(companyupdto.getSector());
	sectornew.addCompany(company);
	company.setSector(sectornew);
	
}
public void addCompany(Companydto companydto) {
	Company company=new Company();
	company.setName(companydto.getName());
	company.setTurnover(companydto.getTurnover());
	company.setDirectors(companydto.getDirectors());
	company.setWriteup(companydto.getWriteup());
	Sector sector=sectorrepo.findByName(companydto.getSector());
	sector.addCompany(company);
	company.setSector(sector);
	
Ipo ipo=new Ipo();
ipo.setDate(companydto.getIpodate());
ipo.setNumber(companydto.getNo());
	
ipo.setTime(companydto.getTime());
ipo.setPrice(companydto.getPrice());


for(String se: companydto.getstockexchange())
{
	StockExchange stockex=stockrepo.findBystockExchange(se);
stockex.addIpo(ipo)	;
ipo.addStockExchange(stockex);
company.addStockexchanges(stockex);
stockex.addcompany(company);

}
company.setipo(ipo);
ipo.setcompany(company);
companyrepo.save(company);
iporepo.save(ipo);
	
}
public List<StockPricedto> displaystockprice(String name, String sd, String st, String ed, String et) {
	Company company=companyrepo.findByName(name);
	List<StockPrice> stockprice=company.getstockPrice();
	List<StockPricedto> list=new ArrayList<>();
	boolean f,s;
	for(StockPrice sp: stockprice)
	{
		f=(sp.getDate().compareTo(sd)>0)||(sp.getDate().compareTo(sd)==0&&sp.getTime().compareTo(st)>=0);
s=(sp.getDate().compareTo(ed)<0)||(sp.getDate().compareTo(ed)==0&&sp.getTime().compareTo(et)<=0);	
if(f&&s)
{
StockPricedto spd=new StockPricedto();
		spd.setName(name);
		spd.setDate(sp.getDate());
		spd.setTime(sp.getTime());
		spd.setStockexchange((sp.getStockexchange()).getstockExchange());
spd.setPrice(sp.getPrice());
list.add(spd);
}
	
}
    return list;
}}
