package se.teamgejm.safesendserver.mvc.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller containing custom error pages
 *
 * @author Marcus Bengtsson
 */
@Controller
public class ErrorController {

	@RequestMapping(value = "/403.html", method = RequestMethod.GET)
	public ModelAndView error403() {

		ModelAndView mav = new ModelAndView("403");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			mav.addObject("username", userDetail.getUsername());
		}

		return mav;
	}

	@RequestMapping(value = "/404.html", method = RequestMethod.GET)
	public ModelAndView error404() {

		return new ModelAndView("404");
	}

	@RequestMapping(value = "/500.html", method = RequestMethod.GET)
	public ModelAndView error500() {

		return new ModelAndView("500");
	}
}
