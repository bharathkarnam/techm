package com.digital.tech.techm.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.digital.tech.techm.controller.test.ControllerTest;

@SpringBootTest
@RunWith(Suite.class)
@ActiveProfiles("test")
@SuiteClasses({ControllerTest.class})
public class Testsuite {

}
