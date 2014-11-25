package se.teamgejm.safesendserver.dao;

import se.teamgejm.safesendserver.domain.message.Message;
import se.teamgejm.safesendserver.domain.user.User;

import javax.ejb.Local;
import java.util.Collection;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
@Local
public interface MessageDao extends BaseDao<Message> {

	Collection<Message> getMessagesByReciever(User receiver);

}
