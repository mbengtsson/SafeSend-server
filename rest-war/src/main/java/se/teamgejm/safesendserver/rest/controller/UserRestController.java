package se.teamgejm.safesendserver.rest.controller;

import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.teamgejm.safesendserver.domain.LogEntry;
import se.teamgejm.safesendserver.domain.User;
import se.teamgejm.safesendserver.rest.model.request.CreateUserRequest;
import se.teamgejm.safesendserver.rest.model.response.GetPublicKeyResponse;
import se.teamgejm.safesendserver.rest.model.response.UserResponse;
import se.teamgejm.safesendserver.service.LogService;
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

    @Inject
    LogService logService;

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<UserResponse>> getAllUsers () {

        List<UserResponse> userList = new ArrayList<UserResponse>();

        for (User user : userService.getAllUsers()) {
            userList.add(new UserResponse(user.getId(), user.getUsername()));
        }

        return new ResponseEntity<List<UserResponse>>(userList, HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<UserResponse> createUser (@RequestBody CreateUserRequest request) {

        if (request == null || request.getUsername() == null || request.getPassword() == null || request.getPublicKey
                () == null) {
            return new ResponseEntity<UserResponse>(new UserResponse(0, null), HttpStatus.BAD_REQUEST);
        }

        PasswordHasher passHash = new PasswordHasher();

        User user = new User(request.getUsername(), passHash.getPasswordHash(request.getPassword()),
                request.getPublicKey());

        if (!userService.getAllUsers().contains(user)) {
            userService.createUser(user);
        }
        else {
            return new ResponseEntity<UserResponse>(new UserResponse(0, null), HttpStatus.CONFLICT);
        }

        logService.createLogEntry(new LogEntry(user.getId(), user.getId(),
                LogEntry.ObjectType.USER, LogEntry.Verb.CREATE, DateTime.now()));

        return new ResponseEntity<UserResponse>(new UserResponse(user.getId(), user.getUsername()), HttpStatus.OK);

    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<UserResponse> getUser (@PathVariable long id) {

        User user = userService.getUser(id);

        if (user == null) {
            return new ResponseEntity<UserResponse>(new UserResponse(0, null), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<UserResponse>(new UserResponse(user.getId(), user.getUsername()),
                HttpStatus.OK);

    }

    @RequestMapping(value = "/users/{id}/pubkey", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<GetPublicKeyResponse> getPublicKey (@PathVariable long id) {

        User user = userService.getUser(id);

        if (user == null) {
            return new ResponseEntity<GetPublicKeyResponse>(new GetPublicKeyResponse(id, null), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<GetPublicKeyResponse>(new GetPublicKeyResponse(id, user.getPublicKey()), HttpStatus.OK);
    }


}
