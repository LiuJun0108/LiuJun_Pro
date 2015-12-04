package com.rying.dao;

import org.springframework.stereotype.Repository;

import com.rying.entity.InvestorRelations;

@Repository
public class InvestorRelationsDao extends HibernateDao<InvestorRelations, Long> implements IInvestorRelationsDao {

}
