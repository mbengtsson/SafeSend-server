package se.teamgejm.safesendserver.mvc.bean;

/**
 * Created by Marcus Bengtsson on 2014-11-23.
 */
public class UserBean {

	private long id;
	private String email;
	private String displayName;
	private String publicKey;
	private String role;

	public UserBean() {
	}

	public UserBean(long id, String email, String displayName, String publicKey, String role) {
		this.id = id;
		this.email = email;
		this.displayName = displayName;
		this.publicKey = publicKey;
		this.role = role;
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

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
