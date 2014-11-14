package se.teamgejm.safesendserver.rest.model.request;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
public class ReceiveMessageRequest {

	private long messageId;

	private String password;

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
