package se.teamgejm.safesendserver.service;

import se.teamgejm.safesendserver.dao.MessageDao;
import se.teamgejm.safesendserver.domain.message.Message;
import se.teamgejm.safesendserver.domain.user.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collection;

/**
 * Default implementation of the MessageService interface
 *
 * @author Marcus Bengtsson
 */
@Stateless
public class DefaultMessageService implements MessageService {

	@Inject
	private MessageDao messageDao;

	@Override
	public Message createMessage(final Message message) {

		final long id = messageDao.persist(message);

		return getMessage(id);
	}

	@Override
	public void removeMessage(final Message message) {
		messageDao.remove(message);
	}

	@Override
	public Message getMessage(final long id) {
		return messageDao.findById(id);
	}

	@Override
	public void updateMessage(final Message message) {
		messageDao.update(message);
	}

	@Override
	public Collection<Message> getMessagesByReceiver(final User receiver) {
		return messageDao.getMessagesByReceiver(receiver);
	}
}
