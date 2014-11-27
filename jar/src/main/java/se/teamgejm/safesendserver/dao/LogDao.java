package se.teamgejm.safesendserver.dao;

import se.teamgejm.safesendserver.domain.logentry.LogEntry;
import se.teamgejm.safesendserver.domain.logentry.ObjectType;
import se.teamgejm.safesendserver.domain.logentry.Verb;

import javax.ejb.Local;
import java.util.Collection;

/**
 * Log Dao to handle log-entry entities in the database
 *
 * @author Marcus Bengtsson
 */
@Local
public interface LogDao extends BaseDao<LogEntry> {

	Collection<LogEntry> getAllLogEntrys();

	Collection<LogEntry> getLogEntrysByActorID(final long actorId);

	Collection<LogEntry> getLogEntrysByTargetID(final long targetId);

	Collection<LogEntry> getLogEntrysByObjectType(final ObjectType objectType);

	Collection<LogEntry> getLogEntrysByVerb(final Verb verb);

}
