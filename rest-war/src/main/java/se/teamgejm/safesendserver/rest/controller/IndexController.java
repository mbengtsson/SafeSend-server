package se.teamgejm.safesendserver.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller containing Base-endpoint /
 *
 * @author Emil Stjerneman
 */

@Controller
public class IndexController {

	/**
	 * Base endpoint redirecting to API-documentation
	 *
	 * @return redirect to API-documentation
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {

		return new ModelAndView("redirect:https://bitbucket.org/teamgejm/server/wiki/REST%20API%20Documentation");
	}

}
