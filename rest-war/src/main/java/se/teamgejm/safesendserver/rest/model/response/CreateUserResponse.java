package se.teamgejm.safesendserver.rest.model.response;

/**
 * Created by Marcus Bengtsson on 2014-11-14.
 */
public class CreateUserResponse {

	private long id;

	public CreateUserResponse(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
