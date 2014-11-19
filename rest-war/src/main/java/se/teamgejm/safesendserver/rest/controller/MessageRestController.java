package se.teamgejm.safesendserver.rest.controller;

import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.teamgejm.safesendserver.domain.LogEntry;
import se.teamgejm.safesendserver.domain.Message;
import se.teamgejm.safesendserver.domain.User;
import se.teamgejm.safesendserver.rest.model.request.SendMessageRequest;
import se.teamgejm.safesendserver.rest.model.response.NewMessagesResponse;
import se.teamgejm.safesendserver.rest.model.response.ReceiveMessageResponse;
import se.teamgejm.safesendserver.rest.model.response.UserResponse;
import se.teamgejm.safesendserver.service.LogService;
import se.teamgejm.safesendserver.service.MessageService;
import se.teamgejm.safesendserver.service.UserService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
@RestController
public class MessageRestController {

	@Inject
	private UserService userService;

	@Inject
	private MessageService messageService;

	@Inject
	private LogService logService;

	/**
	 * REST-endpoint used to send a message. Requires authorization.
	 *
	 * @param authorization Authorization-header "Basic [username:password]" where [username:password] is base64-encoded
	 * @param request       the message-request in json (see API-doc)
	 * @return
	 */
	@RequestMapping(value = "/messages", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity sendMessage(@RequestHeader("Authorization") String authorization,
			@RequestBody SendMessageRequest request) {

		User authorizedUser = userService.getAuthorizedUser(authorization);

		if (authorizedUser == null) {
			return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
		}

		if (request == null) {
			return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
		}

		User receiver = userService.getUser(request.getReceiverId());

		if (receiver == null) {
			return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
		}

		Message message = new Message(request.getMessage(), authorizedUser, receiver, DateTime.now());
		messageService.createMessage(message);

		logService.createLogEntry(new LogEntry(authorizedUser.getId(), request.getReceiverId(),
				LogEntry.ObjectType.TEXT_MESSAGE, LogEntry.Verb.SEND, DateTime.now()));

		return new ResponseEntity<String>("", HttpStatus.OK);
	}

	/**
	 * REST-endpoint used to receive a message, note that the message is removed afterwards. Requires authorization.
	 *
	 * @param authorization Authorization-header "Basic [username:password]" where [username:password] is base64-encoded
	 * @param id            message id
	 * @return the message together with the senders public-key
	 */
	@RequestMapping(value = "/messages/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity receiveMessage(@RequestHeader("Authorization") String authorization, @PathVariable long id) {

		Message message = messageService.getMessage(id);

		if (message == null) {
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
		}

		User authorizedUser = userService.getAuthorizedUser(authorization);

		if (authorizedUser == null || !authorizedUser.equals(message.getReciever())) {
			return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
		}

		messageService.removeMessage(message);
		UserResponse sender = new UserResponse(message.getSender().getId(), message.getSender().getEmail(),
				message.getSender().getDisplayName());
		logService.createLogEntry(new LogEntry(message.getSender().getId(), message.getReciever().getId(),
				LogEntry.ObjectType.TEXT_MESSAGE, LogEntry.Verb.RECEIVE, DateTime.now()));

		return new ResponseEntity<ReceiveMessageResponse>(new ReceiveMessageResponse(sender,
				message.getSender().getPublicKey(), message.getMessage(), message.getTimeStamp().getMillis()),
				HttpStatus.OK);
	}

	/**
	 * REST-endpoint returning a list of messages for the authorized user. Requires authorization.
	 *
	 * @param authorization Authorization-header "Basic [username:password]" where [username:password] is base64-encoded
	 * @return list of messages
	 */
	@RequestMapping(value = "/messages", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity listMessages(@RequestHeader("Authorization") String authorization) {

		User authorizedUser = userService.getAuthorizedUser(authorization);

		if (authorizedUser == null) {
			return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
		}

		List<NewMessagesResponse> newMessages = new ArrayList<NewMessagesResponse>();

		for (Message message : messageService.getMessagesByReciever(authorizedUser)) {
			UserResponse sender = new UserResponse(message.getSender().getId(), message.getSender().getEmail(),
					message.getSender().getDisplayName());
			newMessages.add(new NewMessagesResponse(message.getId(), sender, message.getTimeStamp().getMillis()));
		}

		return new ResponseEntity<List<NewMessagesResponse>>(newMessages, HttpStatus.OK);

	}
}
