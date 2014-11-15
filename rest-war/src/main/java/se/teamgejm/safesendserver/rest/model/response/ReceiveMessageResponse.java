package se.teamgejm.safesendserver.rest.model.response;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
public class ReceiveMessageResponse {

	private UserResponse sender;

	private String senderPublicKey;

	private String message;

	private long timeStamp;

	public ReceiveMessageResponse(UserResponse sender, String senderPublicKey, String message, long timeStamp) {
		this.sender = sender;
		this.senderPublicKey = senderPublicKey;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	public UserResponse getSender() {
		return sender;
	}

	public void setSender(UserResponse senderId) {
		this.sender = senderId;
	}

	public String getSenderPublicKey() {
		return senderPublicKey;
	}

	public void setSenderPublicKey(String senderPublicKey) {
		this.senderPublicKey = senderPublicKey;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
}
