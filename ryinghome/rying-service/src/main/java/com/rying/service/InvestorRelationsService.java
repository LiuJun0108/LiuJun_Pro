package com.rying.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.rying.dao.IInvestorRelationsDao;
import com.rying.entity.InvestorRelations;
import com.rying.entity.SysUser;
import com.rying.util.DateUtil;
import com.rying.util.PropertiesUtil;
import com.rying.util.StrValidator;

@Service
public class InvestorRelationsService implements IInvestorRelationsService {
	private static Logger log = Logger.getLogger(InvestorRelationsService.class);

	@Resource
	private IInvestorRelationsDao investorRelationsDao;

	@Override
	public void addInvestorRelations(InvestorRelations ir) {
		if (ir.getCreatetime() == null) {
			ir.setCreatetime(new Date());
		}
		this.investorRelationsDao.save(ir);
	}

	@Override
	public void updateInvestorRelations(Long id, String title, String filepath) {
		InvestorRelations oldIr = this.investorRelationsDao.getById(id);
		if (StrValidator.isNotNullAndEmpty(title)) {
			oldIr.setTitle(title);
		}
		if (StrValidator.isNotNullAndEmpty(filepath)) {
			deleteFiel(filepath);
			oldIr.setFilePath(filepath);
		}
		this.investorRelationsDao.update(oldIr);
	}

	private void deleteFiel(String filepath) {
		if (filepath == null)
			return;
		File file = new File(PropertiesUtil.get("upload_path") + "/", filepath);
		if (file.exists()) {
			file.delete();
			log.info("删除文件：" + file.getName());
		}
	}

	@Override
	public Long countAll() {
		return this.investorRelationsDao.countAll();
	}

	@Override
	public Long countAll(Date years) {
		if (years == null) {
			return this.countAll();
		}

		Date start = DateUtil.getPeriodStartDate(1, years);
		Date end = DateUtil.getPeriodEndDate(1, years);

		String hql = "select count(*) from InvestorRelations as ir where ir.createtime >= ? and ir.createtime <= ?";
		log.info("hql = " + hql);

		return this.investorRelationsDao.countByHql(hql, new Object[] { start, end });
	}

	@Override
	public List<InvestorRelations> pageList(int pageNo, int pageSize) {
		String hql = "from InvestorRelations as ir order by ir.createtime desc";
		log.info("hql = " + hql);

		return this.investorRelationsDao.queryListByHQL(hql, pageNo, pageSize);
	}

	@Override
	public List<InvestorRelations> pageList(int pageNo, int pageSize, Date years) {
		if (years == null) {
			return this.pageList(pageNo, pageSize);
		}

		Date start = DateUtil.getPeriodStartDate(1, years);
		Date end = DateUtil.getPeriodEndDate(1, years);

		String hql = "from InvestorRelations as ir where ir.createtime >= ? and ir.createtime <= ? order by ir.createtime desc";
		log.info("hlq = " + hql);

		return this.investorRelationsDao.queryListByHQL(hql, new Object[] { start, end }, pageNo, pageSize);
	}

	@Override
	public void deleteIrById(long id) {
		InvestorRelations ir = this.investorRelationsDao.getById(id);
		this.deleteFiel(ir.getFilePath());

		this.investorRelationsDao.deleteById(id);
	}

	@Override
	public InvestorRelations getInvestorRelationsById(long id) {
		return this.investorRelationsDao.getById(id);
	}

	@Override
	public List<InvestorRelations> queryList(Date d) {
		Date start = DateUtil.getPeriodStartDate(1, d);
		Date end = DateUtil.getPeriodEndDate(1, d);
		String hql = "from InvestorRelations as ir where ir.createtime >= ? and ir.createtime <= ? order by ir.createtime desc";
		log.info("hql = " + hql);

		return this.investorRelationsDao.queryListByHQL(hql, new Object[] { start, end });
	}

	@Override
	public void deleteIrBySysUser(SysUser sysUser) {
		String hql = "from InvestorRelations as ir where ir.publishUser = ?";
		log.info("hql = " + hql);

		List<InvestorRelations> list = this.investorRelationsDao.queryListByHQL(hql, new Object[] { sysUser });
		for (InvestorRelations ir : list) {
			this.deleteIrById(ir);
		}
	}

	private void deleteIrById(InvestorRelations ir) {
		this.deleteIrById(ir.getId());
	}

	public static void main(String[] args) {
		Date d = new Date();
		Date start = DateUtil.getPeriodStartDate(1, d);
		Date end = DateUtil.getPeriodEndDate(1, d);
		DateUtil.print(start);
		DateUtil.print(end);
	}

}
