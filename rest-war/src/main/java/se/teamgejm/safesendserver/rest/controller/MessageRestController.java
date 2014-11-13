package se.teamgejm.safesendserver.rest.controller;

import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.teamgejm.safesendserver.domain.LogEntry;
import se.teamgejm.safesendserver.domain.Message;
import se.teamgejm.safesendserver.domain.User;
import se.teamgejm.safesendserver.rest.model.request.SendMessageRequest;
import se.teamgejm.safesendserver.service.LogService;
import se.teamgejm.safesendserver.service.MessageService;
import se.teamgejm.safesendserver.service.UserService;

import javax.inject.Inject;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
@RestController
public class MessageRestController {

	@Inject
	UserService userService;

	@Inject
	MessageService messageService;

	@Inject
	LogService logService;

	@RequestMapping(value = "/message/send", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> sendMessage(@RequestBody SendMessageRequest request) {

		if (request == null) {
			return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
		}

		User sender = userService.getUser(request.getSenderId());
		User receiver = userService.getUser(request.getReceiverId());

		if (sender == null || receiver == null) {
			return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
		}

		Message message = new Message(request.getMessage(), sender, receiver, DateTime.now());

		messageService.createMessage(message);

		logService.createLogEntry(new LogEntry(request.getSenderId(), request.getReceiverId(),
				LogEntry.ObjectType.TEXT_MESSAGE, LogEntry.Verb.SEND, DateTime.now()));

		return new ResponseEntity<String>("", HttpStatus.OK);
	}
}
