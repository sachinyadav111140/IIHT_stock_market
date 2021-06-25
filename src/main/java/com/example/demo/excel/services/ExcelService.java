package com.example.demo.excel.services;



import java.io.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.excel.dao.ExcelRepository;
import com.example.demo.excel.entities.stockPriceEntity;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

@Service
@Transactional
public class ExcelService
{

    @Autowired
    ExcelRepository er;

    List<Integer> companyCode= new ArrayList<>();
    List<String> stockExchange = new ArrayList<>();
    List<Double> currentPrice = new ArrayList<>();
    List<Date> date = new ArrayList<>();
    List<Time> time = new ArrayList<>();
    List<Object> ls = new ArrayList<>() ;

    public void uploadExcel(MultipartFile file) throws IOException, ParseException, SQLException
    {
        String url = "jdbc:mysql://localhost:3306/excelstore";
        Connection connect = DriverManager.getConnection(url,"root","Guri@3421");
        // File file = new File("C:\\Users\\Lenovo User\\Desktop\\ripunjoy\\companies\\sample_stock_data.xlsx");   //creating a new file instance
        File filez = multipartFileToFile(file);
        FileInputStream fis = new FileInputStream(filez);   //obtaining bytes from the file
        ls.clear();
//creating Workbook instance that refers to .xlsx file
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
        Iterator<Row> itr = sheet.iterator();    //iterating over excel file
        int hc = 0;
        while (itr.hasNext()) {
            Row row = itr.next();
            Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
            while (cellIterator.hasNext()) {
                hc++;

                Cell cell = cellIterator.next();
                if (cell.getCellType() == null) break;
                //System.out.println(cell.getCellType());
                ls.add(cell);

//                switch (cell.getCellType())
//                {
//                    case STRING:    //field that represents string cell type
//
//                        //System.out.print(cell.getStringCellValue() + "lol\t\t\t");
//                        ls.add(cell.getStringCellValue());
//                        break;
//                    case NUMERIC:    //field that represents number cell type
//                        numhand(hc,cell);
//
//                        //System.out.print(cell.getNumericCellValue() + "nt\t\t\t");
//                        break;
//
//                    default:
//                }
            }
        }

        System.out.println(ls);
        for (hc = 5; hc<ls.size(); hc++)
        {
                    if(ls.get(hc)==null) break;
                    if(hc%5==0)
                    {
                        String z= ls.get(hc).toString();
                        //System.out.println(z);
                        z=z.substring(0,z.length()-1);
                        int x= Integer.parseInt(z);
                        //System.out.println(ls.get(hc).getClass());
                        companyCode.add(x);
                    }
                    if(hc%5==1)
                    {
                        //System.out.println(ls.get(hc));
                        stockExchange.add(ls.get(hc).toString());
                    }
                    if(hc%5==2)
                    {
                       // System.out.println(ls.get(hc));
                        currentPrice.add(Double.parseDouble(ls.get(hc).toString()));
                    }
                    if(hc%5==3)
                    {
                        String d=ls.get(hc).toString();
                        String dd="";
                        dd+=d.charAt(7);
                        dd+=d.charAt(8);
                        dd+=d.charAt(9);
                        dd+=d.charAt(10);
                        dd+='-';
                        String mon ="";
                        mon+=d.charAt(3) ;
                        mon+=d.charAt(4);
                        mon+=d.charAt(5);
//                        dd+=d.charAt(3);
//                        dd+=d.charAt(3);
//                        dd+=d.charAt(5);
                        dd+= month(mon);
                        dd+='-';
                        dd+=d.charAt(0);
                        dd+=d.charAt(1);
                        //System.out.println(dd);
                        date.add(Date.valueOf(dd));
                    }
                    if(hc%5==4)
                    {
                        String d=ls.get(hc).toString();
//                        String hr="";
//                        String mi="";
//                        String se="";
//                        hr+=d.charAt(0);
//                        hr+=d.charAt(1);
//                        int hrz=Integer.parseInt(hr);
//                        mi+=d.charAt(3);
//                        mi+=d.charAt(4);
//                        int miz=Integer.parseInt(mi);
//                        se+=d.charAt(6);
//                        se+=d.charAt(7);
//                        int sez=Integer.parseInt(se);
                        d=d.substring(2);
                       LocalTime t= LocalTime.parse(d);
                        Time tt=Time.valueOf(t);
                        //System.out.println(d);
                        time.add(tt);
                    }
        }
        System.out.println(companyCode);
        System.out.println(stockExchange);
        System.out.println(currentPrice);
        System.out.println(date);
        System.out.println(time);
        for(int i=0;i<companyCode.size();i++)
        {

            //System.out.println(row);

stockPriceEntity st=new stockPriceEntity();
st.setCompanyCode((long)(companyCode.get(i)));
st.setCurrentPrice(currentPrice.get(i));
st.setDate(date.get(i));
st.setTime(time.get(i));
st.setStockExchange(stockExchange.get(i));
            /*PreparedStatement st = connect.prepareStatement("INSERT INTO stock_price_entity(stockstore.stock_price_entity.company_code, " +
                    "stockstore.stock_price_entity.current_price," +
                    "stockstore.stock_price_entity.date," +
                    "stockstore.stock_price_entity.stock_exchange," +
                    "stockstore.stock_price_entity.time)" + " values (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            st.setInt(1,companyCode.get(i) );
            st.setDouble(2,currentPrice.get(i));
            st.setDate(3, date.get(i));
            st.setString(4, stockExchange.get(i));
            st.setTime(5,time.get(i));
            st.executeUpdate();*/
           // st.setString
er.save(st);
        }
    }

    private String month(String mon)
    {
        if(mon.equals("Jan")) return "01";
        if(mon.equals("Feb")) return "02";
        if(mon.equals("Mar")) return "03";
        if(mon.equals("Apr")) return "04";
        if(mon.equals("May")) return "05";
        if(mon.equals("Jun")) return "06";
        if(mon.equals("Jul")) return "07";
        if(mon.equals("Aug")) return "08";
        if(mon.equals("Sep")) return "09";
        if(mon.equals("Oct")) return "10";
        if(mon.equals("Nov")) return "11";
        if(mon.equals("Dec")) return "12";
        else return "Invalid";

    }

    private void numhand(int hc, Cell cell)
    {
        if(hc%5==3)
        {
            ls.add(cell.getDateCellValue());
        }
        else
        {
            ls.add(cell.getNumericCellValue());
        }
    }

    private File multipartFileToFile(MultipartFile file) throws IOException {
        File convFile = new File("src/main/resources/targetFile2.tmp");
        try (OutputStream os = new FileOutputStream(convFile)) {
            os.write(file.getBytes());
        }
        return convFile;
    }
    public void exceltoDB()
    {

    }

}