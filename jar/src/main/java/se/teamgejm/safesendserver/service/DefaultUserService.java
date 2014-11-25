package se.teamgejm.safesendserver.service;

import org.apache.commons.codec.binary.Base64;
import se.teamgejm.safesendserver.dao.UserDao;
import se.teamgejm.safesendserver.domain.user.User;
import se.teamgejm.safesendserver.security.PasswordHasher;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collection;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
@Stateless
public class DefaultUserService implements UserService {

	private static final String DISPLAYNAME_PATTERN = "[A-Za-z0-9. ]{4,}";
	private static final String EMAIL_PATTERN = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@" +
			"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
	private static final String PASSWORD_PATTERN = "{1}.+";
	private static final String AUTHORIZATION_PATTERN = EMAIL_PATTERN + ":" + PASSWORD_PATTERN;

	private static final String AUTHORIZATION_TYPE = "Basic";

	@Inject
	private UserDao userDao;

	@Override
	public User createUser(User user) {

		if (validateUserData(user)) {
			long id = userDao.persist(user);

			return getUser(id);
		} else {
			return null;
		}
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

		if (validateUserData(user)) {
			userDao.update(user);
		}
	}

	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByEmail(username);
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

	@Override
	public boolean checkAuthorization(String email, String password) {
		User user = getUserByUsername(email);

		if (user != null) {
			String correctHash = user.getPassword();
			PasswordHasher passHash = new PasswordHasher();

			return passHash.validatePassword(password, correctHash) ? true : false;
		}

		return false;
	}

	private boolean validateUserData(User user) {

		return user.getEmail().matches(EMAIL_PATTERN) && user.getDisplayName().matches(DISPLAYNAME_PATTERN);
	}
}
