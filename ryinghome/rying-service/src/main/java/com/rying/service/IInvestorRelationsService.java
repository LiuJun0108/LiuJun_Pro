package com.rying.service;

import java.util.Date;
import java.util.List;

import com.rying.entity.InvestorRelations;
import com.rying.entity.SysUser;

public interface IInvestorRelationsService {
	void addInvestorRelations(InvestorRelations ir);

	void updateInvestorRelations(Long id, String title, String filepath);

	Long countAll();
	
	Long countAll(Date years);
	
	List<InvestorRelations> pageList(int pageNo, int pageSize);

	/**
	 * 按年份分页数据
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param years
	 * @return
	 */
	List<InvestorRelations> pageList(int pageNo, int pageSize, Date years);

	void deleteIrById(long id);

	InvestorRelations getInvestorRelationsById(long id);

	List<InvestorRelations> queryList(Date d);

	void deleteIrBySysUser(SysUser sysUser);
}
