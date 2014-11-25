package se.teamgejm.safesendserver.mvc.bean;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by Marcus Bengtsson on 2014-11-22.
 */
public class LogBean implements Comparable<LogBean> {

	private long actorId;
	private long targetId;
	private String objectType;
	private String verb;
	private String timeStamp;

	private DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("YYYY-MMM-dd HH:mm");

	public LogBean(long actorId, long targetId, String objectType, String verb, DateTime timeStamp) {
		this.actorId = actorId;
		this.targetId = targetId;
		this.objectType = objectType;
		this.verb = verb;
		this.timeStamp = timeFormatter.print(timeStamp);
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

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getVerb() {
		return verb;
	}

	public void setVerb(String verb) {
		this.verb = verb;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public void setTimeStamp(DateTime timeStamp) {
		this.timeStamp = timeFormatter.print(timeStamp);
	}

	@Override
	public int compareTo(LogBean o) {

		return timeFormatter.parseDateTime(timeStamp).compareTo(timeFormatter.parseDateTime(o.timeStamp));

	}
}
