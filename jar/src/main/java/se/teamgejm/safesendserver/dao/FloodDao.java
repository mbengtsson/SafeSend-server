package se.teamgejm.safesendserver.dao;

import se.teamgejm.safesendserver.domain.FloodEvent;

import javax.ejb.Local;

/**
 * @author Emil Stjerneman
 */
@Local
public interface FloodDao {

    long registerEvent (FloodEvent event);

    FloodEvent getEvent (long id);
}
