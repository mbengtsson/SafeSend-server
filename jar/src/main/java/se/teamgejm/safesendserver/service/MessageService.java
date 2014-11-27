package se.teamgejm.safesendserver.service;

import se.teamgejm.safesendserver.domain.message.Message;
import se.teamgejm.safesendserver.domain.user.User;

import javax.ejb.Local;
import java.util.Collection;

/**
 * Message service to handle messages
 *
 * @author Marcus Bengtsson
 */
@Local
public interface MessageService {

	Message createMessage(Message message);

	void removeMessage(Message message);

	Message getMessage(long id);

	void updateMessage(Message message);

	Collection<Message> getMessagesByReceiver(User receiver);
}
