package se.teamgejm.safesendserver.rest.model;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
public class GetPublicKeyResponse {

	private long id;

	private String publicKey;

	public GetPublicKeyResponse(long id, String publicKey) {
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
