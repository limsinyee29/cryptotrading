package com.practice.cryptotrading.restservice;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Sin Yee
 *
 */
@Component
public class RestService {

	private RestTemplate restTemplate;

	protected ObjectMapper objectMapper;

	@PostConstruct
	public void postConstruct() {
		this.restTemplate = new RestTemplateBuilder().build();
		this.objectMapper = new ObjectMapper();
	}

	protected byte[] request(HttpMethod method, String url) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		try {
			ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url, method, null, byte[].class);
			return responseEntity.getBody();

		} catch (RestClientResponseException e) {
			throw new RestServiceException(e);
		}
	}

	public <T> T get(String url, String responseField, Class<T> responseClass) {
		return getResponseBody(request(HttpMethod.GET, url), responseField, responseClass);
	}

	public <T> T get(String url, Class<T> responseClass) {
		return getResponseBody(request(HttpMethod.GET, url), responseClass);
	}

	/**
	 * @param body
	 * @param responseClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> T getResponseBody(byte[] body, Class<T> responseClass) {
		if (responseClass.equals(String.class)) {
			return (T) new String(body);
		} else if (responseClass.equals(Integer.class)) {
			return (T) Integer.valueOf(new String(body));
		} else if (responseClass.equals(Long.class)) {
			return (T) Long.valueOf(new String(body));
		} else if (responseClass.equals(Double.class)) {
			return (T) Double.valueOf(new String(body));
		} else if (responseClass.equals(Boolean.class)) {
			return (T) Boolean.valueOf(new String(body));
		} else {
			try {
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				return mapper.readValue(body, responseClass);
			} catch (StreamReadException e) {
				throw new RestServiceException(e);
			} catch (DatabindException e) {
				throw new RestServiceException(e);
			} catch (IOException e) {
				throw new RestServiceException(e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private <T> T getResponseBody(byte[] body, String field, Class<T> responseClass) {
		if (responseClass.equals(String.class)) {
			return (T) new String(body);
		} else if (responseClass.equals(Integer.class)) {
			return (T) Integer.valueOf(new String(body));
		} else if (responseClass.equals(Long.class)) {
			return (T) Long.valueOf(new String(body));
		} else if (responseClass.equals(Double.class)) {
			return (T) Double.valueOf(new String(body));
		} else if (responseClass.equals(Boolean.class)) {
			return (T) Boolean.valueOf(new String(body));
		} else {
			try {
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				JsonNode result = mapper.readTree(body).get(field);
				return mapper.treeToValue(result, responseClass);
			} catch (StreamReadException e) {
				throw new RestServiceException(e);
			} catch (DatabindException e) {
				throw new RestServiceException(e);
			} catch (IOException e) {
				throw new RestServiceException(e);
			}
		}
	}
}