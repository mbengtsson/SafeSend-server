package se.teamgejm.safesendserver.dao.jpa;

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
    public void registerEvent (FloodEvent event) {
        em.persist(event);
    }

    @Override
    public void purgeEvents (FloodType eventType, String identifier) {
        Query query = em.createNamedQuery("FloodEvent.purge");
        query.setParameter("event", eventType);
        query.setParameter("identifier", identifier);
        query.executeUpdate();
    }

    @Override
    public void purgeExpiredEvents () {
        Query query = em.createNamedQuery("FloodEvent.purgeExpired");
        System.out.println(query.toString());
        query.executeUpdate();
    }
}
