package se.teamgejm.safesendserver.dao.jpa;

import se.teamgejm.safesendserver.dao.LogDao;
import se.teamgejm.safesendserver.domain.logentry.LogEntry;
import se.teamgejm.safesendserver.domain.logentry.ObjectType;
import se.teamgejm.safesendserver.domain.logentry.Verb;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.Collection;

/**
 * JPA implementation of the LogDao interface
 *
 * @author Marcus Bengtsson
 */
@Stateless
public class JpaLogDao extends JpaBaseDao<LogEntry> implements LogDao {

	@SuppressWarnings("unchecked")
	@Override
	public Collection<LogEntry> getAllLogEntrys() {
		return em.createQuery("select l from LogEntry l").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<LogEntry> getLogEntrysByActorID(long actorId) {
		Query query = em.createNamedQuery("getLogEntrysByActorID");
		query.setParameter("actorId", actorId);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<LogEntry> getLogEntrysByTargetID(long targetId) {
		Query query = em.createNamedQuery("getLogEntrysByTargetID");
		query.setParameter("targetId", targetId);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<LogEntry> getLogEntrysByObjectType(ObjectType objectType) {
		Query query = em.createNamedQuery("getLogEntrysByObjectType");
		query.setParameter("objectType", objectType);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<LogEntry> getLogEntrysByVerb(Verb verb) {
		Query query = em.createNamedQuery("getLogEntrysByVerb");
		query.setParameter("verb", verb);
		return query.getResultList();
	}
}
