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
public class LoginController {

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error) {

		ModelAndView mav = new ModelAndView();
		if (error != null) {
			mav.addObject("error", "Invalid username and password!");
		}

		mav.setViewName("login");

		return mav;

	}

	//for 403 access denied page
	@RequestMapping(value = "/403.html", method = RequestMethod.GET)
	public ModelAndView redirect() {

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