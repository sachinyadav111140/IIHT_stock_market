package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;




import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;





import dto.Perioddto;
import dto.Resultdto;
import dto.StockPricedto;
import dto.Userdto;
@RestController
public class UserController {
	@Autowired
	UserRepository userrepo;
	@Autowired
	UserService userservice;
	 @CrossOrigin(origins ="*")
	@Transactional
	@PostMapping("/login")
	public Resultdto login(@RequestBody Userdto userdto)
	{
		System.out.print(userdto.getEmail());
		System.out.print(userdto.getPassword());
		System.out.print(userdto.getUsertype());
		User user=userrepo.findByEmailAndPasswordAndUsertypeAndConfirmed(userdto.getEmail(),userdto.getPassword(),
				userdto.getUsertype(),true);
		if(user!=null)
		{
			Resultdto resu=new Resultdto();
			resu.setRes(true);
			resu.setType(userdto.getUsertype());
	System.out.print(resu.getRes());
	System.out.print(resu.getType());	
	return resu;
		}
		Resultdto res1=new Resultdto();
		res1.setRes(false);
		res1.setType(userdto.getUsertype());
		return res1;
	}	

	/* @RequestMapping(value="/allUsers", method= RequestMethod.GET)
	    public List<UserEntity> x()
	    {
	        return userrepo.findAll();
	    }*/

	

	    @CrossOrigin(origins ="*", allowedHeaders = "*")
	    @RequestMapping(value = "/setuserapi2",method=RequestMethod.POST, headers = "Accept=application/json"  )
	    public  ResponseEntity<Object> reactuserapi2(@RequestBody Userdto user) throws ClassNotFoundException, IOException {
System.out.print(user.getUsertype());
	    	User us=new User();
us.setConfirmed(false);
us.setName(user.getName());
us.setEmail(user.getEmail());
us.setNum(user.getNum());
us.setEmail(user.getEmail());
us.setPassword(user.getPassword());
us.setUsertype(user.getUsertype());
User usrsaved = userrepo.save(us);
	        // make sure your entity class properties of user are in lower case and match the json,to avoid errors
	        System.out.println(user +"check this " +usrsaved.getName());

	        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usrsaved.getId())
	                .toUri();
	        sendemail(usrsaved.getId()) ;

	        return ResponseEntity.created(location).build();
	    }


	    @CrossOrigin("*")
	    public void sendemail(Long userid)
	    {


Optional<User> user=userrepo.findById(userid);
User us=user.get();
String mail=us.getEmail();

	        final String username = "cheggexpert1337@gmail.com";
	        final String password = "LetsLearn@123";

	        Properties prop = new Properties();
	        prop.put("mail.smtp.host", "smtp.gmail.com");
	        prop.put("mail.smtp.port", "587");
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.starttls.enable", "true"); //TLS

	        Session session = Session.getInstance(prop,
	                new javax.mail.Authenticator() {
	                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
	                        return new javax.mail.PasswordAuthentication(username, password);
	                    }
	                });

	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(mail));
	            message.setRecipients(
	                    Message.RecipientType.TO,
	                    InternetAddress.parse(mail)
	            );
	            message.setSubject("User Registration confirmation email");
	            //     message.setText("Dear Mail Crawler,"
	            //           + "\n\n Please do not spam my email!");
	            message.setContent(
	                    "<h1><a href =\"http://127.0.0.1:8080/confirmuser/"+userid+"/\"> Click to confirm </a></h1>",
	                    "text/html");
	            Transport.send(message);

	            System.out.println("Done");

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }


	    @CrossOrigin("*")
	    @RequestMapping(value="/confirmuser/{userid}", method=RequestMethod.GET)
	    public String welcomepage(@PathVariable Long userid) throws EntityNotFoundException {
	        Optional<User> userlist =   userrepo.findById(userid);
	        //do a null check for home work
	        User usr = new User();
	        usr = userrepo.getById(userid);
	        usr.setConfirmed(true);
	        userrepo.save(usr);
	        return "User confirmed " +usr.getName();
	    }







}
