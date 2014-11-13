package se.teamgejm.safesendserver.dao;

import se.teamgejm.safesendserver.domain.User;

import javax.ejb.Local;
import java.util.Collection;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
@Local
public interface UserDao extends BaseDao<User> {

	Collection<User> getAllUsers();
}
