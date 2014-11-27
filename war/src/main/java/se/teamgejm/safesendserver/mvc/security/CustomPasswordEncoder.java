package se.teamgejm.safesendserver.mvc.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import se.teamgejm.safesendserver.security.PasswordHasher;

import javax.inject.Inject;

/**
 * Custom Spring-security PasswordEncoder, uses PasswordHasher for password hashing and verification
 *
 * @author Marcus Bengtsson
 */
public class CustomPasswordEncoder implements PasswordEncoder {

	@Inject
	private PasswordHasher passHash;

	@Override
	public String encode(CharSequence rawPassword) {
		return passHash.generateHash(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return passHash.validatePassword(rawPassword.toString(), encodedPassword);
	}

}
