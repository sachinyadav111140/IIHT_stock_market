package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.excel.dao.ExcelRepository;
import com.example.demo.excel.entities.stockPriceEntity;

import dto.Companydto;

@RestController
public class Chart {
	@Autowired
	ExcelRepository excelrepo;
	
	@CrossOrigin(origins ="http://localhost:3000")
	@Transactional
	@GetMapping("/price")
	public List<stockPriceEntity> getprice() {
		return excelrepo.findAll();
		}
	
}
