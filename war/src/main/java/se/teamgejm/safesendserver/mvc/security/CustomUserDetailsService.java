package se.teamgejm.safesendserver.mvc.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import se.teamgejm.safesendserver.domain.floodevent.FloodType;
import se.teamgejm.safesendserver.domain.user.User;
import se.teamgejm.safesendserver.service.FloodService;
import se.teamgejm.safesendserver.service.UserService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom Spring-security UserDetailService, used to get user objects from our database and convert them to
 * org.springframework.security.core.userdetails.User objects for Spring-security. Also checks if the user is locked
 * in the flood-controll to prevent brute-force attacks on login-page.
 *
 * @author Marcus Bengtsson
 */
public class CustomUserDetailsService implements UserDetailsService {

	final int userThreshold = 6;

	@Inject
	private UserService userService;

	@Inject
	private FloodService floodService;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		if (!floodService.isAllowed(FloodType.FAILED_VALIDATE_CREDENTIALS, username, userThreshold)) {
			return new org.springframework.security.core.userdetails.User(username, "", true, true, true, false,
					new ArrayList<GrantedAuthority>());
		}

		final User user = userService.getUserByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true,
				true, true, true, authorities);
	}
}
