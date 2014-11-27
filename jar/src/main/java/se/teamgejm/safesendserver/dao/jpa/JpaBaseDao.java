package se.teamgejm.safesendserver.dao.jpa;

import se.teamgejm.safesendserver.dao.BaseDao;
import se.teamgejm.safesendserver.domain.IdHolder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

/**
 * JPA implementation of the BaseDao, needs to be extended by any data access object that uses BaseDao for CRUD
 *
 * @author Marcus Bengtsson
 */

public abstract class JpaBaseDao<E extends IdHolder> implements BaseDao<E> {

	@PersistenceContext
	protected EntityManager em;
	private Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public JpaBaseDao() {
		final ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
	}

	@Override
	public long persist(final E entity) {
		em.persist(entity);
		return entity.getId();
	}

	@Override
	public void remove(final E entity) {
		em.remove(em.getReference(entityClass, entity.getId()));
	}

	@Override
	public E findById(final long id) {
		return em.find(entityClass, id);
	}

	@Override
	public void update(final E entity) {
		em.merge(entity);
	}
}
