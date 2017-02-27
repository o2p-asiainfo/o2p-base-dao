package com.ailk.eaap.op2.common.dao.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ailk.eaap.op2.bo.JdbcDataSource;
import com.ailk.eaap.op2.bo.TransformerRule;
import com.asiainfo.integration.o2p.basedao.dao.impl.IbatisDaoImpl;
import com.asiainfo.integration.o2p.basedao.dao.impl.TransformerRuleDaoImpl;


public class BaseDataDaoTest {
	ApplicationContext app = null;
	@Before
	public void setUp(){
		app = new ClassPathXmlApplicationContext(new String[]{"spring/o2p-baseDao-spring.xml"});
	}
	@Test
	public void test(){
		TransformerRuleDaoImpl transformerRuleDao = (TransformerRuleDaoImpl) app.getBean("transformerRuleDao");
		List<TransformerRule> list = transformerRuleDao.getAllUsable();
		System.out.println(list.get(0).getId());
		Assert.assertNotNull(list);
	}
	
	
	@Test
	public void testContractVer(){
		IbatisDaoImpl ibatisDao = (IbatisDaoImpl) app.getBean("ibatisDao");
		ibatisDao.getContractVer(null);
		
	}
	
	@Test
	public void getConfPropertiesTest() {
		IbatisDaoImpl ibatisDao = (IbatisDaoImpl) app.getBean("ibatisDao");
		System.out.println(ibatisDao.getConfProperties().size());
	}
}
