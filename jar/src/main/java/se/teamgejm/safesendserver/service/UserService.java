package se.teamgejm.safesendserver.service;

import se.teamgejm.safesendserver.domain.user.User;

import javax.ejb.Local;
import java.util.Collection;

/**
 * User service to handle users
 *
 * @author Marcus Bengtsson
 */
@Local
public interface UserService {

	User createUser(final User user);

	void removeUser(final User user);

	User getUser(final long id);

	void updateUser(final User user);

	User getUserByUsername(final String username);

	Collection<User> getAllUsers();

	public User getAuthorizedUser(final String authorization);

	public boolean checkAuthorization(final String authorization);

	public boolean checkAuthorization(final String email, final String password);
}
