package se.teamgejm.safesendserver.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import se.teamgejm.safesendserver.domain.message.Message;
import se.teamgejm.safesendserver.domain.user.User;
import se.teamgejm.safesendserver.mvc.bean.MessageBean;
import se.teamgejm.safesendserver.service.MessageService;
import se.teamgejm.safesendserver.service.UserService;

import javax.inject.Inject;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Controller containing user pages
 *
 * @author Marcus Bengtsson
 */
@Controller
public class UserController {

	@Inject
	private UserService userService;

	@Inject
	private MessageService messageService;

	@RequestMapping(value = "/user.html", method = RequestMethod.GET)
	public ModelAndView userPage(final Principal principal) {

		final User user = userService.getUserByUsername(principal.getName());
		final List<Message> messages = new ArrayList<Message>(messageService.getMessagesByReceiver(user));
		Collections.sort(messages);
		final List<MessageBean> messageBeans = new ArrayList<MessageBean>();

		for (Message message : messages) {
			messageBeans.add(new MessageBean(message.getSender().getDisplayName(), message.getSender().getEmail(),
					message.getReceiver().getDisplayName(), message.getReceiver().getEmail(), message.getTimeStamp()));
		}

		final ModelAndView mav = new ModelAndView("user");
		mav.addObject("displayName", user.getDisplayName());
		mav.addObject("messagesLength", messageBeans.size());
		mav.addObject("messages", messageBeans);

		return mav;

	}

}
