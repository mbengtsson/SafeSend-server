package se.teamgejm.safesendserver.service;

import se.teamgejm.safesendserver.domain.User;

import javax.ejb.Local;
import java.util.Collection;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
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
}
