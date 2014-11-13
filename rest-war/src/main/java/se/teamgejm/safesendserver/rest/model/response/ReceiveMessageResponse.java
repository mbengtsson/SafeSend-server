package se.teamgejm.safesendserver.rest.model.response;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
public class ReceiveMessageResponse {

	private long senderId;

	private String senderPublicKey;

	private String message;

	public ReceiveMessageResponse(long senderId, String senderPublicKey, String message) {
		this.senderId = senderId;
		this.senderPublicKey = senderPublicKey;
		this.message = message;
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
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
}
