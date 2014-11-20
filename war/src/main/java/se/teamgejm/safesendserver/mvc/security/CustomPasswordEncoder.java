package se.teamgejm.safesendserver.mvc.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import se.teamgejm.safesendserver.security.PasswordHasher;

/**
 * Created by Marcus Bengtsson on 2014-11-20.
 */
public class CustomPasswordEncoder implements PasswordEncoder {

	PasswordHasher passHash = new PasswordHasher();

	@Override
	public String encode(CharSequence rawPassword) {
		return passHash.generateHash(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return passHash.validatePassword(rawPassword.toString(), encodedPassword);
	}

}
