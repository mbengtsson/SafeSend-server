package se.teamgejm.safesendserver.dao.jpa;

import se.teamgejm.safesendserver.dao.MessageDao;
import se.teamgejm.safesendserver.domain.message.Message;
import se.teamgejm.safesendserver.domain.user.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.Collection;

/**
 * JPA implementation of the MessageDao interface
 *
 * @author Marcus Bengtsson
 */
@Stateless
public class JpaMessageDao extends JpaBaseDao<Message> implements MessageDao {

	@Override
	public Collection<Message> getMessagesByReciever(User receiver) {
		Query query = em.createNamedQuery("getMessagesByReciever");
		query.setParameter("receiver", receiver);
		return query.getResultList();
	}

}
