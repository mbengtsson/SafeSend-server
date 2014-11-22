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
     *
     * @return the registered flood event.
     */
    FloodEvent registerEvent (FloodEvent event);

    /**
     * Registers a flood event.
     *
     * @param eventType
     *         the flood event type.
     * @param identifier
     *         the flood events identifier, usually an IP address.
     *
     * @return the registered flood event.
     */
    FloodEvent registerEvent (FloodType eventType, String identifier);

    /**
     * Registers a flood event.
     *
     * @param eventType
     *         the flood event type.
     * @param identifier
     *         the flood events identifier, usually an IP address.
     * @param expiration
     *         the expiration time when the flood event is considered outdated
     *         and can be purged. Defaults to now + 1 hour.
     *
     * @return the registered flood event.
     */
    FloodEvent registerEvent (FloodType eventType, String identifier, DateTime expiration);

    /**
     * Gets a registered flood event.
     *
     * @param id
     *         the id of the flood event to get.
     *
     * @return the registered flood event with the given id.
     */
    FloodEvent getEvent (long id);
}
