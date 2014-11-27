package se.teamgejm.safesendserver.rest.model.request;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * SendMessage request bean
 *
 * @author Marcus Bengtsson
 */
public class SendMessageRequest {

	@NotNull
	private long receiverId;

	@NotEmpty
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
