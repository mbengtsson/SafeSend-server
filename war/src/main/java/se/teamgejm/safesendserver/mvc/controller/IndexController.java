package se.teamgejm.safesendserver.mvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Emil Stjerneman
 */
@RestController
public class IndexController {

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public ResponseEntity<String> index () {
        return new ResponseEntity<String>("SafeSend", HttpStatus.OK);
    }
}
