package se.teamgejm.safesendserver;

import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.teamgejm.safesendserver.domain.LogEntry;
import se.teamgejm.safesendserver.service.LogService;

import javax.inject.Inject;

/**
 * Created by Marcus Bengtsson on 2014-11-12.
 * <p/>
 * Dummy controller to check if the war is deployed and can be accessed correctly
 */
@RestController
public class DummyController {

	@Inject
	LogService logService;

	@RequestMapping(value = "/isUp", method = RequestMethod.GET)
	public ResponseEntity<String> isUp() {

		logService.createLogEntry(new LogEntry(1, 1, LogEntry.ObjectType.TEXT_MESSAGE, LogEntry.Verb.RECEIVE,
				DateTime.now()));

		return new ResponseEntity<String>("", HttpStatus.OK);
	}
}
