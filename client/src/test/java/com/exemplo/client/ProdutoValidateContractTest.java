package com.exemplo.client;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.exemplo.client.Produto;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureStubRunner(ids = { "com.exemplo:demo:+:stubs:8080" }, workOffline = true)
public class ProdutoValidateContractTest {

	private RestTemplate restTemplate = new RestTemplate();

	@Test
	public void test_post_product() throws Exception {

		URI productPostUrl = new URI("http://127.0.0.1:8080/produto");
		Produto product = new Produto(null, UUID.randomUUID().toString());
		HttpEntity<Produto> request = new HttpEntity<>(product);
		ResponseEntity<String> responseEntity = restTemplate.exchange(productPostUrl, HttpMethod.POST, request,
				String.class);
		System.out.println(responseEntity.getBody());

		assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.CREATED));
		assertThat(responseEntity.getHeaders().getContentType(), equalTo(MediaType.APPLICATION_JSON));
		assertTrue(responseEntity.getBody().contains("\"id\":"));
		assertTrue(responseEntity.getBody().contains("\"name\":"));


		DocumentContext parsedJson = JsonPath.parse(responseEntity.getBody().toString());
		System.out.println(parsedJson.json().toString());
		assertThatJson(parsedJson).field("['id']")
				.matches("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}");
		assertThatJson(parsedJson).field("['name']").isEqualTo(product.getName());

	}

}
