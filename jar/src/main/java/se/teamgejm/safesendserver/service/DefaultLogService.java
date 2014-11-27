package se.teamgejm.safesendserver.service;

import se.teamgejm.safesendserver.dao.LogDao;
import se.teamgejm.safesendserver.domain.logentry.LogEntry;
import se.teamgejm.safesendserver.domain.logentry.ObjectType;
import se.teamgejm.safesendserver.domain.logentry.Verb;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collection;

/**
 * Default implementation of the LogService interface
 *
 * @author Marcus Bengtsson
 */
@Stateless
public class DefaultLogService implements LogService {

	@Inject
	private LogDao logDao;

	@Override
	public LogEntry createLogEntry(final LogEntry logEntry) {
		final long id = logDao.persist(logEntry);

		return getLogEntry(id);
	}

	@Override
	public void removeLogEntry(final LogEntry logEntry) {
		logDao.remove(logEntry);
	}

	@Override
	public LogEntry getLogEntry(final long id) {
		return logDao.findById(id);
	}

	@Override
	public void updateLogEntry(final LogEntry logEntry) {
		logDao.update(logEntry);
	}

	@Override
	public Collection<LogEntry> getAllLogEntrys() {
		return logDao.getAllLogEntrys();
	}

	@Override
	public Collection<LogEntry> getLogEntrysByActorID(final long actorId) {
		return logDao.getLogEntrysByActorID(actorId);
	}

	@Override
	public Collection<LogEntry> getLogEntrysByTargetID(final long targetId) {
		return logDao.getLogEntrysByTargetID(targetId);
	}

	@Override
	public Collection<LogEntry> getLogEntrysByObjectType(final ObjectType objectType) {
		return logDao.getLogEntrysByObjectType(objectType);
	}

	@Override
	public Collection<LogEntry> getLogEntrysByVerb(final Verb verb) {
		return logDao.getLogEntrysByVerb(verb);
	}
}
