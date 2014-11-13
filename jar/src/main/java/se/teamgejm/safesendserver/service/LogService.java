package se.teamgejm.safesendserver.service;

import se.teamgejm.safesendserver.domain.LogEntry;

import javax.ejb.Local;
import java.util.Collection;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
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

	Collection<LogEntry> getLogEntrysByObjectType(LogEntry.ObjectType objectType);

	Collection<LogEntry> getLogEntrysByVerb(LogEntry.Verb verb);
}
