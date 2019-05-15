package com.example.demo;

import com.example.demo.rest.DemoRestController;
import com.example.demo.rest.PessoaRestController;
import com.example.demo.rest.ProdutoRestController;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

public class MockMvcTest {

	/**
	 * Classe de configuracao > Passar os controller que serão testados.
	 */
	@Before
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(
				new DemoRestController(),
				new ProdutoRestController(),
				new PessoaRestController()
		);
	}

}
