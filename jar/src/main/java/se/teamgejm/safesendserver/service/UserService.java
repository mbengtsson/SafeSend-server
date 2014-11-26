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

	User createUser(User user);

	void removeUser(User user);

	User getUser(long id);

	void updateUser(User user);

	User getUserByUsername(String username);

	Collection<User> getAllUsers();

	public User getAuthorizedUser(String authorization);

	public boolean checkAuthorization(String authorization);

	public boolean checkAuthorization(String email, String password);
}
