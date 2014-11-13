package se.teamgejm.safesendserver.dao;

import se.teamgejm.safesendserver.domain.LogEntry;

import java.util.Collection;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
public interface LogDao extends BaseDao<LogEntry> {

	Collection<LogEntry> getAllLogEntrys();

	Collection<LogEntry> getLogEntrysByActorID(long actorId);

	Collection<LogEntry> getLogEntrysByTargetID(long targetId);

	Collection<LogEntry> getLogEntrysByObjectType(LogEntry.ObjectType objectType);

	Collection<LogEntry> getLogEntrysByVerb(LogEntry.Verb verb);

}
