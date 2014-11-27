package se.teamgejm.safesendserver.mvc.bean;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Message bean
 *
 * @author Marcus Bengtsson
 */
public class MessageBean {

	private String senderName;
	private String senderEmail;
	private String receiverName;
	private String receiverEmail;
	private String time;

	private DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("YYYY-MMM-dd HH:mm");

	public MessageBean(String senderName, String senderEmail, String receiverName, String receiverEmail, DateTime time) {
		this.senderName = senderName;
		this.senderEmail = senderEmail;
		this.receiverName = receiverName;
		this.receiverEmail = receiverEmail;
		this.time = timeFormatter.print(time);
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverEmail() {
		return receiverEmail;
	}

	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}

	public String getTime() {
		return time;
	}

	public void setTime(DateTime time) {
		this.time = timeFormatter.print(time);
	}

	public void setTime(String time) {
		this.time = time;
	}
}
