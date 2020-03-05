package com.digital.tech.techm.controller;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.zip.Checksum;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.bouncycastle.asn1.iana.IANAObjectIdentifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.digital.tech.service.model.User;
import com.digital.tech.techm.service.UserEntityService;
import com.digital.tech.techm.util.Constants;
import com.digital.tech.techm.util.CustomValidation;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.Api;

@RestController
@Api(tags = Constants.SWAGGER_TAGS, value = Constants.SWAGGER_VALUE)
@RequestMapping("/api")
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	UserEntityService service;

	@RequestMapping(method = RequestMethod.POST, path = Constants.INSERT_USER_DETAILS, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> insertUserdetails(@RequestBody @NotNull @Valid User user) {
		try {
			if (null != user) {
				if (CustomValidation.doCustomValidaion(user)) {
					user.setUniqId(UUID.randomUUID().toString());
					if (null != service.insertUser(user)) {
						logger.info(Constants.PROCESSED_MESSAGE + user.getUniqId());
						return new ResponseEntity<String>(new Gson().toJson(user), HttpStatus.OK);
					} else {
						logger.info(Constants.NOT_PROCESSED_MESSAGE + user.getUniqId());
						return new ResponseEntity<String>(Constants.SOMETHING_WENT_WRONG,
								HttpStatus.SERVICE_UNAVAILABLE);
					}
				} else {
					logger.error(Constants.VALIDATION_FAILED + user.getUniqId());
					return new ResponseEntity<String>(Constants.SOMETHING_WENT_WRONG_ON_YOUR_SIDE,
							HttpStatus.BAD_REQUEST);
				}
			}
			logger.error(Constants.VALIDATION_FAILED);
			return new ResponseEntity<String>(Constants.SOMETHING_WENT_WRONG_ON_YOUR_SIDE, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(Constants.NOT_PROCESSED_MESSAGE + user.getUniqId() + " message:" + e.getMessage());
			return new ResponseEntity<String>(Constants.SOMETHING_WENT_WRONG, HttpStatus.SERVICE_UNAVAILABLE);
		}

	}

	@RequestMapping(method = RequestMethod.GET, path = Constants.VIEW_USER_DETAILS_ID, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> getUserDetail(@PathVariable String id) throws URISyntaxException {
		try {
			if (!id.isEmpty()) {
				User usr = service.viewUser(id);
				if (null != usr) {
					return new ResponseEntity<String>(new Gson().toJson(usr), HttpStatus.OK);
				} else {
					return new ResponseEntity<String>(Constants.SOMETHING_WENT_WRONG, HttpStatus.SERVICE_UNAVAILABLE);
				}
			}
			return new ResponseEntity<String>(Constants.SOMETHING_WENT_WRONG_ON_YOUR_SIDE, HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			return new ResponseEntity<String>(Constants.SOMETHING_WENT_WRONG, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = Constants.PING_SERVICE, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, String>> Ping() {
		Map<String, String> response = new HashMap<>();
		response.put(Constants.MESSAGE, Constants.I_AM_ALIVE);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}