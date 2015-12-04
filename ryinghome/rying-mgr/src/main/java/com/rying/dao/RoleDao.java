package com.rying.dao;

import org.springframework.stereotype.Repository;

import com.rying.entity.Role;

@Repository
public class RoleDao extends HibernateDao<Role, Integer> implements IRoleDao {

}
