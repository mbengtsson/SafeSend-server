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
    public void registerEvent (FloodEvent event) {
        floodDao.registerEvent(event);
    }

    @Override
    public void registerEvent (FloodType eventType, String identifier) {
        final FloodEvent event = new FloodEvent(eventType, identifier, DateTime.now(), DateTime.now().plusHours(1));
        floodDao.registerEvent(event);
    }

    @Override
    public void registerEvent (FloodType eventType, String identifier, DateTime expiration) {
        final FloodEvent event = new FloodEvent(eventType, identifier, DateTime.now(), expiration);
        floodDao.registerEvent(event);
    }

    @Override
    public void purgeEvents (FloodType eventType, String identifier) {
        floodDao.purgeEvents(eventType, identifier);
    }

    @Override
    public void purgeExpiredEvents () {
        floodDao.purgeExpiredEvents();
    }
}
