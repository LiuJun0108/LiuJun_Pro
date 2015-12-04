package com.rying.dao;

import org.springframework.stereotype.Repository;

import com.rying.entity.SysUser;

@Repository
public class SysUserDao extends HibernateDao<SysUser, Integer> implements ISysUserDao {

}
