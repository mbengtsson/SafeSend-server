package se.teamgejm.safesendserver.rest.model.response;

/**
 * Created by Marcus Bengtsson on 2014-11-14.
 */
public class NewMessagesResponse {

	private long messageId;

	private long senderId;

	private String sender;

	public NewMessagesResponse(long messageId, long senderId, String sender) {
		this.messageId = messageId;
		this.senderId = senderId;
		this.sender = sender;
	}

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
}
