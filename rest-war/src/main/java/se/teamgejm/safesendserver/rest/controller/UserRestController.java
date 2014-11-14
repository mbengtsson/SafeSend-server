package se.teamgejm.safesendserver.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.teamgejm.safesendserver.domain.User;
import se.teamgejm.safesendserver.rest.model.request.CreateUserRequest;
import se.teamgejm.safesendserver.rest.model.response.GetPublicKeyResponse;
import se.teamgejm.safesendserver.rest.model.response.GetUserResponse;
import se.teamgejm.safesendserver.service.UserService;
import se.teamgejm.safesendserver.util.hash.PasswordHasher;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
@RestController
public class UserRestController {

	@Inject
	UserService userService;

	@RequestMapping(value = "/user", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> createUser(@RequestBody CreateUserRequest request) {

		if (request == null || request.getUsername() == null || request.getPassword() == null || request.getPublicKey
				() == null) {
			return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
		}

		PasswordHasher passHash = new PasswordHasher();

		User user = new User(request.getUsername(), passHash.getPasswordHash(request.getPassword()),
				request.getPublicKey());

		if (!userService.getAllUsers().contains(user)) {
			userService.createUser(user);
		} else {
			return new ResponseEntity<String>("", HttpStatus.CONFLICT);
		}

		return new ResponseEntity<String>("", HttpStatus.OK);

	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<GetUserResponse> getUser(@PathVariable long id) {

		User user = userService.getUser(id);

		if (user == null) {
			return new ResponseEntity<GetUserResponse>(new GetUserResponse(0, null), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<GetUserResponse>(new GetUserResponse(user.getId(), user.getUsername()),
				HttpStatus.OK);

	}

	@RequestMapping(value = "/user/{id}/pubkey", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<GetPublicKeyResponse> getPublicKey(@PathVariable long id) {

		User user = userService.getUser(id);

		if (user == null) {
			return new ResponseEntity<GetPublicKeyResponse>(new GetPublicKeyResponse(id, null), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<GetPublicKeyResponse>(new GetPublicKeyResponse(id, user.getPublicKey()), HttpStatus.OK);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<GetUserResponse>> getAllUsers() {

		List<GetUserResponse> userList = new ArrayList<GetUserResponse>();

		for (User user : userService.getAllUsers()) {
			userList.add(new GetUserResponse(user.getId(), user.getUsername()));
		}

		return new ResponseEntity<List<GetUserResponse>>(userList, HttpStatus.OK);
	}
}
