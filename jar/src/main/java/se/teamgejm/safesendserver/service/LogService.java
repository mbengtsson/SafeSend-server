package se.teamgejm.safesendserver.service;

import se.teamgejm.safesendserver.domain.logentry.LogEntry;
import se.teamgejm.safesendserver.domain.logentry.ObjectType;
import se.teamgejm.safesendserver.domain.logentry.Verb;

import javax.ejb.Local;
import java.util.Collection;

/**
 * Log service to handle logging
 *
 * @author Marcus Bengtsson
 */
@Local
public interface LogService {

	LogEntry createLogEntry(LogEntry logEntry);

	void removeLogEntry(LogEntry logEntry);

	LogEntry getLogEntry(long id);

	void updateLogEntry(LogEntry logEntry);

	Collection<LogEntry> getAllLogEntrys();

	Collection<LogEntry> getLogEntrysByActorID(long actorId);

	Collection<LogEntry> getLogEntrysByTargetID(long targetId);

	Collection<LogEntry> getLogEntrysByObjectType(ObjectType objectType);

	Collection<LogEntry> getLogEntrysByVerb(Verb verb);
}
