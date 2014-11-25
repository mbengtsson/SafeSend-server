package se.teamgejm.safesendserver.dao.jpa;

import se.teamgejm.safesendserver.dao.MessageDao;
import se.teamgejm.safesendserver.domain.message.Message;
import se.teamgejm.safesendserver.domain.user.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.Collection;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
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
