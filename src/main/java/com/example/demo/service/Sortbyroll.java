package com.example.demo.service;

import java.util.Comparator;

import com.example.demo.entity.Ipo;

import dto.Ipodto;

public class Sortbyroll implements Comparator<Ipo> {
	public int compare(Ipo a, Ipo b)
    {
		if(a.getDate()==b.getDate())
        return (a.getTime()).compareTo(b.getTime());
		return (a.getDate()).compareTo(b.getDate());
    }
}
