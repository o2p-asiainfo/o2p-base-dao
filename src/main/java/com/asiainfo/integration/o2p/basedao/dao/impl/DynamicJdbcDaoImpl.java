package com.asiainfo.integration.o2p.basedao.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ailk.eaap.op2.bo.CsvTemplate;
import com.ailk.eaap.op2.bo.JdbcDataSource;
import com.asiainfo.integration.o2p.basedao.dao.IDynamicJdbcDao;

public class DynamicJdbcDaoImpl implements IDynamicJdbcDao, Serializable {

	private static final long serialVersionUID = 1L;
	private transient SqlMapClientTemplate sqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<JdbcDataSource> getAllDataSource() {
		return sqlMapClientTemplate.queryForList("o2p-serviceAgent-core.getAllDataSource",null);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CsvTemplate> getAllCsvTemplate() {
		return sqlMapClientTemplate.queryForList("o2p-serviceAgent-core.getAllCsvTemplate", null);
	}

	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(
			SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

}
