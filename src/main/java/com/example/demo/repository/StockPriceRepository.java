package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Company;
import com.example.demo.entity.StockPrice;

public interface StockPriceRepository extends JpaRepository<StockPrice,Long> {


}
