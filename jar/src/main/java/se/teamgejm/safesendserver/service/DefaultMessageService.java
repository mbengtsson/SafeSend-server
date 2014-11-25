package se.teamgejm.safesendserver.service;

import se.teamgejm.safesendserver.dao.MessageDao;
import se.teamgejm.safesendserver.domain.message.Message;
import se.teamgejm.safesendserver.domain.user.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collection;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
@Stateless
public class DefaultMessageService implements MessageService {

	@Inject
	private MessageDao messageDao;

	@Override
	public Message createMessage(Message message) {

		long id = messageDao.persist(message);

		return getMessage(id);
	}

	@Override
	public void removeMessage(Message message) {
		messageDao.remove(message);
	}

	@Override
	public Message getMessage(long id) {
		return messageDao.findById(id);
	}

	@Override
	public void updateMessage(Message message) {
		messageDao.update(message);
	}

	@Override
	public Collection<Message> getMessagesByReciever(User receiver) {
		return messageDao.getMessagesByReciever(receiver);
	}
}
