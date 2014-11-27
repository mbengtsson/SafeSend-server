package se.teamgejm.safesendserver.dao;

import se.teamgejm.safesendserver.domain.user.User;

import javax.ejb.Local;
import java.util.Collection;

/**
 * User Dao to handle user entities in the database
 *
 * @author Marcus Bengtsson
 */
@Local
public interface UserDao extends BaseDao<User> {

	User getUserByEmail(final String email);

	Collection<User> getAllUsers();
}
