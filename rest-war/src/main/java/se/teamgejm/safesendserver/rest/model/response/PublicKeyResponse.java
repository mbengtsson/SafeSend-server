package se.teamgejm.safesendserver.rest.model.response;

import se.teamgejm.safesendserver.domain.user.User;

/**
 * GetPublicKey response bean
 *
 * @author Marcus Bengtsson
 */
public class PublicKeyResponse {

	private long id;

	private String publicKey;

	public PublicKeyResponse(User user) {
		this.id = user.getId();
		this.publicKey = user.getPublicKey();
	}

	public PublicKeyResponse(long id, String publicKey) {
		this.id = id;
		this.publicKey = publicKey;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
}
