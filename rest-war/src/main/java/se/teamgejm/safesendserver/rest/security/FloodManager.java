package se.teamgejm.safesendserver.rest.security;

import org.apache.commons.codec.binary.Base64;
import se.teamgejm.safesendserver.domain.floodevent.FloodType;
import se.teamgejm.safesendserver.service.FloodService;

/**
 * Created by Marcus Bengtsson on 2014-11-29.
 */

public class FloodManager {

	final static private int IP_THRESHOLD = 60;
	final static private int USER_THRESHOLD = 6;

	final private FloodService floodService;
	final private String ipAddress;
	final private String email;

	public FloodManager(final FloodService floodService, final String ipAddress, final String email) {
		this.floodService = floodService;
		this.ipAddress = ipAddress;
		this.email = email;
	}

	public static String getEmailFromAuthorization(String authorization) {

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

	public void registerFailedLoginAttempt() {

		if (email != null) {
			floodService.registerEvent(FloodType.FAILED_VALIDATE_CREDENTIALS, email + "-" + ipAddress);
		}

		floodService.registerEvent(FloodType.FAILED_VALIDATE_CREDENTIALS, ipAddress);
	}

	public void resetAttempts() {
		floodService.purgeEvents(FloodType.FAILED_VALIDATE_CREDENTIALS, email + "-" + ipAddress);
	}

	public boolean isBlocked() {

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
