package se.teamgejm.safesendserver.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.teamgejm.safesendserver.domain.User;
import se.teamgejm.safesendserver.rest.model.GetAllUsersResponse;
import se.teamgejm.safesendserver.rest.model.GetPublicKeyResponse;
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

	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<GetAllUsersResponse> getAllUsers() {

		List<GetAllUsersResponse.UserItem> userList = new ArrayList<GetAllUsersResponse.UserItem>();

		for (User user : userService.getAllUsers()) {
			userList.add(new GetAllUsersResponse.UserItem(user.getId(), user.getUsername()));
		}

		return new ResponseEntity<GetAllUsersResponse>(new GetAllUsersResponse(userList), HttpStatus.OK);
	}

	@RequestMapping(value = "/getPublicKey/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<GetPublicKeyResponse> getPublicKey(@PathVariable long id) {

		User user = userService.getUser(id);

		if (user == null) {
			return new ResponseEntity<GetPublicKeyResponse>(new GetPublicKeyResponse(id, null), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<GetPublicKeyResponse>(new GetPublicKeyResponse(id, user.getPublicKey()), HttpStatus.OK);
	}
}
