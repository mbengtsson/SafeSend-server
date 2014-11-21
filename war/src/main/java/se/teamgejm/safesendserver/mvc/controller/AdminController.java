package se.teamgejm.safesendserver.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Marcus Bengtsson
 */
@Controller
public class AdminController {

	@RequestMapping(value = "/admin.html", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "Safe-send - administration");
		mav.addObject("message", "Only administrators can access this page!");
		mav.setViewName("admin");
		return mav;

	}
}
