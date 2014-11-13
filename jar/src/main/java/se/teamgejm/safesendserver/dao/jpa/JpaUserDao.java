package se.teamgejm.safesendserver.dao.jpa;

import se.teamgejm.safesendserver.dao.UserDao;
import se.teamgejm.safesendserver.domain.User;

import javax.ejb.Stateless;
import java.util.Collection;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
@Stateless
public class JpaUserDao extends JpaBaseDao<User> implements UserDao {

	@Override
	public Collection<User> getAllUsers() {
		return em.createQuery("select u from LogEntry u").getResultList();
	}
}
