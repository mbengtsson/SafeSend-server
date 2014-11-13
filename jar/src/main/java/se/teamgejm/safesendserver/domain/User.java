package se.teamgejm.safesendserver.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
@Entity
public class User implements IdHolder, Comparable<User> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	@NotBlank
	private String publicKey;

	public User() {
	}

	public User(String username, String password, String publicKey) {
		this.username = username;
		this.password = password;
		this.publicKey = publicKey;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", publicKey='" + publicKey + '\'' +
				'}';
	}

	@Override
	public int compareTo(User o) {
		return username.compareTo(o.getUsername());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof User)) {
			return false;
		}

		User user = (User) o;

		if (!password.equals(user.password)) {
			return false;
		}
		if (!publicKey.equals(user.publicKey)) {
			return false;
		}
		if (!username.equals(user.username)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = username.hashCode();
		result = 31 * result + password.hashCode();
		result = 31 * result + publicKey.hashCode();
		return result;
	}
}