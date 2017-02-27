package com.asiainfo.integration.o2p.international.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ailk.eaap.op2.bo.LocaleRegConfig;
import com.ailk.eaap.op2.bo.LocaleSet;
import com.asiainfo.integration.o2p.international.dao.BaseDao;

public class BaseDaoImpl extends SqlMapClientDaoSupport  implements BaseDao  {

	@SuppressWarnings("unchecked")
	@Override
	public List<LocaleSet> getAllLocalSet() {
		
		Map map = new HashMap();
		return this.getSqlMapClientTemplate().queryForList("loadInternational.getAllLocaleSet", map);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LocaleRegConfig> getAllLocaleRegConfig() {
		
		Map map = new HashMap();
		return this.getSqlMapClientTemplate().queryForList("loadInternational.getAllLocaleRegConfig", map);
	}
	
}
