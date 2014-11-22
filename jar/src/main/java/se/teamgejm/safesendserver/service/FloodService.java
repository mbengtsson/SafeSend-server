package se.teamgejm.safesendserver.service;

import org.joda.time.DateTime;
import se.teamgejm.safesendserver.domain.FloodEvent;
import se.teamgejm.safesendserver.domain.FloodType;

import javax.ejb.Local;

/**
 * Flood service to handle flood events.
 *
 * @author Emil Stjerneman
 */
@Local
public interface FloodService {

    /**
     * Registers a flood event.
     *
     * @param event
     *         the flood event to register.
     */
    void registerEvent (FloodEvent event);

    /**
     * Registers a flood event.
     *
     * @param eventType
     *         the flood event type.
     * @param identifier
     *         the flood events identifier, usually an IP address.
     */
    void registerEvent (FloodType eventType, String identifier);

    /**
     * Registers a flood event.
     *
     * @param eventType
     *         the flood event type.
     * @param identifier
     *         the flood events identifier, usually an IP address.
     * @param expiration
     *         the expiration time when the flood event is considered expired
     *         and can be purged. Defaults to now + 1 hour.
     */
    void registerEvent (FloodType eventType, String identifier, DateTime expiration);

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
     *
     * Note: This should be executed by CRON, but that is out of scope for this
     * project.
     */
    void purgeExpiredEvents ();
}
