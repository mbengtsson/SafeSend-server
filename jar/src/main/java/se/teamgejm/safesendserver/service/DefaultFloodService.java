package se.teamgejm.safesendserver.service;

import org.joda.time.DateTime;
import se.teamgejm.safesendserver.dao.FloodDao;
import se.teamgejm.safesendserver.domain.FloodEvent;
import se.teamgejm.safesendserver.domain.FloodType;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Emil Stjerneman
 */
@Stateless
public class DefaultFloodService implements FloodService {

    @Inject
    private FloodDao floodDao;

    @Override
    public FloodEvent registerEvent (FloodEvent event) {
        long id = floodDao.registerEvent(event);
        return getEvent(id);
    }

    @Override
    public FloodEvent registerEvent (FloodType eventType, String identifier) {
        final FloodEvent event = new FloodEvent(eventType, identifier, DateTime.now(), DateTime.now().plusHours(1));
        long id = floodDao.registerEvent(event);
        return getEvent(id);
    }

    @Override
    public FloodEvent registerEvent (FloodType eventType, String identifier, DateTime expiration) {
        final FloodEvent event = new FloodEvent(eventType, identifier, DateTime.now(), expiration);
        long id = floodDao.registerEvent(event);
        return getEvent(id);
    }

    @Override
    public FloodEvent getEvent (long id) {
        return floodDao.getEvent(id);
    }
}
