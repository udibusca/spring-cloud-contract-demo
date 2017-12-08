package com.example.demo;

import com.example.demo.rest.DemoRestController;
import com.example.demo.rest.PersonRestController;
import com.example.demo.rest.ProductRestController;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

public class MockMvcTest {

	@Before
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(
				new DemoRestController(),
				new ProductRestController(),
				new PersonRestController()
		);
	}

}
