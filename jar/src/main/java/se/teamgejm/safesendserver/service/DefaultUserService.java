package se.teamgejm.safesendserver.service;

import org.apache.commons.codec.binary.Base64;
import se.teamgejm.safesendserver.dao.UserDao;
import se.teamgejm.safesendserver.domain.User;
import se.teamgejm.safesendserver.util.hash.PasswordHasher;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collection;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
@Stateless
public class DefaultUserService implements UserService {

	private static final String AUTHORIZATION_PATTERN = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\" +
			".[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\:.+";

	private static final String AUTHORIZATION_TYPE = "Basic";

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
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	@Override
	public Collection<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public User getAuthorizedUser(String authorization) {
		if (authorization.substring(0, 5).equals(AUTHORIZATION_TYPE)) {

			authorization = new String(Base64.decodeBase64(authorization.substring(5).getBytes()));

			if (authorization != null && authorization.matches(AUTHORIZATION_PATTERN)) {

				String[] parts = authorization.split(":");
				String username = parts[0];
				String password = parts[1];
				User user = getUserByUsername(username);

				if (user != null) {
					String correctHash = user.getPassword();
					PasswordHasher passHash = new PasswordHasher();

					return passHash.validatePassword(password, correctHash) ? user : null;
				}
			}
		}
		return null;
	}

	@Override
	public boolean checkAuthorization(String authorization) {

		return getAuthorizedUser(authorization) != null;
	}

}
