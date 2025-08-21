package com.poss.utils;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Meta {
	private String transactionID;
	private String status;
	private Integer statusCode;
	private String timestamp;
	private String devMessage;
	private String message;

	public Meta(
		String transactionID,
		String status,
		Integer statusCode
	) {
		this.transactionID = transactionID;
		this.status = status;
		this.statusCode = statusCode;
		this.timestamp = LocalDateTime.now().toString();
	}

	public Meta(
		String transactionID,
		String status,
		Integer statusCode,
		String devMessage
	) {
		this.transactionID = transactionID;
		this.status = status;
		this.statusCode = statusCode;
		this.timestamp = LocalDateTime.now().toString();
		this.devMessage = devMessage;
	}

	public Meta(
		String transactionID,
		String status,
		Integer statusCode,
		String devMessage,
		String message
	) {
		this.transactionID = transactionID;
		this.status = status;
		this.statusCode = statusCode;
		this.timestamp = LocalDateTime.now().toString();
		this.devMessage = devMessage;
		this.message = message;
	}
}
