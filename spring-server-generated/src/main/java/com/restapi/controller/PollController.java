package com.restapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.domain.Poll;

import io.swagger.model.Pet;

@RestController
public class PollController {
	
	List<Poll> polls = new ArrayList<>();
	int startIndex = 2;
	
	public PollController(){
		getPolls();
	}

	@RequestMapping(value = "/polls", method = RequestMethod.GET)
	public ResponseEntity<List<Poll>> getAllPolls() {

		return new ResponseEntity<>(polls, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/polls", 
			produces = { "application/json", "application/xml" }, 
	        consumes = { "application/json", "application/xml" }, 
	        method = RequestMethod.POST)
	public ResponseEntity<Poll> createPoll(@RequestBody Poll body) {

		Poll poll = new Poll();
		poll.setId(startIndex + 1);
		poll.setQuestion(body.getQuestion());
		polls.add(poll);
		
		return new ResponseEntity<>(poll, HttpStatus.OK);
	}

	private List<Poll> getPolls() {
		
		Poll poll;
		poll = new Poll();
		poll.setId(1);
		poll.setQuestion("What's your favorite programming language?");
		polls.add(poll);
		
		poll = new Poll();
		poll.setId(2);
		poll.setQuestion("What's your favorite movie?");
		polls.add(poll);

		return polls;
	}

}
