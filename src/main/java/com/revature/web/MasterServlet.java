

package com.revature.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.AcountsController;

import com.revature.models.Record;

//import com.revature.controllers.AvengerController;
//import com.revature.controllers.HomeController;
//import com.revature.controllers.LoginController;
//import com.revature.models.Avenger;
import com.revature.controllers.LoginController;
import com.revature.models.CreatUserDTO;
import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOimpl;

public class MasterServlet extends HttpServlet {

	private static final ObjectMapper om = new ObjectMapper();
//	private static final AvengerController ac = new AvengerController();
//	private static final HomeController hc = new HomeController();
	private static final LoginController lc = new LoginController();
	private static final AcountsController ac=new AcountsController();
	private static final UserDAO ud=UserDAOimpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("application/json");
		// this will set the default response to not found; we will change it later if
		// the request was successful
		res.setStatus(404);

		final String URI = req.getRequestURI().replace("/RocpFirst/", "");

		String[] portions = URI.split("/");

		System.out.println(Arrays.toString(portions));
		
//		if(req.getMethod().equalsIgnoreCase("GET"))System.out.println("I am in Get");
//		if(req.getMethod().equalsIgnoreCase("Post"))System.out.println("I am in post");
		
		try {
			switch (portions[0]) {
			case "login":
				lc.login(req, res);
				break;
			case "logout":
				lc.logout(req, res);
				break;
			case "accounts":
				HttpSession ses = req.getSession(false);
				if (ses != null && ((Boolean) ses.getAttribute("loggedin"))) {
					
					LoginDTO u=(LoginDTO) ses.getAttribute("user");
					
				    User user= ud.findByUserNameInUsersList(u.username);
				    System.out.println(user.toString());
					
					List<Record> list= ac.findAll((int) user.getUserId());
					
					res.getWriter().println("the accounts for "+user.getFirstName()+
							" "+user.getLastName() + " are:</p>" );
					for(Record rec: list) {
//					String json = om.writeValueAsString(rec);
//					res.getWriter().println(json+"</p>");
					res.getWriter().println(rec.toString());	
						
					}
					
				}else {
					res.setStatus(401);
					res.getWriter().println("You must be logged in to do that!");
				}
				break;
				
			case "createAccount":
				    HttpSession ses1 = req.getSession(false);
                    if (ses1 != null && ((Boolean) ses1.getAttribute("loggedin"))) {
					
					LoginDTO u=(LoginDTO) ses1.getAttribute("user");
					
				    User user= ud.findByUserNameInUsersList(u.username);
				    System.out.println(user.toString());
				    
				    if (user.getRole().getRole().equalsIgnoreCase("Admin")) {
				    	
				    	if (req.getMethod().equals("POST")) {
				    		
				    		BufferedReader reader = req.getReader();
							
							StringBuilder s = new StringBuilder();
							
							String line = reader.readLine();
							
							while(line != null) {
								s.append(line);
								line=reader.readLine();
							}
							
							String body = new String(s);
							
							System.out.println(body);
							
							CreatUserDTO l = om.readValue(body, CreatUserDTO.class);
							if (ud.insert(l)) {
								System.out.println("a new user is created");
							}else {
								System.out.println("No new user is created: username has been taken!");
							}
							if (ud.insertRecord(l)) {
								System.out.println("money deposited");
							}else {
							     System.out.println("nothing deposited");
							};
							
				    	}else {
				    		res.setStatus(401);
							res.getWriter().println("You must use POST method!");
				    	}
				    	
			    	
				    	
				    }else {
				    	res.setStatus(401);
						res.getWriter().println("You must be admin to create accounts!");
				    }
				    
	
					} else {
					res.setStatus(401);
					res.getWriter().println("You must be logged in to do that!");
				}
				break;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				res.getWriter().println("The id you provided is not an integer");
				res.setStatus(400);
			}

		}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
