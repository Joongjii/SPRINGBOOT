package com.mc.mvc.Infra.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.mc.mvc.Infra.api.dto.ChatCompletionResponse;
import com.mc.mvc.Infra.code.Code;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OpenAI {
	
 private final String CHAT_COMPELETION_URL = "https://api.openai.com/v1/chat/completions";
 private final String IMAGE_GENERATION_URL = "https://api.openai.com/v1/images/generations";
 private final RestTemplate restTemplate;
 
 public ChatCompletionResponse chatCompletion(String question) {
	 
	 String url="https://api.openai.com/v1/chat/completions";
		final String API_KEY =
			"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";
	
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("model", "gpt-3.5-turbo");
		body.put("messages", List.of(Map.of("role","user","content",question)));
		
		ResponseEntity<JsonNode> response =
										restTemplate.exchange(
												RequestEntity.post(CHAT_COMPELETION_URL)
												.header("Authorization", "Bearer "+ Code.OPENAI_API_KEY)
												.contentType(MediaType.APPLICATION_JSON)
												.body(body)
												,JsonNode.class);
		
		JsonNode node= response.getBody();
//		System.out.println(node);
//		System.out.println(node.at("/choices/0/message"));
//		System.out.println(node.at("/choices/0/message/content").asText());
		
		ChatCompletionResponse res = new ChatCompletionResponse();
		res.setCreated(node.at("/created").asLong());
		res.setMessage(node.at("/choices/0/message/content").asText());
		res.setRole(node.at("/choices/0/message/role").asText());
		
		return res;
 }
 
 
}
