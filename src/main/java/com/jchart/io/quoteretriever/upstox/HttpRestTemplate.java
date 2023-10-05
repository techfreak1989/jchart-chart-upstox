package com.jchart.io.quoteretriever.upstox;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class HttpRestTemplate {

	private final RestTemplate restTemplate;
//	private final Duration timeout = Duration.ofSeconds(30);

	public <T> Optional<T> sendGetRequest(URI uri, HttpHeaders headers, String correlationId, Class<T> elementClass) {
		try {
			ResponseEntity<T> response = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers), elementClass);
			logResponseSuccess(uri, correlationId, response);
			return Optional.of(response.getBody());
		} catch (Exception e) {
			logError(uri, correlationId, e);
		}
		return Optional.empty();
	}
	
	public <T> Optional<T> sendPostRequest(URI uri, HttpHeaders headers, String correlationId, Object entity, Class<T> elementClass) {
		try {
			ResponseEntity<T> response = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(entity, headers), elementClass);
			logResponseSuccess(uri, correlationId, response);
			return Optional.of(response.getBody());
		} catch (Exception e) {
			logError(uri, correlationId, e);
		}
		return Optional.empty();
	}
	
	public <T> Optional<T> sendPutRequest(URI uri, HttpHeaders headers, String correlationId, Object entity, Class<T> elementClass) {
		try {
			ResponseEntity<T> response = restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(entity, headers), elementClass);
			logResponseSuccess(uri, correlationId, response);
			return Optional.of(response.getBody());
		} catch (Exception e) {
			logError(uri, correlationId, e);
		}
		return Optional.empty();
	}
	
	public <T> Optional<T> sendPatchRequest(URI uri, HttpHeaders headers, String correlationId, Object entity, Class<T> elementClass) {
		try {
			ResponseEntity<T> response = restTemplate.exchange(uri, HttpMethod.PATCH, new HttpEntity<>(entity, headers), elementClass);
			logResponseSuccess(uri, correlationId, response);
			return Optional.of(response.getBody());
		} catch (Exception e) {
			logError(uri, correlationId, e);
		}
		return Optional.empty();
	}

	private void logError(URI uri, String correlationId, Exception e) {
		 log.error("Received error  for uri: {}, error: {} ,correlationId: {}", uri, "CommonUtils.getErrorMessage(e)"+e.getMessage(), correlationId, e);
	}

	private void logResponseSuccess(URI uri, String correlationId, ResponseEntity<?> response) {
		 log.info("Received success response for uri: {}, correlationId: {}, response: {}", uri, correlationId, response);
	}
	
	public HttpHeaders buildHeaders(String source, String token) {
		HttpHeaders httpHeaders = new HttpHeaders();
		if(StringUtils.hasText(token))
			httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer ".concat(token));
		return httpHeaders;
	}
}
