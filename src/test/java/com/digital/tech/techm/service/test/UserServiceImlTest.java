package com.digital.tech.techm.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.digital.tech.service.model.User;
import com.digital.tech.techm.common.CommonUtil;
import com.digital.tech.techm.serviceImpl.UserEntityServiceImpl;
import com.digital.tech.techm.swagger.config.CommonConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration(classes = {CommonConfig.class,UserEntityServiceImpl.class})
@TestPropertySource("classpath:application.properties")
@RunWith(SpringRunner.class)
public class UserServiceImlTest {
	@Mock
	private RestTemplate restTemplate;
	
	MockHttpServletRequest request = new MockHttpServletRequest();
	
	@InjectMocks
	private UserEntityServiceImpl userservice;

	@Value("${mongoWritePOST}")
	private URI urlPOST;
	
	@Value("${mongoWriteGET}")
	private URI urlGET;
	
	User usr = CommonUtil.buildUserDetails();


	@Test
	public void testpostservice() throws Exception {
		ReflectionTestUtils.setField(userservice, "urlPOST", urlPOST);
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(restTemplate.postForObject(urlPOST, usr,User.class)).thenReturn(usr);		
        User us = userservice.insertUser(usr);
        assertThat(us).isNotNull();
	}
	
	@Test
	public void testpostserviceException() throws Exception {
		ReflectionTestUtils.setField(userservice, "urlPOST", urlPOST);
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(restTemplate.postForObject(urlPOST, usr,User.class)).thenReturn(null);		
        User us = userservice.insertUser(usr);
        assertThat(us).isNull();
	}
	
	@Test
	public void testgetservice() throws Exception {
		ReflectionTestUtils.setField(userservice, "urlGET", urlGET);
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(restTemplate.postForObject(urlGET, usr.getUniqId(),User.class)).thenReturn(usr);		
        User us = userservice.viewUser(usr.getUniqId());
        assertThat(us).isNotNull();
	}

	@Test
	public void testgetserviceException() throws Exception {
		ReflectionTestUtils.setField(userservice, "urlGET", urlGET);
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(restTemplate.postForObject(urlGET, usr.getUniqId(),User.class)).thenReturn(null);		
        User us = userservice.viewUser(usr.getUniqId());
        assertThat(us).isNull();
	}
}
