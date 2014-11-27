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

	Message createMessage(final Message message);

	void removeMessage(final Message message);

	Message getMessage(final long id);

	void updateMessage(final Message message);

	Collection<Message> getMessagesByReceiver(final User receiver);
}
