package se.teamgejm.safesendserver.dao.jpa;

import se.teamgejm.safesendserver.dao.UserDao;
import se.teamgejm.safesendserver.domain.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
@Stateless
public class JpaUserDao extends JpaBaseDao<User> implements UserDao {

	@Override
	public User getUserByUsername(String username) {
		Query query = em.createNamedQuery("getUserByUsername");
		query.setParameter("username", username);
		List<User> userList = query.getResultList();

		return userList.isEmpty() ? null : userList.get(0);
	}

	@Override
	public Collection<User> getAllUsers() {
		return em.createQuery("select u from User u").getResultList();
	}
}
