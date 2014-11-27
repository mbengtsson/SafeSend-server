package se.teamgejm.safesendserver.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import se.teamgejm.safesendserver.domain.logentry.LogEntry;
import se.teamgejm.safesendserver.domain.user.Role;
import se.teamgejm.safesendserver.domain.user.User;
import se.teamgejm.safesendserver.mvc.bean.LogBean;
import se.teamgejm.safesendserver.mvc.bean.UserBean;
import se.teamgejm.safesendserver.service.LogService;
import se.teamgejm.safesendserver.service.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

/**
 * Controller containing administration pages
 *
 * @author Marcus Bengtsson
 */
@Controller
public class AdminController {

	@Inject
	private UserService userService;

	@Inject
	private LogService logService;

	@RequestMapping(value = "/admin.html", method = RequestMethod.GET)
	public ModelAndView adminPage(final Principal principal) {

		final User user = userService.getUserByUsername(principal.getName());

		final ModelAndView mav = new ModelAndView("admin");
		mav.addObject("displayName", user.getDisplayName());
		mav.addObject("message", "Only administrators can access this page!");

		return mav;

	}

	@RequestMapping(value = "/admin/log.html", method = RequestMethod.GET)
	public ModelAndView log() {

		final List<LogBean> log = new ArrayList<LogBean>();
		for (LogEntry logEntry : logService.getAllLogEntrys()) {
			log.add(new LogBean(logEntry.getActorId(), logEntry.getTargetId(), logEntry.getObjectType().toString(),
					logEntry.getVerb().toString(), logEntry.getTimeStamp()));
		}

		final ModelAndView mav = new ModelAndView("log");
		mav.addObject("log", log);

		return mav;
	}

	@RequestMapping(value = "/admin/users.html", method = RequestMethod.GET)
	public ModelAndView users() {

		final List<UserBean> users = new ArrayList<UserBean>();
		for (User user : userService.getAllUsers()) {
			users.add(new UserBean(user.getId(), user.getEmail(), user.getDisplayName(), null, user.getRole().toString()));
		}

		final ModelAndView mav = new ModelAndView("users");
		mav.addObject("users", users);

		return mav;
	}

	@RequestMapping(value = "/admin/users/{id}.html", method = RequestMethod.GET)
	public ModelAndView userDetails(@PathVariable final long id) {

		final User user = userService.getUser(id);
		final UserBean userBean = new UserBean(user.getId(), user.getEmail(), user.getDisplayName(),
				user.getPublicKey(), user.getRole().toString());
		final Role[] roles = Role.values();

		final Set<LogEntry> logEntries = new HashSet<LogEntry>(logService.getLogEntrysByActorID(id));
		logEntries.addAll(logService.getLogEntrysByTargetID(id));

		final List<LogBean> log = new ArrayList<LogBean>();
		for (LogEntry logEntry : logEntries) {
			log.add(new LogBean(logEntry.getActorId(), logEntry.getTargetId(), logEntry.getObjectType().toString(),
					logEntry.getVerb().toString(), logEntry.getTimeStamp()));
		}
		Collections.sort(log);

		final ModelAndView mav = new ModelAndView("userdetails");
		mav.addObject("user", userBean);
		mav.addObject("roles", roles);
		mav.addObject("log", log);
		return mav;
	}

	@RequestMapping(value = "/admin/users/{id}.html", method = RequestMethod.POST)
	public ModelAndView userDetailsSubmit(@Valid final UserBean bean) {

		final User user = userService.getUser(bean.getId());
		for (Role role : Role.values()) {
			if (bean.getRole().equalsIgnoreCase(role.name())) {
				user.setRole(role);
			}
		}
		userService.updateUser(user);

		return new ModelAndView("redirect:/admin/users.html");
	}

}
