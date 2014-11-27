package se.teamgejm.safesendserver.rest.model.request;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * CreateUser request bean
 *
 * @author Marcus Bengtsson
 */
public class CreateUserRequest {

	@NotEmpty
	private String email;

	@NotEmpty
	private String displayName;

	@NotEmpty
	private String password;

	@NotEmpty
	private String publicKey;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
}
