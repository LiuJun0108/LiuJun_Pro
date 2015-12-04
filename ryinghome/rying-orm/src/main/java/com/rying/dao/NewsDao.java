package com.rying.dao;

import org.springframework.stereotype.Repository;

import com.rying.entity.News;

@Repository
public class NewsDao extends HibernateDao<News, Long> implements INewsDao {

}
