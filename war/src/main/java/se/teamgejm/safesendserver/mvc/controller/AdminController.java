package se.teamgejm.safesendserver.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import se.teamgejm.safesendserver.domain.User;
import se.teamgejm.safesendserver.service.UserService;

import javax.inject.Inject;
import java.security.Principal;

/**
 * @author Marcus Bengtsson
 */
@Controller
public class AdminController {

	@Inject
	UserService userService;

	@RequestMapping(value = "/admin.html", method = RequestMethod.GET)
	public ModelAndView adminPage(Principal principal) {

		User user = userService.getUserByUsername(principal.getName());

		ModelAndView mav = new ModelAndView("admin");
		mav.addObject("displayName", user.getDisplayName());
		mav.addObject("message", "Only administrators can access this page!");

		return mav;

	}

	@RequestMapping(value = "/admin/log.html", method = RequestMethod.GET)
	public ModelAndView log() {

		return null;
	}

	@RequestMapping(value = "/admin/users.html", method = RequestMethod.GET)
	public ModelAndView users() {

		return null;
	}

	@RequestMapping(value = "/admin/users/{id}.html", method = RequestMethod.GET)
	public ModelAndView userDetails(@PathVariable long id) {

		return null;
	}


}
