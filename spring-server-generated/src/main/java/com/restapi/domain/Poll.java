package com.restapi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Poll {

	@JsonProperty("id")
	private int id;

	@JsonProperty("question")
	private String question;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}
