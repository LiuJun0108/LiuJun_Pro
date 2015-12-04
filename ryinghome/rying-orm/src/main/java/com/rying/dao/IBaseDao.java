package com.rying.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface IBaseDao<T, ID extends Serializable> {

	public abstract void save(T t);

	public abstract void saveOrUpdate(T t);

	public abstract T load(ID id);

	public abstract T getById(ID id);

	public abstract boolean contains(T t);

	public abstract void delete(T t);

	public abstract boolean deleteById(ID Id);

	public abstract void deleteAll(Collection<T> entities);

	public abstract void executeHql(String hql, Object[] values);

	public abstract void executeSql(String hql, Object[] values);

	public abstract T getByHQL(String hql, Object[] values);

	public abstract T getBySQL(String hql, Object[] values);

	public abstract List<T> queryListByHQL(String hql, Object[] values);

	public abstract List<T> queryListBySQL(String hql, Object[] values);

	public abstract void refresh(T t);

	public abstract void update(T t);

	public abstract Long countByHql(String hql, Object[] values);

	public abstract List<T> queryListByHQL(String hql, int page, int rows);

	public abstract Long countByHql(String hql);

	public abstract List<T> queryListByHQL(String hql);

	public abstract List<T> queryAll();

	public abstract Long countAll();

	public abstract List<T> queryListByHQL(String hql, Object[] values,
			int page, int rows);

}