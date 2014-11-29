package se.teamgejm.safesendserver.rest.controller;

import org.apache.commons.codec.binary.Base64;
import se.teamgejm.safesendserver.domain.floodevent.FloodType;
import se.teamgejm.safesendserver.service.FloodService;

import javax.inject.Inject;

/**
 * Abstract class other REST controllers can extend to get some handy methods for flood-control
 *
 * @author Marcus Bengtsson
 */
public abstract class AbstractRestController {

	private final static int IP_THRESHOLD = 60;
	private final static int USER_THRESHOLD = 6;

	@Inject
	private FloodService floodService;

	protected String getEmailFromAuthorization(String authorization) {

		if (authorization == null || authorization.length() < 5) {
			return null;
		}

		byte[] auth = authorization.substring(5).getBytes();
		if (Base64.isArrayByteBase64(auth)) {
			authorization = new String(Base64.decodeBase64(auth));
			return authorization.split(":")[0];
		} else {
			return null;
		}

	}

	protected void registerFailedLoginAttempt(final String ipAddress, final String email) {

		if (email != null) {
			floodService.registerEvent(FloodType.FAILED_VALIDATE_CREDENTIALS, email + "-" + ipAddress);
		}

		floodService.registerEvent(FloodType.FAILED_VALIDATE_CREDENTIALS, ipAddress);
	}

	protected void resetAttempts(final String ipAddress, final String email) {
		floodService.purgeEvents(FloodType.FAILED_VALIDATE_CREDENTIALS, email + "-" + ipAddress);
	}

	protected boolean isBlocked(final String ipAddress, final String email) {

		if (email == null) {
			return true;
		}

		if (!floodService.isAllowed(FloodType.FAILED_VALIDATE_CREDENTIALS, ipAddress, IP_THRESHOLD)) {
			return true;
		}

		if (!floodService.isAllowed(FloodType.FAILED_VALIDATE_CREDENTIALS, email + "-" + ipAddress, USER_THRESHOLD)) {
			return true;
		}

		return false;
	}
}
