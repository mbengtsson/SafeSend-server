package se.teamgejm.safesendserver.rest.controller;

import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.teamgejm.safesendserver.domain.LogEntry;
import se.teamgejm.safesendserver.domain.User;
import se.teamgejm.safesendserver.rest.model.request.CreateUserRequest;
import se.teamgejm.safesendserver.rest.model.request.ValidateCredentialsRequest;
import se.teamgejm.safesendserver.rest.model.response.GetPublicKeyResponse;
import se.teamgejm.safesendserver.rest.model.response.UserResponse;
import se.teamgejm.safesendserver.security.PasswordHasher;
import se.teamgejm.safesendserver.service.LogService;
import se.teamgejm.safesendserver.service.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
@RestController
public class UserRestController {

    @Inject
    private UserService userService;
    @Inject
    private LogService logService;

    /**
     * REST-endpoint that returns a list of all users. Requires authorization.
     *
     * @param authorization
     *         Authorization-header "Basic [username:password]" where [username:password] is base64-encoded
     *
     * @return list of all users
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAllUsers (@RequestHeader("Authorization") String authorization) {

        if (!userService.checkAuthorization(authorization)) {
            return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
        }

        List<UserResponse> userList = new ArrayList<UserResponse>();

        for (User user : userService.getAllUsers()) {
            userList.add(new UserResponse(user.getId(), user.getEmail(), user.getDisplayName()));
        }

        return new ResponseEntity<List<UserResponse>>(userList, HttpStatus.OK);
    }

    /**
     * REST-endpoint for validation user credentials
     *
     * @param request
     *         validate credentials request
     *
     * @return 200 if the validation succeed, otherwise 401
     */
    @RequestMapping(value = "/users/validate_credentials", method = RequestMethod.POST, consumes = "application/json",
            produces = "application/json")
    public ResponseEntity validateUserCredentials (@Valid @RequestBody ValidateCredentialsRequest request) {

        if (request == null || request.getEmail() == null || request.getPassword() == null) {
            return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
        }

        if (userService.checkAuthorization(request.getEmail(), request.getPassword())) {
            return new ResponseEntity<String>("", HttpStatus.OK);
        }

        return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
    }

    /**
     * REST-endpoint for creating a new user
     *
     * @param request
     *         new user request in json (see API-doc)
     *
     * @return the created user
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = "application/json",
            produces = "application/json")
    public ResponseEntity createUser (@Valid @RequestBody CreateUserRequest request) {

        if (request == null || request.getEmail() == null || request.getDisplayName() == null || request.getPassword()
                == null || request
                .getPublicKey
                        () == null) {
            return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
        }

        PasswordHasher passHash = new PasswordHasher();
        User user = new User(request.getEmail(), request.getDisplayName(), passHash.generateHash(request
                .getPassword()),
                request.getPublicKey());

        if (!userService.getAllUsers().contains(user)) {
            userService.createUser(user);
        }
        else {
            return new ResponseEntity<String>("", HttpStatus.CONFLICT);
        }

        logService.createLogEntry(new LogEntry(user.getId(), user.getId(),
                LogEntry.ObjectType.USER, LogEntry.Verb.CREATE, DateTime.now()));

        return new ResponseEntity<UserResponse>(new UserResponse(user.getId(), user.getEmail(),
                user.getDisplayName()), HttpStatus.OK);

    }

    /**
     * REST-endpoint returning a single user. Requires authorization.
     *
     * @param authorization
     *         Authorization-header "Basic [username:password]" where [username:password] is base64-encoded
     * @param id
     *         users id
     *
     * @return the user
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getUser (@RequestHeader("Authorization") String authorization, @PathVariable long id) {

        if (!userService.checkAuthorization(authorization)) {
            return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
        }

        User user = userService.getUser(id);

        if (user == null) {
            return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<UserResponse>(new UserResponse(user.getId(), user.getDisplayName(), user.getEmail()),
                HttpStatus.OK);

    }

    /**
     * REST-endpoint returning a users public-key. Requires authorization.
     *
     * @param authorization
     *         Authorization-header "Basic [username:password]" where [username:password] is base64-encoded
     * @param id
     *         users id
     *
     * @return the users public-key
     */
    @RequestMapping(value = "/users/{id}/pubkey", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getPublicKey (@RequestHeader("Authorization") String authorization, @PathVariable long id) {

        if (!userService.checkAuthorization(authorization)) {
            return new ResponseEntity<String>("", HttpStatus.UNAUTHORIZED);
        }

        User user = userService.getUser(id);

        if (user == null) {
            return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<GetPublicKeyResponse>(new GetPublicKeyResponse(id, user.getPublicKey()), HttpStatus.OK);
    }
}
