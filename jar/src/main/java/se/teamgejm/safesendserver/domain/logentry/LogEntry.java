package se.teamgejm.safesendserver.domain.logentry;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import se.teamgejm.safesendserver.domain.IdHolder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * JPA Entity used to hold a log-entry for the log.
 *
 * @author Marcus Bengtsson
 */

@Entity
@Table(name = "log")
@NamedQueries({@NamedQuery(name = "getLogEntrysByActorID", query = "select l from LogEntry l where l.actorId = :actorId"),
		@NamedQuery(name = "getLogEntrysByTargetID", query = "select l from LogEntry l where l.targetId = :targetId"),
		@NamedQuery(name = "getLogEntrysByObjectType", query = "select l from LogEntry l where l.objectType = :objectType"),
		@NamedQuery(name = "getLogEntrysByVerb", query = "select l from LogEntry l where l.verb = :verb")})
public class LogEntry implements IdHolder, Comparable<LogEntry> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	private long actorId;

	@NotNull
	private long targetId;

	@NotNull
	@Enumerated(EnumType.STRING)
	private ObjectType objectType;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Verb verb;

	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime timeStamp;

	public LogEntry() {
		// Default constructor.
	}

	public LogEntry(long actorId, long targetId, ObjectType objectType, Verb verb, DateTime timeStamp) {
		this.actorId = actorId;
		this.targetId = targetId;
		this.objectType = objectType;
		this.verb = verb;
		this.timeStamp = timeStamp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getActorId() {
		return actorId;
	}

	public void setActorId(long actorId) {
		this.actorId = actorId;
	}

	public long getTargetId() {
		return targetId;
	}

	public void setTargetId(long targetId) {
		this.targetId = targetId;
	}

	public ObjectType getObjectType() {
		return objectType;
	}

	public void setObjectType(ObjectType objectType) {
		this.objectType = objectType;
	}

	public Verb getVerb() {
		return verb;
	}

	public void setVerb(Verb verb) {
		this.verb = verb;
	}

	public DateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(DateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "LogEntry{" +
				"id=" + id +
				", actorId=" + actorId +
				", targetId=" + targetId +
				", objectType=" + objectType +
				", verb=" + verb +
				", timeStamp=" + timeStamp +
				'}';
	}

	@Override
	public int compareTo(LogEntry o) {
		return timeStamp.compareTo(o.getTimeStamp());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof LogEntry)) {
			return false;
		}

		LogEntry logEntry = (LogEntry) o;

		if (actorId != logEntry.actorId) {
			return false;
		}
		if (id != logEntry.id) {
			return false;
		}
		if (targetId != logEntry.targetId) {
			return false;
		}
		if (objectType != logEntry.objectType) {
			return false;
		}
		if (!timeStamp.equals(logEntry.timeStamp)) {
			return false;
		}
		if (verb != logEntry.verb) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (int) (actorId ^ (actorId >>> 32));
		result = 31 * result + (int) (targetId ^ (targetId >>> 32));
		result = 31 * result + objectType.hashCode();
		result = 31 * result + verb.hashCode();
		result = 31 * result + timeStamp.hashCode();
		return result;
	}

}
