package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import dto.Userdto;
@Service
public class UserService {
@Autowired
UserRepository userrepo;
	public void addUser(Userdto userdto) {
/*		User user=new User();
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
	user.setUsertype(userdto.getRole());
		userrepo.save(user);
	}*/

}}
