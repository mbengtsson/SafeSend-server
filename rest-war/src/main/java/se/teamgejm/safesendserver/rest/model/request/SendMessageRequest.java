package se.teamgejm.safesendserver.rest.model.request;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
public class SendMessageRequest {

	private long receiverId;

	private String message;

	public long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
