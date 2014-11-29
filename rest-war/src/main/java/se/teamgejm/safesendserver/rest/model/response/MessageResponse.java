package se.teamgejm.safesendserver.rest.model.response;

import se.teamgejm.safesendserver.domain.message.Message;

/**
 * ReceiveMessage response bean
 *
 * @author Marcus Bengtsson
 */
public class MessageResponse {

	private UserResponse sender;

	private UserResponse receiver;

	private String senderPublicKey;

	private String message;

	private long timeStamp;

	public MessageResponse(Message message) {
		this.sender = new UserResponse(message.getSender());
		this.receiver = new UserResponse(message.getReceiver());
		this.senderPublicKey = message.getSender().getPublicKey();
		this.message = message.getMessage();
		this.timeStamp = message.getTimeStamp().getMillis();
	}

	public MessageResponse(UserResponse sender, UserResponse receiver, String senderPublicKey, String message,
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
