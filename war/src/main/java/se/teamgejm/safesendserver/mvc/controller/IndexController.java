package se.teamgejm.safesendserver.mvc.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Marcus Bengtsson
 */
@Controller
public class IndexController {

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "Safe-send administration");
		mav.addObject("message", "Everyone can access this page!");
		mav.setViewName("index");
		return mav;

	}

	@RequestMapping(value = "/admin.html", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "Safe-send administration");
		mav.addObject("message", "Only administrators can access this page!");
		mav.setViewName("admin");
		return mav;

	}

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView mav = new ModelAndView();
		if (error != null) {
			mav.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			mav.addObject("msg", "You've been logged out successfully.");
		}
		mav.setViewName("login");

		return mav;

	}

	//for 403 access denied page
	@RequestMapping(value = "/403.html", method = RequestMethod.GET)
	public ModelAndView accessDenied() {

		ModelAndView mav = new ModelAndView();

		//check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			mav.addObject("username", userDetail.getUsername());
		}

		mav.setViewName("403");
		return mav;

	}

}
