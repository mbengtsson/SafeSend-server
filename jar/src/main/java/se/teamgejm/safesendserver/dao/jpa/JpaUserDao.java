package se.teamgejm.safesendserver.dao.jpa;

import se.teamgejm.safesendserver.dao.UserDao;
import se.teamgejm.safesendserver.domain.user.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

/**
 * JPA implementation of the UserDao interface
 *
 * @author Marcus Bengtsson
 */
@Stateless
public class JpaUserDao extends JpaBaseDao<User> implements UserDao {

	@Override
	public User getUserByEmail(String email) {
		Query query = em.createNamedQuery("getUserByEmail");
		query.setParameter("email", email);
		List<User> userList = query.getResultList();

		return userList.isEmpty() ? null : userList.get(0);
	}

	@Override
	public Collection<User> getAllUsers() {
		return em.createQuery("select u from User u").getResultList();
	}
}
