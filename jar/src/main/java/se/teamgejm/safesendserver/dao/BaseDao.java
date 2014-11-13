package se.teamgejm.safesendserver.dao;

import se.teamgejm.safesendserver.domain.IdHolder;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
public interface BaseDao<E extends IdHolder> {

	long persist(E entity);

	void remove(E entity);

	E findById(long id);

	void update(E entity);
}
