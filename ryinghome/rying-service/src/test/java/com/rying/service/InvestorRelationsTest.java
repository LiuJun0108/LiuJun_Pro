package com.rying.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;

import com.rying.entity.InvestorRelations;

public class InvestorRelationsTest extends BaseTest {
	@Resource
	private IInvestorRelationsService serivce;

	@Test
	@Transactional
	public void testAddInvestorRelations() {
		InvestorRelations ir = new InvestorRelations();
		ir.setTitle("a");
		ir.setFilePath("a");

		this.serivce.addInvestorRelations(ir);
	}

	@Test
	public void testQueryList() {
		List<InvestorRelations> list = this.serivce.queryList(new Date());
//		Assert.assertTrue(!list.isEmpty());
	}
}
