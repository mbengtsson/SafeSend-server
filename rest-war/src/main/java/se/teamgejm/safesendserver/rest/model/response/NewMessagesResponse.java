package se.teamgejm.safesendserver.rest.model.response;

/**
 * NewMessages response bean
 *
 * @author Marcus Bengtsson
 */
public class NewMessagesResponse {

	private long id;

	private UserResponse sender;

	private UserResponse receiver;

	private long timeStamp;

	public NewMessagesResponse(long id, UserResponse sender, UserResponse receiver, long timeStamp) {
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.timeStamp = timeStamp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserResponse getSender() {
		return sender;
	}

	public void setSender(UserResponse sender) {
		this.sender = sender;
	}

	public UserResponse getReceiver() {
		return receiver;
	}

	public void setReceiver(UserResponse receiver) {
		this.receiver = receiver;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
}
