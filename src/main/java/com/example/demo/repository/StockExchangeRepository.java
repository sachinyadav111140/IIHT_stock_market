package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.entity.StockExchange;


public interface StockExchangeRepository extends JpaRepository<StockExchange,Long> {

	StockExchange findBystockExchange(String se);

}
