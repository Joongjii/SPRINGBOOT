package com.mc.mvc.ai;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

@SpringBootTest
public class OpenAiTest {

	@Autowired
	private RestTemplate restTemplate;
	
	@Test
	public void testChatCOmpletion() {
		String url="https://api.openai.com/v1/chat/completions";
		final String API_KEY =
			"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";
	
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("model", "gpt-3.5-turbo");
		body.put("messages", List.of(Map.of("role","user","content","프랑스 여행짜줘")));
		
		ResponseEntity<JsonNode> response =
										restTemplate.exchange(
												RequestEntity.post(url)
												.header("Authorization", "Bearer "+ API_KEY)
												.contentType(MediaType.APPLICATION_JSON)
												.body(body)
												,JsonNode.class);
		
		JsonNode node= response.getBody();
		System.out.println(node);
		System.out.println(node.at("/choices/0/message"));
		System.out.println(node.at("/choices/0/message/content").asText());
		
	}
	
	
	@Test
	public void TestImageGenerations() {
		String url="https://api.openai.com/v1/images/generations";
		final String API_KEY =
			"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";
	
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("prompt", "cute ironman character");
		body.put("n", 1);
		body.put("size", "256x256");
		
		ResponseEntity<JsonNode> response =
										restTemplate.exchange(
												RequestEntity.post(url)
												.header("Authorization", "Bearer "+ API_KEY)
												.contentType(MediaType.APPLICATION_JSON)
												.body(body)
												,JsonNode.class);
		
		JsonNode node= response.getBody();
		System.out.println(node);
		System.out.println(node.at("/data/0/url"));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
