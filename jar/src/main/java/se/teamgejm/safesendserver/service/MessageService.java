package se.teamgejm.safesendserver.service;

import se.teamgejm.safesendserver.domain.message.Message;
import se.teamgejm.safesendserver.domain.user.User;

import javax.ejb.Local;
import java.util.Collection;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
@Local
public interface MessageService {

	Message createMessage(Message message);

	void removeMessage(Message message);

	Message getMessage(long id);

	void updateMessage(Message message);

	Collection<Message> getMessagesByReciever(User receiver);
}
