package se.teamgejm.safesendserver.mvc.security;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import se.teamgejm.safesendserver.domain.floodevent.FloodType;
import se.teamgejm.safesendserver.service.FloodService;

import javax.inject.Inject;

/**
 * Created by Marcus Bengtsson on 2014-11-25.
 */
@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

	@Inject
	private FloodService floodService;

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		String email = event.getAuthentication().getPrincipal().toString();

		floodService.purgeEvents(FloodType.FAILED_VALIDATE_CREDENTIALS, email);

	}
}
