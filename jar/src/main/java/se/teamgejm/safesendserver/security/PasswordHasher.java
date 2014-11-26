package se.teamgejm.safesendserver.security;

import javax.ejb.*;

/**
 * Created by Marcus Bengtsson on 2014-11-25.
 */
@Local
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public interface PasswordHasher {

	@Lock(LockType.READ)
	String generateHash(String password);

	@Lock(LockType.READ)
	boolean validatePassword(String password, String correctHash);
}
