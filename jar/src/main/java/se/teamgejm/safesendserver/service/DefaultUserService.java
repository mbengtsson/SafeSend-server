package se.teamgejm.safesendserver.service;

import se.teamgejm.safesendserver.dao.UserDao;
import se.teamgejm.safesendserver.domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collection;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
@Stateless
public class DefaultUserService implements UserService {

	@Inject
	UserDao userDao;

	@Override
	public User createUser(User user) {
		long id = userDao.persist(user);

		return getUser(id);
	}

	@Override
	public void removeUser(User user) {
		userDao.remove(user);
	}

	@Override
	public User getUser(long id) {
		return userDao.findById(id);
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	public Collection<User> getAllUsers() {
		return userDao.getAllUsers();
	}
}
