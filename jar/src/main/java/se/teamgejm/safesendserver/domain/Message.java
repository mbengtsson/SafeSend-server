package se.teamgejm.safesendserver.domain;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */

@Entity
public class Message implements IdHolder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	private String message;

	@NotNull
	@ManyToOne
	private User sender;

	@NotNull
	@ManyToOne
	private User reiever;

	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime timeStamp;

	public Message() {
	}

	public Message(String message, User sender, User reiever, DateTime timeStamp) {
		this.message = message;
		this.sender = sender;
		this.reiever = reiever;
		this.timeStamp = timeStamp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReiever() {
		return reiever;
	}

	public void setReiever(User reiever) {
		this.reiever = reiever;
	}

	public DateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(DateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Message{" +
				"id=" + id +
				", message='" + message + '\'' +
				", sender=" + sender +
				", reiever=" + reiever +
				", timeStamp=" + timeStamp +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Message)) {
			return false;
		}

		Message message1 = (Message) o;

		if (id != message1.id) {
			return false;
		}
		if (!message.equals(message1.message)) {
			return false;
		}
		if (!reiever.equals(message1.reiever)) {
			return false;
		}
		if (!sender.equals(message1.sender)) {
			return false;
		}
		if (!timeStamp.equals(message1.timeStamp)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + message.hashCode();
		result = 31 * result + sender.hashCode();
		result = 31 * result + reiever.hashCode();
		result = 31 * result + timeStamp.hashCode();
		return result;
	}
}
