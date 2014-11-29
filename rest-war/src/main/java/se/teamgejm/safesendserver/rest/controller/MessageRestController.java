package se.teamgejm.safesendserver.rest.controller;

import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.teamgejm.safesendserver.domain.logentry.LogEntry;
import se.teamgejm.safesendserver.domain.logentry.ObjectType;
import se.teamgejm.safesendserver.domain.logentry.Verb;
import se.teamgejm.safesendserver.domain.message.Message;
import se.teamgejm.safesendserver.domain.user.User;
import se.teamgejm.safesendserver.rest.model.request.SendMessageRequest;
import se.teamgejm.safesendserver.rest.model.response.MessageListResponse;
import se.teamgejm.safesendserver.rest.model.response.MessageResponse;
import se.teamgejm.safesendserver.service.FloodService;
import se.teamgejm.safesendserver.service.LogService;
import se.teamgejm.safesendserver.service.MessageService;
import se.teamgejm.safesendserver.service.UserService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * REST-controller containing /messages endpoints, all endpoints requiering authorization is flood-controlld to avoid
 * brute-force attacks against them
 *
 * @author Marcus Bengtsson
 */
@RestController
public class MessageRestController extends AbstractRestController {

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
	 * @param body          the message-request in json (see API-doc)
	 * @return
	 */
	@RequestMapping(value = "/messages", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity sendMessage(@RequestHeader("Authorization") final String authorization,
			@Valid @RequestBody final SendMessageRequest body, final HttpServletRequest request) {

		if (isBlocked(request.getRemoteAddr(), getEmailFromAuthorization(authorization))) {
			return new ResponseEntity<String>("", HttpStatus.TOO_MANY_REQUESTS);
		}

		final User authorizedUser = userService.getAuthorizedUser(authorization);

		if (authorizedUser == null) {
			registerFailedLoginAttempt(request.getRemoteAddr(), getEmailFromAuthorization(authorization));
			return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
		}

		final User receiver = userService.getUser(body.getReceiverId());

		if (receiver == null) {
			return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
		}

		final Message message = messageService.createMessage(new Message(body.getMessage(), authorizedUser,
				receiver, DateTime.now()));

		if (message == null) {
			return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
		}

		logService.createLogEntry(new LogEntry(authorizedUser.getId(), body.getReceiverId(),
				ObjectType.TEXT_MESSAGE, Verb.SEND, DateTime.now()));

		resetAttempts(request.getRemoteAddr(), getEmailFromAuthorization(authorization));
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
	public ResponseEntity receiveMessage(@RequestHeader("Authorization") final String authorization,
			@PathVariable final long id, final HttpServletRequest request) {

		if (isBlocked(request.getRemoteAddr(), getEmailFromAuthorization(authorization))) {
			return new ResponseEntity<String>("", HttpStatus.TOO_MANY_REQUESTS);
		}

		final Message message = messageService.getMessage(id);

		if (message == null) {
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
		}

		final User authorizedUser = userService.getAuthorizedUser(authorization);

		if (authorizedUser == null || !authorizedUser.equals(message.getReceiver())) {
			registerFailedLoginAttempt(request.getRemoteAddr(), getEmailFromAuthorization(authorization));
			return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
		}

		messageService.removeMessage(message);

		logService.createLogEntry(new LogEntry(message.getSender().getId(), message.getReceiver().getId(),
				ObjectType.TEXT_MESSAGE, Verb.RECEIVE, DateTime.now()));

		resetAttempts(request.getRemoteAddr(), getEmailFromAuthorization(authorization));
		return new ResponseEntity<MessageResponse>(new MessageResponse(message), HttpStatus.OK);
	}

	/**
	 * REST-endpoint returning a list of messages for the authorized user. Requires authorization.
	 *
	 * @param authorization Authorization-header "Basic [username:password]" where [username:password] is base64-encoded
	 * @return list of messages
	 */
	@RequestMapping(value = "/messages", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity listMessages(@RequestHeader("Authorization") final String authorization,
			final HttpServletRequest request) {

		if (isBlocked(request.getRemoteAddr(), getEmailFromAuthorization(authorization))) {
			return new ResponseEntity<String>("", HttpStatus.TOO_MANY_REQUESTS);
		}

		final User authorizedUser = userService.getAuthorizedUser(authorization);

		if (authorizedUser == null) {
			registerFailedLoginAttempt(request.getRemoteAddr(), getEmailFromAuthorization(authorization));
			return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
		}

		final List<MessageListResponse> newMessages = new ArrayList<MessageListResponse>();

		for (Message message : messageService.getMessagesByReceiver(authorizedUser)) {

			newMessages.add(new MessageListResponse(message));
		}

		resetAttempts(request.getRemoteAddr(), getEmailFromAuthorization(authorization));
		return new ResponseEntity<List<MessageListResponse>>(newMessages, HttpStatus.OK);

	}

}
