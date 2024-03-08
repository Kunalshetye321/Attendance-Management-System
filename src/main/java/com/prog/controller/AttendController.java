package com.prog.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prog.entity.Attendance;
import com.prog.entity.User;
import com.prog.service.UserService;

import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AttendController {

	@Autowired
	private UserService service;
	int flag = 1;
	
	@GetMapping("/")
	public String login(HttpSession session)
	{
		if(flag == 1)
		{
			System.out.println(flag);
			session.removeAttribute("msg");
			flag--;
		}
		
		return "login";
	}
	
	@GetMapping("/register")
	public String register(HttpSession session)
	{
//		if(!session.isNew())
//		{
//			session.invalidate();
//		}
		return "register";
	}
	
//	@PostMapping("/addNewUser")
//	public String addNewUser(@ModelAttribute User user, HttpSession session)
//	{
//		boolean flag = service.AddNewUser(user);
//		if(flag == false)
//		{
//			session.setAttribute("msg", "User already exist");
//			return "/register";
//		}
//		return "redirect:/";
//	}
	
	//RequestBody :- JSON->Java Class
		@PostMapping("/addNewUser")
		public ResponseEntity<?> addNewUser(@RequestBody User user)
		{
			System.out.println("Register User Controller");
			return new ResponseEntity<>(service.AddNewUser(user),HttpStatus.CREATED);
			//-> WHAT IS REGISTER FAIL NAVIGATE TO SAME PAGE
			//-> NAVIGATE TO LOGIN
		}
	
//	@PostMapping("/checklogin")
//	public String checkLogin(@ModelAttribute User user, Model m, HttpSession session)
//	{
//		
//		System.out.println(user);
//		boolean flag = service.CheckUser(user);
//		if(flag == true)
//		{
//			if(user.getUsername().equals("admin") && user.getPassword().equals("admin"))
//			{
//				List<User> allUser = service.getAllUser();
//				System.out.println(allUser);
//				m.addAttribute("allUser", allUser);
//				return "admin";
//			}
//			else {
//				m.addAttribute("user",user);
//				return "home";
//			}
//		}
//		
//		session.setAttribute("msg", "Invalid Username/Password");
//		return "redirect:/temp";
//	}
		@GetMapping("/checklogin")
		public ResponseEntity<?> checkLogin()
		{
			/*
			
			System.out.println(user);
			boolean flag = service.CheckUser(user);
			
			//LOGIN SUCCESSFULL
			if(flag == true)
			{
				//IF LOGIN IS ADMIN
				if(user.getUsername().equals("admin") && user.getPassword().equals("admin"))
				{
					List<User> allUser = service.getAllUser();
					System.out.println(allUser);
					m.addAttribute("allUser", allUser);
					return "admin";
				}
				else {
					m.addAttribute("user",user);
					return "home";
				}
			}
			
			//If Login Failed
			//Go to Home
			session.setAttribute("msg", "Invalid Username/Password");
			return "redirect:/temp";
			*/
			//JUST RETURN LIST AND DO NAVIGATION AND RENDERING IN FRONT
			
			return new ResponseEntity<>(service.getAllUserNew(), HttpStatus.OK);
		}
	
	@GetMapping("/temp")
	public String login() {
		return "redirect:/";
	}
	
//	@PostMapping("/storeAttendance")
//	public String storeAttendance(@ModelAttribute Attendance att, Model m, HttpSession session)
//	{
//		System.out.println(att);
////		boolean setRecord = 
//		service.saveAttendance(att);
//		
////		if(setRecord == false)
////		{
////			System.out.println("Duplicate Attendance");
////			session.setAttribute("msg", "Attendance already marked");
////			return "redirect:/";
////		} 
//		m.addAttribute("att", att);
//		return "home2";
//	}
	//RequestBody :- JSON->Java Class
		@PostMapping("/storeAttendance")
		public ResponseEntity<?> storeAttendance(@RequestBody Attendance att)
		{
//			att.setUsername("Mkla");
//			att.setDate("100");
//			att.setTime1("19028");
//			att.setTime2("832");
			//->System.out.println(att);
			//->boolean setRecord = 
			//->service.saveAttendance(att);
			System.out.println("Save Check In Attendance Controller");
			System.out.println(att.getUsername());
			return new ResponseEntity<>(service.saveAttendance(att),HttpStatus.CREATED);
			

			//->m.addAttribute("att", att);
			//->return "home2"; NAVIGATE
		}
		
		
//	@PostMapping("/storeAttendance2")
//	public String storeAttendance2(@ModelAttribute Attendance att, Model m)
//	{
//		System.out.println(att);
//		service.saveAttendance(att);
//		m.addAttribute("user", att);
//		return "home";
//	}
	
//	@PostMapping("/storeAttendance3")
//	public String storeAttendance3(@ModelAttribute Attendance att, Model m,HttpSession session)
//	{
//		System.out.println(att);
//		service.saveAttendance2(att);
//		m.addAttribute("user", att);
//		return "home";
//	}
	
	@PostMapping("/lastAtt")
	public ResponseEntity<?> GetLastAtt()
	{
		return new ResponseEntity<>(service.getLastAtt(),HttpStatus.OK);
	}
	
	@PostMapping("/editProduct/{id}")
	public ResponseEntity<?> editProduct(@RequestBody Attendance attendance, @PathVariable Integer id)
	{
		System.out.println("time2 update karle"+attendance);
		
		
		
//		attendance.setTime2(new SimpleDateFormat("HH:mm:ss").format(new Date()).toString());
		// Create a SimpleDateFormat object to format the date and time
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");

        // Get the current date and time
        Date date = new Date();

        // Format the date and time
        String time = sdf.format(date);
        if (time.endsWith("pm")) {
            time = time.replaceFirst("pm", "PM");
        }
        if (time.endsWith("am")) {
            time = time.replaceFirst("am", "AM");
        }
        
        attendance.setTime2(time);
        
        
        
        
        
        
		return new ResponseEntity<>(service.editProduct(attendance, id),HttpStatus.CREATED);
	}
	
//	@GetMapping("/viewAttendance/{username}")
//	public String viewAttendance(@PathVariable String username, Model m, HttpSession session)
//	{
////		System.out.println("View Record");
//		List<Attendance> att = service.findAllByUsername(username);
//		
//		for(int i = 0; i < att.size(); i++)
//		{
//			
//			System.out.println(att.get(i).getTime1() + "," + att.get(i).getTime2());
//			String[] t1 = att.get(i).getTime1().split(":");
//			String[] t2 = att.get(i).getTime2().split(":");
//			
//			int a = Integer.parseInt(t1[0]);
//			int b = Integer.parseInt(t2[0]);
//			int c = Integer.parseInt(t1[1]);
//			int d = Integer.parseInt(t2[1]);
//			
//			System.out.println("a="+a+", b="+b+", c="+c+", d="+d);
//			
//			int res = -100;
//			if(c == d)
//			{
//				res = b-a;
//			}
//			else if(c < d)
//			{
//				res = b-a;
//			}
//			else if(c > d)
//			{
//				res = b-a-1;
//			}
//			System.out.println(res);
////			System.out.println(b-a);
//			att.get(i).setDate(att.get(i).getDate()+Integer.toString(res));
//			//System.out.println("Total Hours: " + calculateTotalHours(res.getTime1(), res.getTime2()));
//		}
//		m.addAttribute("attList", att);
//		session.setAttribute("username", username);
//		return "viewAttendance";
//	}
//}
	@PostMapping("/viewAttendance/{username}")
	public ResponseEntity<?> viewAttendance(@PathVariable String username)
	{
//		System.out.println("View Record");
//		List<Attendance> att = service.findAllByUsername(username);
		return new ResponseEntity<>(service.findAllByUsername(username), HttpStatus.OK);
		
	}
	}


