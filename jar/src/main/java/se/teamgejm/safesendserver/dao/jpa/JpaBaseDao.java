package se.teamgejm.safesendserver.dao.jpa;

import se.teamgejm.safesendserver.dao.BaseDao;
import se.teamgejm.safesendserver.domain.IdHolder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
public abstract class JpaBaseDao<E extends IdHolder> implements BaseDao<E> {

	private Class<E> entityClass;

	@PersistenceContext
	protected EntityManager em;

	@SuppressWarnings("unchecked")
	public JpaBaseDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
	}

	@Override
	public long persist(E entity) {
		em.persist(entity);
		return entity.getId();
	}

	@Override
	public void remove(E entity) {
		em.remove(em.getReference(entityClass, entity.getId()));
	}

	@Override
	public E findById(long id) {
		return em.find(entityClass, id);
	}

	@Override
	public void update(E entity) {
		em.merge(entity);
	}
}
