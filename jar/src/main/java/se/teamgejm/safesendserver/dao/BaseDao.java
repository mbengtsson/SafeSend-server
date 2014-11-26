package se.teamgejm.safesendserver.dao;

import se.teamgejm.safesendserver.domain.IdHolder;

/**
 * Generic CRUD methods that can be extended by other data access object interfaces
 *
 * @author Marcus Bengtsson
 */
public interface BaseDao<E extends IdHolder> {

	long persist(E entity);

	void remove(E entity);

	E findById(long id);

	void update(E entity);
}
