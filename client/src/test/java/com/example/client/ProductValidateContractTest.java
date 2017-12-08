package com.example.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureStubRunner(ids = {"com.example:demo:+:stubs:8080"}, workOffline = true)
public class ProductValidateContractTest {

	private RestTemplate restTemplate = new RestTemplate();

	@Test
	public void test_post_product() throws Exception {
		/*****************************************************************************************************************************************
		 * Spring Cloud Contract comes with a series of predefined regular expressions that you can use in your contracts,
		 * as shown in the following example:
		 * http://cloud.spring.io/spring-cloud-static/spring-cloud-contract/1.2.1.RELEASE/single/spring-cloud-contract.html#_regular_expressions
		 *****************************************************************************************************************************************/
		// protected static final Pattern ONLY_ALPHA_UNICODE = Pattern.compile(/[\p{L}]*/)
		// protected static final Pattern TRUE_OR_FALSE = Pattern.compile(/(true|false)/)
		// protected static final Pattern NUMBER = Pattern.compile('-?\\d*(\\.\\d+)?')
		// protected static final Pattern IP_ADDRESS = Pattern.compile('([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])')
		// protected static final Pattern HOSTNAME_PATTERN = Pattern.compile('((http[s]?|ftp):/)/?([^:/\\s]+)(:[0-9]{1,5})?')
		// protected static final Pattern EMAIL = Pattern.compile('[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}')
		// protected static final Pattern URL = UrlHelper.URL
		// protected static final Pattern UUID = Pattern.compile('[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}')
		// protected static final Pattern ANY_DATE = Pattern.compile('(\\d\\d\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])')
		// protected static final Pattern ANY_DATE_TIME = Pattern.compile('([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])')
		// protected static final Pattern ANY_TIME = Pattern.compile('(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])')
		// protected static final Pattern NON_EMPTY = Pattern.compile(/[\S\s]+/)
		// protected static final Pattern NON_BLANK = Pattern.compile(/^\s*\S[\S\s]*/)
		// protected static final Pattern ISO8601_WITH_OFFSET = Pattern.compile(/([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])(\.\d{3})?(Z|[+-][01]\d:[0-5]\d)/)


		URI productPostUrl = new URI("http://127.0.0.1:8080/product");
		Product product = new Product(null, UUID.randomUUID().toString());
		HttpEntity<Product> request = new HttpEntity<>(product);
		ResponseEntity<String> responseEntity = restTemplate.exchange(productPostUrl, HttpMethod.POST, request, String.class);

		System.out.println(responseEntity.getBody());

		assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.CREATED));
		assertThat(responseEntity.getHeaders().getContentType(), equalTo(MediaType.APPLICATION_JSON));
		assertTrue(responseEntity.getBody().contains("\"id\":"));
		assertTrue(responseEntity.getBody().contains("\"name\":"));

		DocumentContext parsedJson = JsonPath.parse(responseEntity.getBody().toString());
		System.out.println(parsedJson.json().toString());
		assertThatJson(parsedJson).field("['id']").matches("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}");
		assertThatJson(parsedJson).field("['name']").isEqualTo(product.getName());

	}

}
