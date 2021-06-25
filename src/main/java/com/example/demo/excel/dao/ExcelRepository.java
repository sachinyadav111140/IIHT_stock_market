package com.example.demo.excel.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.excel.entities.stockPriceEntity;



@Repository
public interface ExcelRepository extends JpaRepository<stockPriceEntity,Long>
{

	

	

	

	



}
