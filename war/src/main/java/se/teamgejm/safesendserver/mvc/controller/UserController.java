package se.teamgejm.safesendserver.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Marcus Bengtsson
 */
@Controller
public class UserController {

	@RequestMapping(value = "/user.html", method = RequestMethod.GET)
	public ModelAndView userPage() {

		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "Safe-send - user");
		mav.addObject("message", "All users can access this page!");
		mav.setViewName("user");
		return mav;

	}
}
