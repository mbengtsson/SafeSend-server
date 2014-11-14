package se.teamgejm.safesendserver.rest.model.response;

/**
 * Created by Marcus Bengtsson on 2014-11-14.
 */
public class GetUserResponse {

	private long id;

	private String username;

	public GetUserResponse(long id, String username) {
		this.id = id;
		this.username = username;
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

}
