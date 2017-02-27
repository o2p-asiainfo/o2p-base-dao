package com.ailk.eaap.op2.common.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.bo.BizFunction;
import com.linkage.rainbow.dao.SqlMapDAO;

public class GetConfigDao implements IGetConfigDao{

	private SqlMapDAO sqlMapDao;
	public List<BizFunction> getBusCode(String code) {
		// TODO Auto-generated method stub
		List<BizFunction> bizFunctionList = (ArrayList<BizFunction>)sqlMapDao.queryForList("getconfig.getBusCode",code) ;
		
		return bizFunctionList;
	}
	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}
	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}
	public Map<String, Object> loadDataSourceRoute() {
		// TODO Auto-generated method stub
		return null;
	}

}
