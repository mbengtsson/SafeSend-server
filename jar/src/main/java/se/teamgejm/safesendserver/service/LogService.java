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

	LogEntry createLogEntry(final LogEntry logEntry);

	void removeLogEntry(final LogEntry logEntry);

	LogEntry getLogEntry(final long id);

	void updateLogEntry(final LogEntry logEntry);

	Collection<LogEntry> getAllLogEntrys();

	Collection<LogEntry> getLogEntrysByActorID(final long actorId);

	Collection<LogEntry> getLogEntrysByTargetID(final long targetId);

	Collection<LogEntry> getLogEntrysByObjectType(final ObjectType objectType);

	Collection<LogEntry> getLogEntrysByVerb(final Verb verb);
}
