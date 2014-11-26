package se.teamgejm.safesendserver.security;

import javax.ejb.*;

/**
 * Implementations of this interface are used to generate password hashes and validate passwords aginst a hash
 *
 * @author Marcus Bengtsson
 */
@Local
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public interface PasswordHasher {

	@Lock(LockType.READ)
	String generateHash(String password);

	@Lock(LockType.READ)
	boolean validatePassword(String password, String correctHash);
}
