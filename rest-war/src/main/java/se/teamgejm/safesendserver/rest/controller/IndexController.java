package se.teamgejm.safesendserver.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Emil Stjerneman
 */

@Controller
public class IndexController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		System.out.println(request.getContextPath());
		return new ModelAndView("redirect:" + request.getContextPath() + "/web/index.html");
	}
}
