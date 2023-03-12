package com.mc.mvc.Infra.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatCompletionResponse {
	
	private String message;
	private String role;
	private Long created;
}
