package se.teamgejm.safesendserver.dao;

import se.teamgejm.safesendserver.domain.IdHolder;

/**
 * Generic CRUD methods that can be extended by other data access object interfaces
 *
 * @author Marcus Bengtsson
 */
public interface BaseDao<E extends IdHolder> {

	long persist(final E entity);

	void remove(final E entity);

	E findById(final long id);

	void update(final E entity);
}
