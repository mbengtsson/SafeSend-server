package se.teamgejm.safesendserver.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller containing index page
 *
 * @author Marcus Bengtsson
 */
@Controller
public class IndexController {

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		final ModelAndView mav = new ModelAndView("index");
		mav.addObject("title", "Safe-send");
		mav.addObject("message", "Welcome to safe-send, your encrypted messaging service!");

		return mav;

	}

}
