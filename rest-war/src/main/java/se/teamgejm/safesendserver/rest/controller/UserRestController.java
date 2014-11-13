package se.teamgejm.safesendserver.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.teamgejm.safesendserver.domain.User;
import se.teamgejm.safesendserver.rest.model.response.GetPublicKeyResponse;
import se.teamgejm.safesendserver.rest.model.response.UserResponseItem;
import se.teamgejm.safesendserver.service.UserService;

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

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<UserResponseItem>> getAllUsers() {

		List<UserResponseItem> userList = new ArrayList<UserResponseItem>();

		for (User user : userService.getAllUsers()) {
			userList.add(new UserResponseItem(user.getId(), user.getUsername()));
		}

		return new ResponseEntity<List<UserResponseItem>>(userList, HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{id}/pubkey", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<GetPublicKeyResponse> getPublicKey(@PathVariable long id) {

		User user = userService.getUser(id);

		if (user == null) {
			return new ResponseEntity<GetPublicKeyResponse>(new GetPublicKeyResponse(id, null), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<GetPublicKeyResponse>(new GetPublicKeyResponse(id, user.getPublicKey()), HttpStatus.OK);
	}
}
