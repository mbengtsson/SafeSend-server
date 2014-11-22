package se.teamgejm.safesendserver.dao;

import se.teamgejm.safesendserver.domain.FloodEvent;
import se.teamgejm.safesendserver.domain.FloodType;

import javax.ejb.Local;

/**
 * Flood Dao to handle flood event entities in a database.
 *
 * @author Emil Stjerneman
 */
@Local
public interface FloodDao {

    /**
     * Registers a flood event.
     *
     * @param event
     *         the flood event to register.
     */
    void registerEvent (FloodEvent event);

    /**
     * Purges events with given conditions.
     *
     * @param eventType
     *         the flood event type.
     * @param identifier
     *         the flood events identifier.
     */
    void purgeEvents (FloodType eventType, String identifier);

    /**
     * Purges events that has expired in time.
     */
    void purgeExpiredEvents ();
}
