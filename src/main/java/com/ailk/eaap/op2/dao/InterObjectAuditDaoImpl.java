package com.ailk.eaap.op2.dao;

import java.util.List;

import com.ailk.eaap.op2.bo.InterObjectAudit;
import com.linkage.rainbow.dao.SqlMapDAO;

public class InterObjectAuditDaoImpl implements InterObjectAuditDao {
	private SqlMapDAO sqlMapDao;
	
	public String addInterObjectAudit(InterObjectAudit interObjectAudit){
		return (String) sqlMapDao.insert("interObjAudit.insertInterObjAudit", interObjectAudit);
	}
	
	public Integer updateInterObjectAudit(InterObjectAudit interObjectAudit){
		return sqlMapDao.update("interObjAudit.updateInterObjAudit", interObjectAudit);
	}
	
	@SuppressWarnings("unchecked")
	public List<InterObjectAudit> qryInterObjectAudit(InterObjectAudit interObjectAudit){
		return sqlMapDao.queryForList("interObjAudit.selectInterObjAudit", interObjectAudit);
	}
	
	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}
	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}
}
