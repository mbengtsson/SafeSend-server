package se.teamgejm.safesendserver.dao;

import se.teamgejm.safesendserver.domain.message.Message;
import se.teamgejm.safesendserver.domain.user.User;

import javax.ejb.Local;
import java.util.Collection;

/**
 * Message Dao to handle message entities in the database
 *
 * @author Marcus Bengtsson
 */
@Local
public interface MessageDao extends BaseDao<Message> {

	Collection<Message> getMessagesByReceiver(User receiver);

}
