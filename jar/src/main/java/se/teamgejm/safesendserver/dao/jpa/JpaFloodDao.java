package se.teamgejm.safesendserver.dao.jpa;

import se.teamgejm.safesendserver.dao.FloodDao;
import se.teamgejm.safesendserver.domain.FloodEvent;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Emil Stjerneman
 */
public class JpaFloodDao implements FloodDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long registerEvent (FloodEvent event) {
        em.persist(event);
        return event.getId();
    }

    @Override
    public FloodEvent getEvent (long id) {
        return em.find(FloodEvent.class, id);
    }
}
