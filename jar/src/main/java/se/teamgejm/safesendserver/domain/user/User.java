package se.teamgejm.safesendserver.domain.user;

import org.hibernate.validator.constraints.NotBlank;
import se.teamgejm.safesendserver.domain.IdHolder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
@Entity
@NamedQuery(name = "getUserByEmail", query = "select u from User u where u.email = :email")
public class User implements IdHolder, Comparable<User> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	private String email;

	private String displayName;

	@NotBlank
	private String password;

	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String publicKey;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Role role;

	public User() {
	}

	public User(String email, String displayName, String password, String publicKey) {
		this.email = email;
		this.displayName = displayName;
		this.password = password;
		this.publicKey = publicKey;
		this.role = Role.USER;
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

	public void setEmail(String username) {
		this.email = username;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User{" +
				"role=" + role +
				", displayName='" + displayName + '\'' +
				", email='" + email + '\'' +
				", id=" + id +
				'}';
	}

	@Override
	public int compareTo(User o) {
		return email.compareTo(o.getEmail());
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

		if (!email.equals(user.email)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return email.hashCode();
	}

}
