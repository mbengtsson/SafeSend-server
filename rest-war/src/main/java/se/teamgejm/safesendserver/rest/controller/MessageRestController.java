package se.teamgejm.safesendserver.rest.controller;

import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.teamgejm.safesendserver.domain.LogEntry;
import se.teamgejm.safesendserver.domain.Message;
import se.teamgejm.safesendserver.domain.User;
import se.teamgejm.safesendserver.rest.model.request.ReceiveMessageRequest;
import se.teamgejm.safesendserver.rest.model.request.SendMessageRequest;
import se.teamgejm.safesendserver.rest.model.response.NewMessagesResponse;
import se.teamgejm.safesendserver.rest.model.response.ReceiveMessageResponse;
import se.teamgejm.safesendserver.rest.model.response.UserResponse;
import se.teamgejm.safesendserver.service.LogService;
import se.teamgejm.safesendserver.service.MessageService;
import se.teamgejm.safesendserver.service.UserService;
import se.teamgejm.safesendserver.util.hash.PasswordHasher;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "/messages/send", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> sendMessage (@RequestBody SendMessageRequest request) {

        if (request == null) {
            return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
        }

        User sender = userService.getUser(request.getSenderId());
        User receiver = userService.getUser(request.getReceiverId());

        if (sender == null || receiver == null) {
            return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
        }

        PasswordHasher passHash = new PasswordHasher();

        if (request.getPassword() == null || !passHash.validatePassword(request.getPassword(), sender.getPassword())) {
            return new ResponseEntity<String>("", HttpStatus.FORBIDDEN);
        }

        Message message = new Message(request.getMessage(), sender, receiver, DateTime.now());

        messageService.createMessage(message);

        logService.createLogEntry(new LogEntry(request.getSenderId(), request.getReceiverId(),
                LogEntry.ObjectType.TEXT_MESSAGE, LogEntry.Verb.SEND, DateTime.now()));

        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    @RequestMapping(value = "/messages/receive", method = RequestMethod.POST, consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<ReceiveMessageResponse> receiveMessage (@RequestBody ReceiveMessageRequest request) {

        if (request == null) {
            return new ResponseEntity<ReceiveMessageResponse>(new ReceiveMessageResponse(null, null, null, 0),
                    HttpStatus.BAD_REQUEST);
        }

        Message message = messageService.getMessage(request.getMessageId());

        if (message == null) {
            return new ResponseEntity<ReceiveMessageResponse>(new ReceiveMessageResponse(null, null, null, 0),
                    HttpStatus.BAD_REQUEST);
        }

        PasswordHasher passHash = new PasswordHasher();

        if (request.getPassword() == null || !passHash.validatePassword(request.getPassword(),
                message.getReciever().getPassword())) {
            return new ResponseEntity<ReceiveMessageResponse>(new ReceiveMessageResponse(null, null, null, 0),
                    HttpStatus.FORBIDDEN);
        }

        UserResponse sender = new UserResponse(message.getSender().getId(), message.getSender().getUsername());

        messageService.removeMessage(message);

        logService.createLogEntry(new LogEntry(message.getSender().getId(), message.getReciever().getId(),
                LogEntry.ObjectType.TEXT_MESSAGE, LogEntry.Verb.RECEIVE, DateTime.now()));

        return new ResponseEntity<ReceiveMessageResponse>(new ReceiveMessageResponse(sender,
                message.getSender().getPublicKey(), message.getMessage(), message.getTimeStamp().getMillis()),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/messages/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<NewMessagesResponse>> newMessages (@PathVariable long id) {

        List<NewMessagesResponse> newMessages = new ArrayList<NewMessagesResponse>();
        User receiver = userService.getUser(id);

        if (receiver == null) {
            return new ResponseEntity<List<NewMessagesResponse>>(newMessages, HttpStatus.NOT_FOUND);
        }

        for (Message message : messageService.getMessagesByReciever(receiver)) {
            UserResponse sender = new UserResponse(message.getSender().getId(), message.getSender().getUsername());
            newMessages.add(new NewMessagesResponse(message.getId(), sender, message.getTimeStamp().getMillis()));
        }

        return new ResponseEntity<List<NewMessagesResponse>>(newMessages, HttpStatus.OK);

    }
}
