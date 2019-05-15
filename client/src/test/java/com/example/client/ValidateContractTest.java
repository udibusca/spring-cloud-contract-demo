package com.example.client;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureStubRunner(ids = { "com.example:demo:+:stubs:8080" }, workOffline = true)
public class ValidateContractTest {

	private RestTemplate restTemplate = new RestTemplate();
	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void validateContract() throws Exception {
		String url = "http://127.0.0.1:8080/demo";
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

		assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(responseEntity.getHeaders().getContentType(), equalTo(MediaType.APPLICATION_JSON));
		assertThat(responseEntity.getBody(), equalTo("{\"valid\":true}"));

	}

}
