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
 * Created by Marcus Bengtsson on 2014-11-20.
 */
public class CustomUserDetailsService implements UserDetailsService {

	final int userThreshold = 6;

	@Inject
	private UserService userService;

	@Inject
	private FloodService floodService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if (!floodService.isAllowed(FloodType.FAILED_VALIDATE_CREDENTIALS, username, userThreshold)) {
			return new org.springframework.security.core.userdetails.User(username, "", true, true, true, false,
					new ArrayList<GrantedAuthority>());
		}

		User user = userService.getUserByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true,
				true, true, true, authorities);
	}
}
