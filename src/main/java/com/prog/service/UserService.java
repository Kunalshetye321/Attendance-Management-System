package com.prog.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.entity.Attendance;
import com.prog.entity.User;
import com.prog.repository.AttendanceRepo;
import com.prog.repository.UserRepo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class UserService {
	
	@Autowired
	private UserRepo repo1;
	
	@Autowired
	private AttendanceRepo repo2;

	public boolean AddNewUser(User usr)
	{
		List<User> usr1 = repo1.findAll();
		for(int i = 0; i < usr1.size(); i++)
		{
			User usr2 = usr1.get(i);
			if(usr2.getUsername().equals(usr.getUsername()) && usr2.getPassword().equals(usr.getPassword())
					&& usr2.getEmail().equals(usr.getEmail()) && usr2.getPhone().equals(usr.getPhone()))
			{
				return false;
			}
		}
		repo1.save(usr);
		return true;
	}
	
	public Attendance getLastAttendance()
	{
		List<Attendance> allAtt = repo2.findAll();
		return allAtt.get(allAtt.size()-1);
	}
	
	public List<User> getAllUser()
	{
		List<User> users = repo1.findAll();
		for(int i = 0; i < users.size(); i++)
		{
			if( users.get(i).getUsername().equals("admin") && users.get(i).getPassword().equals("admin") )
			{
				//Except admin return all users
				users.remove(i);
			}
		}
		return users;
	}
	
	//NEW
	public List<User> getAllUserNew()
	{
		return repo1.findAll();
	}
	
	
	public boolean CheckUser(User usr)
	{
		String uname = usr.getUsername();
		String pwd = usr.getPassword();
		List<User> allUser = repo1.findAll();
		for(int i = 0; i < allUser.size(); i++)
		{
			User usr2 = allUser.get(i);
			if(usr2.getUsername().equals(uname) && usr2.getPassword().equals(pwd))
			{
				return true;
			}
		}
		return false;
	}
	
	public Attendance getLastAtt()
	{
		List<Attendance> att =  repo2.findAll();
		return att.get(att.size()-1);
	}
	
	public Attendance editProduct(Attendance p, Integer id) {
		Attendance oldProduct =  repo2.findById(id).get();
		oldProduct.setUsername(p.getUsername());
		oldProduct.setTime1(p.getTime1());
		oldProduct.setTime2(p.getTime2());
		oldProduct.setDate(p.getDate());

		return repo2.save(oldProduct);
	}
	
	public boolean saveAttendance(Attendance att)
	{
//		List<Attendance> att1 = repo2.findAll();
//		for(int i = 0; i < att1.size(); i++)
//		{
//			Attendance att2 = att1.get(i);
//			if(att2.getDate().equals(att.getDate()) && att2.getUsername().equals(att.getUsername())
//					&& !att.getTime1().isEmpty())
//			{
//				System.out.println("Duplicate Entry" +att.getUsername());
//				return false;
//			}
//		}
		repo2.save(att);
		return true;
	}
	
	public void saveAttendance2(Attendance att)
	{
//		List<Attendance> att1 = repo2.findAll();
//		for(int i = 0; i < att1.size(); i++)
//		{
//			Attendance att2 = att1.get(i);
//			if(att2.getDate().equals(att.getDate()))
//			{
//				return;
//			}
//		}
		repo2.save(att);
		return;
	}
	
	//Get attendance of particular user
//	public List<Attendance> findAllByUsername(String Username)
//	{
//		System.out.println(Username.toString());
//		List<Attendance> att = repo2.findAll();
//		List<Attendance> att2 = new ArrayList<>();
//		for(int i = 0; i < att.size(); i++)
//		{
//			Attendance att3 = att.get(i);
//			if(att3.getUsername().equals(Username.toString()) )
//			{
//				att2.add(att3);
//			}
//		}
//		
//		return att2;
//	}
	public List<Attendance> findAllByUsername(String username) {
	    System.out.println(username);

	    List<Attendance> allAttendances = repo2.findAll();
	    List<Attendance> userAttendances = new ArrayList<>();

	    for (Attendance attendance : allAttendances) {
	        String currentUsername = attendance.getUsername();

	        // Check for null and perform case-insensitive comparison
	        if (username != null && username.equalsIgnoreCase(currentUsername)) {
	            userAttendances.add(attendance);
	        }
	    }

	    return userAttendances;
	}
	
	
}
