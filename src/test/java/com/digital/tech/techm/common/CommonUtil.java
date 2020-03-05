package com.digital.tech.techm.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.LifecycleListener;

import com.digital.tech.service.model.User;

public class CommonUtil {
	
	public static User buildUserDetails() {
		List<String> skill=new ArrayList<String>();
		skill.add("kotlin");
		User usr = new User();
		String address = "Australia melbourne";
		usr.setAddress(address);
		usr.setDesignation("software dev");
		usr.setMobilenumber((long) 431424709);
		usr.setName("bharath");
		usr.setRole("Development");
		usr.setSkills(skill);
		usr.setTotalyearsOfexperience(8.0);
		usr.setUniqId("1234");
		return usr;

	}

}
