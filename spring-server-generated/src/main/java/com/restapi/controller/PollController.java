package com.restapi.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restapi.domain.Poll;

@RestController
public class PollController {

	List<Poll> polls = new ArrayList<>();
	int startIndex = 2;

	public PollController() {
		getPolls();

		System.out.println("Poll collection initialized");
	}

	@RequestMapping(value = "/polls", method = RequestMethod.GET)
	public ResponseEntity<List<Poll>> getAllPolls() {

		return new ResponseEntity<>(polls, HttpStatus.OK);
	}

	@RequestMapping(value = "/polls", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" }, method = RequestMethod.POST)
	public ResponseEntity<Poll> createPoll(@RequestBody Poll body) {

		startIndex++;

		Poll poll = new Poll();
		poll.setId(startIndex);
		poll.setQuestion(body.getQuestion());
		polls.add(poll);

		// Set the location header for the newly created resource
		// A best practice is to convey the URI to the newly created resource
		// using the Location HTTP header
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(poll.getId())
				.toUri();
		responseHeaders.setLocation(newPollUri);

		return new ResponseEntity<>(poll, responseHeaders, HttpStatus.CREATED);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/polls/{pollId}", method = RequestMethod.GET)
	public ResponseEntity<Poll> getPoll(@PathVariable final int pollId) {

		Predicate<Poll> predicate = new Predicate<Poll>() {
			@Override
			public boolean evaluate(Poll poll) {

				Poll p = (Poll) poll;
				return (p.getId() == pollId);
			}
		};

		Poll p = CollectionUtils.find(polls, predicate);
		if (p == null) {
			throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
		}
		
		return (ResponseEntity<Poll>) new ResponseEntity<>(p, HttpStatus.OK);
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
