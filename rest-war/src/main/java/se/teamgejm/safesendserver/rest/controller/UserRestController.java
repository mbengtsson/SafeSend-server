package se.teamgejm.safesendserver.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.teamgejm.safesendserver.domain.User;
import se.teamgejm.safesendserver.rest.model.GetAllUsersRestResponse;
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
	public ResponseEntity<GetAllUsersRestResponse> getAllUsers() {

		List<GetAllUsersRestResponse.UserItem> userList = new ArrayList<GetAllUsersRestResponse.UserItem>();

		for (User user : userService.getAllUsers()) {
			userList.add(new GetAllUsersRestResponse.UserItem(user.getId(), user.getUsername()));
		}

		return new ResponseEntity<GetAllUsersRestResponse>(new GetAllUsersRestResponse(userList), HttpStatus.OK);
	}
}
