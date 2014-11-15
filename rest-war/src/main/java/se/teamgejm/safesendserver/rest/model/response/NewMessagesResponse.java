package se.teamgejm.safesendserver.rest.model.response;

/**
 * Created by Marcus Bengtsson on 2014-11-14.
 */
public class NewMessagesResponse {

	private long messageId;

	private UserResponse sender;

	private long timeStamp;

	public NewMessagesResponse(long messageId, UserResponse sender, long timeStamp) {
		this.messageId = messageId;
		this.sender = sender;
		this.timeStamp = timeStamp;
	}

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public UserResponse getSender() {
		return sender;
	}

	public void setSender(UserResponse sender) {
		this.sender = sender;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
}
