package se.teamgejm.safesendserver.mvc.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import se.teamgejm.safesendserver.security.PasswordHasher;

import javax.inject.Inject;

/**
 * Created by Marcus Bengtsson on 2014-11-20.
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
