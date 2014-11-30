package se.teamgejm.safesendserver.mvc.security;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import se.teamgejm.safesendserver.domain.floodevent.FloodType;
import se.teamgejm.safesendserver.service.FloodService;

import javax.inject.Inject;

/**
 * Listen to Spring-security AuthenticationSuccessEvents and purges failed login attempts in flood-controller
 *
 * @author Marcus Bengtsson
 */
@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

	@Inject
	private FloodService floodService;

	@Override
	public void onApplicationEvent(final AuthenticationSuccessEvent event) {
		final String email = event.getAuthentication().getPrincipal().toString();

		floodService.purgeEvents(FloodType.FAILED_WEB_LOGIN, email);

	}
}
