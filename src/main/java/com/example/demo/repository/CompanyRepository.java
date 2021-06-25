package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Company;


public interface CompanyRepository extends JpaRepository<Company,Long> {

	Company findByName(String name);
	Company findById(long id);
	List<Company> findByNameStartsWith(String name);

}
