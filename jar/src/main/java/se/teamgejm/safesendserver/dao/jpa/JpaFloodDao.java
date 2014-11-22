package se.teamgejm.safesendserver.dao.jpa;

import org.joda.time.DateTime;
import se.teamgejm.safesendserver.dao.FloodDao;
import se.teamgejm.safesendserver.domain.FloodEvent;
import se.teamgejm.safesendserver.domain.FloodType;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Emil Stjerneman
 */
@Stateless
public class JpaFloodDao implements FloodDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void registerEvent (final FloodEvent event) {
        em.persist(event);
    }

    @Override
    public void purgeEvents (final FloodType eventType, final String identifier) {
        final Query query = em.createNamedQuery("FloodEvent.purge");
        query.setParameter("event", eventType);
        query.setParameter("identifier", identifier);
        query.executeUpdate();
    }

    @Override
    public void purgeExpiredEvents () {
        final Query query = em.createNamedQuery("FloodEvent.purgeExpired");
        // We can't use hibernates default 'CURRENT_TIME' method here as the
        // saved datetime is calculated from GMT0.
        query.setParameter("dateNow", DateTime.now());
        query.executeUpdate();
    }

    @Override
    public long isAllowed (final FloodType eventType, final String identifier) {
        final Query query = em.createNamedQuery("FloodEvent.isAllowed");
        query.setParameter("event", eventType);
        query.setParameter("identifier", identifier);
        return (Long) query.getSingleResult();
    }


}
