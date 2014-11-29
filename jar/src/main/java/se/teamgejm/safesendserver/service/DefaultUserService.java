package se.teamgejm.safesendserver.service;

import org.apache.commons.codec.binary.Base64;
import se.teamgejm.safesendserver.dao.UserDao;
import se.teamgejm.safesendserver.domain.user.User;
import se.teamgejm.safesendserver.security.PasswordHasher;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collection;

/**
 * Default implementation of the UserService interface
 *
 * @author Marcus Bengtsson
 */
@Stateless
public class DefaultUserService implements UserService {

	private static final String DISPLAYNAME_PATTERN = "[A-Za-z0-9. ]{4,}";
	private static final String EMAIL_PATTERN = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@" +
			"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
	private static final String PASSWORD_PATTERN = ".{8,}";
	private static final String AUTHORIZATION_PATTERN = EMAIL_PATTERN + ":" + PASSWORD_PATTERN;
	private static final String PUBKEY_PATTERN = "(-----BEGIN PGP PUBLIC KEY BLOCK-----)([\\s\\S]+)(-----END PGP PUBLIC KEY BLOCK-----)";

	private static final String AUTHORIZATION_TYPE = "Basic";

	@Inject
	private UserDao userDao;

	@Inject
	private PasswordHasher passHash;

	@Override
	public User createUser(final User user) {

		if (validateUserData(user)) {
			user.setPassword(passHash.generateHash(user.getPassword()));
			final long id = userDao.persist(user);

			return getUser(id);
		} else {
			return null;
		}
	}

	@Override
	public void removeUser(final User user) {

		if (validateUserData(user)) {
			userDao.remove(user);
		}
	}

	@Override
	public User getUser(final long id) {
		return userDao.findById(id);
	}

	@Override
	public void updateUser(final User user) {

		if (validateUserData(user)) {
			userDao.update(user);
		}
	}

	@Override
	public User getUserByUsername(final String username) {
		return userDao.getUserByEmail(username);
	}

	@Override
	public Collection<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public User getAuthorizedUser(final String authorization) {

		if (authorization == null || authorization.length() < 5) {
			return null;
		}

		if (!authorization.substring(0, 5).equals(AUTHORIZATION_TYPE)) {
			return null;
		}

		final byte[] authBytes = authorization.substring(5).getBytes();

		if (!Base64.isArrayByteBase64(authBytes)) {
			return null;
		}

		final String plainAuth = new String(Base64.decodeBase64(authBytes));

		if (!plainAuth.matches(AUTHORIZATION_PATTERN)) {
			return null;
		}

		final String[] parts = authorization.split(":");
		final String email = parts[0];
		final String password = parts[1];
		final User user = getUserByUsername(email);

		if (user == null) {
			return null;
		}

		final String correctHash = user.getPassword();
		return passHash.validatePassword(password, correctHash) ? user : null;

	}

	@Override
	public boolean checkAuthorization(final String authorization) {
		return getAuthorizedUser(authorization) != null;
	}

	@Override
	public boolean checkAuthorization(final String email, final String password) {
		final User user = getUserByUsername(email);

		if (user != null) {
			final String correctHash = user.getPassword();

			return passHash.validatePassword(password, correctHash);
		}

		return false;
	}

	/**
	 * Validates if user contains a valid email, diaplay-name, password and public key
	 *
	 * @param user user to validate
	 * @return true if user data is valid
	 */
	private boolean validateUserData(final User user) {

		final String pubKey = new String(Base64.decodeBase64(user.getPublicKey().getBytes())).trim();

		return user.getEmail().matches(EMAIL_PATTERN) && user.getDisplayName().matches(DISPLAYNAME_PATTERN) &&
				user.getPassword().matches(PASSWORD_PATTERN) && pubKey.matches(PUBKEY_PATTERN);
	}
}
