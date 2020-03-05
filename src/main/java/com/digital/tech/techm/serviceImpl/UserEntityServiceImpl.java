package com.digital.tech.techm.serviceImpl;

import java.net.URI;
import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.digital.tech.service.model.User;
import com.digital.tech.techm.service.UserEntityService;
import com.digital.tech.techm.util.Constants;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@Service
public class UserEntityServiceImpl implements UserEntityService {


	private Logger logger = LoggerFactory.getLogger(UserEntityServiceImpl.class);
	
	@Autowired
	private RestTemplate restTemplate;
	

	@Value("${mongoWritePOST}")
	private URI urlPOST;

	@Value("${mongoWriteGET}")
	private URI urlGET;

	@HystrixCommand(fallbackMethod = "fallbackInsertUser")
	public User insertUser(User user) {
		User response = restTemplate.postForObject(urlPOST, user, User.class);
		return response;
	}

	@HystrixCommand(fallbackMethod = "fallbackViewUser")
	public User viewUser(String id) throws URISyntaxException {
		User response = restTemplate.postForObject(urlGET, id, User.class);
		return response;
	}
	
	public User fallbackViewUser(String id) {
		logger.error(""+id+ Constants.ERROR_MESSAGE);
		return null;
	}
	
	public User fallbackInsertUser(User user) {
		logger.error(""+user.getUniqId()+ Constants.ERROR_MESSAGE);
		return null;
	}

}