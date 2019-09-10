package com.exemplo.demo;

import com.exemplo.demo.rest.DemoRestController;
import com.exemplo.demo.rest.PessoaRestController;
import com.exemplo.demo.rest.ProdutoRestController;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

public class MockMvcTest {

	/**
	 * Classe de configuracao > Passar os controller que serão testados.
	 * 3 controllers adicionados.
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
