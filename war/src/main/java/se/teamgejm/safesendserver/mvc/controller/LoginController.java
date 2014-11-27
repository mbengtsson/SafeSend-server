package se.teamgejm.safesendserver.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller containing spring-security login page
 *
 * @author Marcus Bengtsson
 */
@Controller
public class LoginController {

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) final String error,
			final HttpServletRequest request) {

		final ModelAndView mav = new ModelAndView("login");
		if (error != null) {
			final String accountLocked = "User account is locked";
			if (request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION").toString().toLowerCase().contains
					(accountLocked.toLowerCase())) {
				mav.addObject("error", "To many attempts, account locked for 1 hour");
			} else {
				mav.addObject("error", "Invalid email and password!");
			}
		}

		return mav;

	}

}
