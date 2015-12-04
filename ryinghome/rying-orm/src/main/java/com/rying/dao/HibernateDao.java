package com.rying.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("unchecked")
public class HibernateDao<T, ID extends Serializable> implements
		IBaseDao<T, ID> {

	@Autowired
	private SessionFactory sessionFactory;
	protected Class<T> entityClass;

	public HibernateDao() {
		if (entityClass == null) {
			entityClass = (Class<T>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		}
	}

	@Override
	public void save(T t) {
		this.getSession().save(t);
	}

	@Override
	public void saveOrUpdate(T t) {
		this.getSession().saveOrUpdate(t);
	}

	@Override
	public T load(ID id) {
		T load = (T) this.getSession().load(entityClass, id);
		return load;
	}

	@Override
	public T getById(ID id) {
		T load = (T) this.getSession().get(entityClass, id);
		return load;
	}

	@Override
	public boolean contains(T t) {
		return this.getSession().contains(t);
	}

	@Override
	public void delete(T t) {
		this.getSession().delete(t);
	}

	@Override
	public boolean deleteById(ID Id) {
		T t = getById(Id);
		if (t == null) {
			return false;
		}
		delete(t);
		return true;
	}

	public void deleteAll(Collection<T> entities) {
		for (Object entity : entities) {
			this.getSession().delete(entity);
		}
	}

	@Override
	public void executeHql(String hql, Object[] values) {
		Query query = this.getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		query.executeUpdate();
	}

	@Override
	public void executeSql(String sqlString, Object[] values) {
		Query query = this.getSession().createSQLQuery(sqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		query.executeUpdate();
	}

	@Override
	public T getByHQL(String hql, Object[] values) {
		Query query = this.getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return (T) query.uniqueResult();
	}

	@Override
	public T getBySQL(String sqlString, Object[] values) {
		Query query = this.getSession().createSQLQuery(sqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return (T) query.uniqueResult();
	}

	@Override
	public List<T> queryListByHQL(String hql) {
		return this.queryListByHQL(hql, null);
	}

	@Override
	public List<T> queryListByHQL(String hql, int page, int rows) {
		return this.queryListByHQL(hql, null, page, rows);
	}

	@Override
	public List<T> queryListByHQL(String hql, Object[] values) {
		Query query = this.getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.list();
	}

	@Override
	public List<T> queryListByHQL(String hql, Object[] values, int page,
			int rows) {
		Query query = this.getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}

		if (page > 0 && rows > 0) {
			int startIndex = (page - 1) * rows;
			query.setFirstResult(startIndex);
			query.setMaxResults(rows);
		}

		return query.list();
	}

	@Override
	public List<T> queryListBySQL(String sqlString, Object[] values) {
		Query query = this.getSession().createSQLQuery(sqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.list();
	}

	@Override
	public void refresh(T t) {
		this.getSession().refresh(t);
	}

	@Override
	public void update(T t) {
		this.getSession().update(t);
	}

	@Override
	public Long countByHql(String hql, Object[] values) {
		Query query = this.getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return (Long) query.uniqueResult();
	}

	@Override
	public Long countByHql(String hql) {
		Query query = this.getSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	@Override
	public List<T> queryAll() {
		String hql = "from " + this.entityClass.getName();
		return queryListByHQL(hql);
	}

	public Long countAll() {
		Query query = this.getSession().createQuery(
				"select count(*) from " + this.entityClass.getName());
		return (Long) query.uniqueResult();
	}

	/**
	 * 
	 * @return session
	 */
	public Session getSession() {
		// 需要开启事物，才能得到CurrentSession
		return sessionFactory.getCurrentSession();
	}

}