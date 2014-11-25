package se.teamgejm.safesendserver.dao;

import se.teamgejm.safesendserver.domain.floodevent.FloodEvent;
import se.teamgejm.safesendserver.domain.floodevent.FloodType;

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
	 * @param event the flood event to register.
	 */
	void registerEvent(final FloodEvent event);

	/**
	 * Purges events with given conditions.
	 *
	 * @param eventType  the flood event type.
	 * @param identifier the flood events identifier.
	 */
	void purgeEvents(final FloodType eventType, final String identifier);

	/**
	 * Purges events that has expired in time.
	 */
	void purgeExpiredEvents();

	/**
	 * Calculates the number of entrys in the database with the given
	 * conditions.
	 *
	 * @param eventType  the flood event type.
	 * @param identifier the flood events identifier.
	 * @return the number of entrys in the database with the given conditions.
	 */
	long isAllowed(final FloodType eventType, final String identifier);
}
