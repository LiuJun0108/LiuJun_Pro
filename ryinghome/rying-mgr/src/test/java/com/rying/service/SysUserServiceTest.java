package com.rying.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rying.easyui.model.Datagrid;
import com.rying.entity.SysUser;
import com.rying.model.SysUserQueryVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring*.xml" })
public class SysUserServiceTest {
	@Resource
	private ISysUserService sysUserService;

	@Test
	public void test() {
		Datagrid<SysUser> dg = this.sysUserService.datagrid(new SysUserQueryVo(null, null, null, null, null, null, null, null), null, null, 1, 10);
		System.out.println(dg.getRows().size());
	}
}
