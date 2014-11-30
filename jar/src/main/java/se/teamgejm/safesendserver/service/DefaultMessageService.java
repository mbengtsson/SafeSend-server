package se.teamgejm.safesendserver.service;

import org.apache.commons.codec.binary.Base64;
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

	private static final String PGPMESSAGE_PATTERN = "(-----BEGIN PGP MESSAGE-----)([\\s\\S]+)(-----END PGP MESSAGE-----)";

	@Inject
	private MessageDao messageDao;

	@Override
	public Message createMessage(final Message message) {

		if (validateMessageData(message)) {
			final long id = messageDao.persist(message);

			return getMessage(id);
		}

		return null;
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

	/**
	 * Validates if message contains a valid pgp-message
	 *
	 * @param message message to validate
	 * @return true if message data is valid
	 */
	private boolean validateMessageData(final Message message) {

		final byte[] messageBytes = message.getMessage().getBytes();
		if (!Base64.isArrayByteBase64(messageBytes)) {
			return false;
		}
		final String pgpMessage = new String(Base64.decodeBase64(messageBytes)).trim();

		return pgpMessage.matches(PGPMESSAGE_PATTERN);
	}
}
