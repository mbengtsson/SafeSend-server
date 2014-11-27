package se.teamgejm.safesendserver.rest.model.response;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
public class ReceiveMessageResponse {

	private UserResponse sender;

	private UserResponse receiver;

	private String senderPublicKey;

	private String message;

	private long timeStamp;

	public ReceiveMessageResponse(UserResponse sender, UserResponse receiver, String senderPublicKey, String message,
			long timeStamp) {
		this.sender = sender;
		this.receiver = receiver;
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

	public UserResponse getReceiver() {
		return receiver;
	}

	public void setReceiver(UserResponse receiver) {
		this.receiver = receiver;
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
