package se.teamgejm.safesendserver.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Marcus Bengtsson
 */
@Controller
public class IndexController {

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "Safe-send");
		mav.addObject("message", "Welcome to safe-send, your encrypted messaging service!");
		mav.setViewName("index");
		return mav;

	}

}