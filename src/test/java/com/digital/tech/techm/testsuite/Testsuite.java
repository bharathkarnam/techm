package com.digital.tech.techm.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.digital.tech.techm.controller.test.ControllerTest;
import com.digital.tech.techm.service.test.UserServiceImlTest;


@SpringBootTest
@RunWith(Suite.class)
@SuiteClasses({ControllerTest.class, UserServiceImlTest.class})
public class Testsuite {

}
