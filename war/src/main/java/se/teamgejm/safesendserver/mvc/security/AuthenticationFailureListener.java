package se.teamgejm.safesendserver.mvc.security;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import se.teamgejm.safesendserver.domain.floodevent.FloodType;
import se.teamgejm.safesendserver.service.FloodService;

import javax.inject.Inject;

/**
 * Listen to Spring-security AuthenticationFailureBadCredentialsEvents and register failed login attempts to
 * flood-controller
 *
 * @author Marcus Bengtsson
 */
@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

	@Inject
	private FloodService floodService;

	@Override
	public void onApplicationEvent(final AuthenticationFailureBadCredentialsEvent event) {
		final String email = event.getAuthentication().getPrincipal().toString();

		floodService.registerEvent(FloodType.FAILED_WEB_LOGIN, email);

	}

}
