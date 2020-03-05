package com.digital.tech.techm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.digital.tech.service.model.User;

public class CustomValidation {

	public static boolean doCustomValidaion(User user) {				
		try {
			boolean res =true; 
			Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\s]*$");
		     for (String skill: user.getSkills()) {
		    	 System.out.println(skill); 
		    	 res =pattern.matcher(skill).find();
		    	}	   
		     if(res) {
			    return pattern.matcher(user.getDesignation()).find() && pattern.matcher(user.getAddress()).find() 
			    		&& pattern.matcher(user.getName()).find() && pattern.matcher(user.getRole()).find() ;
		     } 
		     return res;
		} catch (Exception e) {
			return false;
		}
		
		
	}

}
