package com.rying.dao;

import org.springframework.stereotype.Repository;

import com.rying.entity.Resources;

@Repository
public class ResourcesDao extends HibernateDao<Resources, Integer> implements IResourcesDao {

}
