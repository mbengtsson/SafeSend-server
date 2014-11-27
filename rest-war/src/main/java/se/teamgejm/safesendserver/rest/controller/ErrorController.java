package se.teamgejm.safesendserver.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller containing custom error endpoints
 *
 * @author Marcus Bengtsson
 */
@Controller
public class ErrorController {

	/**
	 * Http-status 400 endpoint, is used instead of jboss default for REST API
	 *
	 * @return Http-status 400
	 */
	@RequestMapping(value = "/400", method = RequestMethod.GET)
	public ResponseEntity error400() {

		return new ResponseEntity<String>("Http-Status 400 - Bad Request", HttpStatus.BAD_REQUEST);

	}

	/**
	 * Http-status 401 endpoint, is used instead of jboss default for REST API
	 *
	 * @return Http-status 401
	 */
	@RequestMapping(value = "/401", method = RequestMethod.GET)
	public ResponseEntity error401() {

		return new ResponseEntity<String>("Http-Status 401 - Unauthorized", HttpStatus.UNAUTHORIZED);

	}

	/**
	 * Http-status 404 endpoint, is used instead of jboss default for REST API
	 *
	 * @return Http-status 404
	 */
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public ResponseEntity error404() {

		return new ResponseEntity<String>("Http-Status 404 - Not Found", HttpStatus.NOT_FOUND);

	}

	/**
	 * Http-status 409 endpoint, is used instead of jboss default for REST API
	 *
	 * @return Http-status 409
	 */
	@RequestMapping(value = "/409", method = RequestMethod.GET)
	public ResponseEntity error409() {

		return new ResponseEntity<String>("Http-Status 409 - Conflict", HttpStatus.CONFLICT);

	}

	/**
	 * Http-status 429 endpoint, is used instead of jboss default for REST API
	 *
	 * @return Http-status 429
	 */
	@RequestMapping(value = "/429", method = RequestMethod.GET)
	public ResponseEntity error429() {

		return new ResponseEntity<String>("Http-Status 429 - Too Many Requests", HttpStatus.TOO_MANY_REQUESTS);

	}

	/**
	 * Http-status 500 endpoint, is used instead of jboss default for REST API
	 *
	 * @return Http-status 500
	 */
	@RequestMapping(value = "/500", method = RequestMethod.GET)
	public ResponseEntity error500() {

		return new ResponseEntity<String>("Http-Status 500 - Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
