package com.example.demo.excel.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.excel.dao.ExcelRepository;
import com.example.demo.excel.services.ExcelService;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

@RestController

public class excelController
{
    @Autowired
    ExcelService es;
    @Autowired
    ExcelRepository er;

//    @CrossOrigin(origins ="http://localhost:3000", allowedHeaders = "*")
//    @GetMapping(value="/files")
//    public String testing()
//    {
//        return "Excel files will be shown here";
//    }

    @CrossOrigin(origins ="http://localhost:3000")
    @PostMapping(value="/excel/uploadExcel")
    public String uploadExcel(@RequestParam MultipartFile file) throws IOException, ParseException, SQLException
    {
 //console.log("rrrrrrrrraaaaaaaaaaaaaiiiiiiiiiiiiiiiiiiiaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        es.uploadExcel(file);
        return "Excel file uploaded successfully";
    }

}

