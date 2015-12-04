package com.rying.dao;

import org.springframework.stereotype.Repository;

import com.rying.entity.Menu;

@Repository
public class MenuDao extends HibernateDao<Menu, Integer> implements IMenuDao {
}
