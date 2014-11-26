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

	Collection<LogEntry> getLogEntrysByActorID(long actorId);

	Collection<LogEntry> getLogEntrysByTargetID(long targetId);

	Collection<LogEntry> getLogEntrysByObjectType(ObjectType objectType);

	Collection<LogEntry> getLogEntrysByVerb(Verb verb);

}
