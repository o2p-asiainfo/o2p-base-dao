package com.asiainfo.integration.o2p.basedao.dao;

import java.util.List;

import com.ailk.eaap.op2.bo.CsvTemplate;
import com.ailk.eaap.op2.bo.JdbcDataSource;

public interface IDynamicJdbcDao {

	List<JdbcDataSource> getAllDataSource();
	
	List<CsvTemplate> getAllCsvTemplate();

}
