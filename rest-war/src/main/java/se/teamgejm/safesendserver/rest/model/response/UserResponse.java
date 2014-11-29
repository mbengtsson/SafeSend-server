package se.teamgejm.safesendserver.rest.model.response;

import se.teamgejm.safesendserver.domain.user.User;

/**
 * User response bean
 *
 * @author Marcus Bengtsson
 */
public class UserResponse {

	private long id;

	private String email;

	private String displayName;

	public UserResponse(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.displayName = user.getDisplayName();
	}

	public UserResponse(long id, String email, String displayName) {
		this.id = id;
		this.email = email;
		this.displayName = displayName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
