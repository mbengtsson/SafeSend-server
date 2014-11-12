package se.teamgejm.safesendserver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Marcus Bengtsson on 2014-11-12.
 * <p/>
 * Dummy controller to check if the war is deployed and can be accessed correctly
 */
@Controller
public class DummyController {

	@RequestMapping(value = "/isUp", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> isUp() {

		return new ResponseEntity<String>("", HttpStatus.OK);
	}
}
