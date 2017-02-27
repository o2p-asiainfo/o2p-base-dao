package com.ailk.eaap.op2.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.linkage.rainbow.dao.SqlMapDAO;

public class RateDaoImpl implements RateDao{

	private SqlMapDAO sqlMapDao;
	
	@SuppressWarnings("unchecked")
	public int getProductNum(Map inMap) {
		List<HashMap> retList = sqlMapDao.queryForList("eaap-op2-serviceAgent-rate.getProductNum", inMap);
		Map queryMap = retList.get(0);
		return Integer.valueOf(queryMap.get("PRODUCT_NUM").toString()).intValue();
	}

	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}

	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}
}
