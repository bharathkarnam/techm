package com.digital.tech.techm.controller.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.digital.tech.service.model.User;
import com.digital.tech.techm.common.CommonUtil;
import com.digital.tech.techm.controller.MainController;
import com.digital.tech.techm.serviceImpl.UserEntityServiceImpl;
import com.digital.tech.techm.swagger.config.CommonConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
public class ControllerTest {
	@InjectMocks
	MainController controller;
	@Mock
	UserEntityServiceImpl userservice;

	MockHttpServletRequest request = new MockHttpServletRequest();

	User usr = CommonUtil.buildUserDetails();

	 ResponseEntity<String> ok = new ResponseEntity<String>(new Gson().toJson(usr), HttpStatus.OK);

	@Test
	public void testInsertUserDetails() throws Exception {
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(userservice.insertUser((User) any(User.class))).thenReturn(usr);
		ResponseEntity<String> responseEntity = controller.insertUserdetails(usr);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void testViewUser() throws Exception {
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(userservice.viewUser(any(String.class))).thenReturn(usr);
		ResponseEntity<String> responseEntity = controller.getUserDetail(usr.getUniqId());
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(responseEntity.getBody()).contains("1234");
	}

	@Test
	public void testInsertUserexception() throws Exception {
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(userservice.insertUser((User) (any(User.class)))).thenThrow(new RuntimeException("Connection closed"));
		ResponseEntity<String> responseEntity = controller.insertUserdetails(usr);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(503);
	}

	@Test
	public void testViewUserexception() throws Exception {
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(userservice.viewUser(any(String.class))).thenThrow(new RuntimeException("Connection closed"));
		ResponseEntity<String> responseEntity = controller.getUserDetail(usr.getUniqId());
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(503);
	}

	@Test
	public void testViewUser404() throws Exception {
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		ResponseEntity<String> responseEntity = controller.getUserDetail("");
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void testInsertUserDetails400TestNull() throws Exception {
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		ResponseEntity<String> responseEntity = controller.insertUserdetails(null);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void testInsertUserDetails400() throws Exception {
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		User user = CommonUtil.buildUserDetails();
		user.setName("123qwe @#$");
		ResponseEntity<String> responseEntity = controller.insertUserdetails(user);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
	}

	
}
