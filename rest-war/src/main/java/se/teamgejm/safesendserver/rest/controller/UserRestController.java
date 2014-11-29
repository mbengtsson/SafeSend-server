package se.teamgejm.safesendserver.rest.controller;

import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.teamgejm.safesendserver.domain.logentry.LogEntry;
import se.teamgejm.safesendserver.domain.logentry.ObjectType;
import se.teamgejm.safesendserver.domain.logentry.Verb;
import se.teamgejm.safesendserver.domain.user.User;
import se.teamgejm.safesendserver.rest.model.request.CreateUserRequest;
import se.teamgejm.safesendserver.rest.model.request.ValidateCredentialsRequest;
import se.teamgejm.safesendserver.rest.model.response.PublicKeyResponse;
import se.teamgejm.safesendserver.rest.model.response.UserResponse;
import se.teamgejm.safesendserver.service.LogService;
import se.teamgejm.safesendserver.service.UserService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * REST-controller containing /users endpoints, all endpoints requiering authorization is flood-controlld to avoid
 * brute-force attacks against them
 *
 * @author Marcus Bengtsson
 */
@RestController
public class UserRestController extends AbstractRestController {

	@Inject
	private UserService userService;
	@Inject
	private LogService logService;

	/**
	 * REST-endpoint that returns a list of all users. Requires authorization.
	 *
	 * @param authorization Authorization-header "Basic [username:password]" where [username:password] is base64-encoded
	 * @return list of all users
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity getAllUsers(@RequestHeader("Authorization") final String authorization,
			final HttpServletRequest request) {

		if (isBlocked(request.getRemoteAddr(), getEmailFromAuthorization(authorization))) {
			return new ResponseEntity<String>("", HttpStatus.TOO_MANY_REQUESTS);
		}

		if (!userService.checkAuthorization(authorization)) {
			registerFailedLoginAttempt(request.getRemoteAddr(), getEmailFromAuthorization(authorization));
			return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
		}

		final List<UserResponse> userList = new ArrayList<UserResponse>();

		for (User user : userService.getAllUsers()) {
			userList.add(new UserResponse(user));
		}

		resetAttempts(request.getRemoteAddr(), getEmailFromAuthorization(authorization));
		return new ResponseEntity<List<UserResponse>>(userList, HttpStatus.OK);
	}

	/**
	 * REST-endpoint for validation user credentials
	 *
	 * @param request validate credentials request
	 * @return 200 if the validation succeed, otherwise 401
	 */
	@RequestMapping(value = "/users/validate_credentials", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity validateUserCredentials(@Valid @RequestBody final ValidateCredentialsRequest body,
			final HttpServletRequest request) {

		if (isBlocked(request.getRemoteAddr(), body.getEmail())) {
			return new ResponseEntity<String>("", HttpStatus.TOO_MANY_REQUESTS);
		}

		if (!userService.checkAuthorization(body.getEmail(), body.getPassword())) {
			registerFailedLoginAttempt(request.getRemoteAddr(), body.getEmail());
			return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
		}

		resetAttempts(request.getRemoteAddr(), body.getEmail());
		return new ResponseEntity<String>("", HttpStatus.OK);

	}

	/**
	 * REST-endpoint for creating a new user
	 *
	 * @param body new user request in json (see API-doc)
	 * @return the created user
	 */
	@RequestMapping(value = "/users", method = RequestMethod.POST, consumes = "application/json",
			produces = "application/json")
	public ResponseEntity createUser(@Valid @RequestBody final CreateUserRequest body) {

		User user = new User(body.getEmail(), body.getDisplayName(), body.getPassword(),
				body.getPublicKey());

		if (userService.getAllUsers().contains(user)) {
			return new ResponseEntity<String>("", HttpStatus.CONFLICT);
		}

		user = userService.createUser(user);

		if (user == null) {
			return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
		}

		logService.createLogEntry(new LogEntry(user.getId(), user.getId(), ObjectType.USER, Verb.CREATE,
				DateTime.now()));

		return new ResponseEntity<UserResponse>(new UserResponse(user), HttpStatus.OK);

	}

	/**
	 * REST-endpoint returning a single user. Requires authorization.
	 *
	 * @param authorization Authorization-header "Basic [username:password]" where [username:password] is base64-encoded
	 * @param id            users id
	 * @return the user
	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity getUser(@RequestHeader("Authorization") final String authorization,
			@PathVariable final long id, final HttpServletRequest request) {

		if (isBlocked(request.getRemoteAddr(), getEmailFromAuthorization(authorization))) {
			return new ResponseEntity<String>("", HttpStatus.TOO_MANY_REQUESTS);
		}

		if (!userService.checkAuthorization(authorization)) {
			registerFailedLoginAttempt(request.getRemoteAddr(), getEmailFromAuthorization(authorization));
			return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
		}

		final User user = userService.getUser(id);

		if (user == null) {
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
		}

		resetAttempts(request.getRemoteAddr(), getEmailFromAuthorization(authorization));
		return new ResponseEntity<UserResponse>(new UserResponse(user), HttpStatus.OK);

	}

	/**
	 * REST-endpoint returning a users public-key. Requires authorization.
	 *
	 * @param authorization Authorization-header "Basic [username:password]" where [username:password] is base64-encoded
	 * @param id            users id
	 * @return the users public-key
	 */
	@RequestMapping(value = "/users/{id}/pubkey", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity getPublicKey(@RequestHeader("Authorization") final String authorization,
			@PathVariable final long id, final HttpServletRequest request) {

		if (isBlocked(request.getRemoteAddr(), getEmailFromAuthorization(authorization))) {
			return new ResponseEntity<String>("", HttpStatus.TOO_MANY_REQUESTS);
		}

		if (!userService.checkAuthorization(authorization)) {
			registerFailedLoginAttempt(request.getRemoteAddr(), getEmailFromAuthorization(authorization));
			return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
		}

		final User user = userService.getUser(id);

		if (user == null) {
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
		}

		resetAttempts(request.getRemoteAddr(), getEmailFromAuthorization(authorization));
		return new ResponseEntity<PublicKeyResponse>(new PublicKeyResponse(user), HttpStatus.OK);
	}

}
