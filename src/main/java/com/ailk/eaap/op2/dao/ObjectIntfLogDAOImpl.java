package com.ailk.eaap.op2.dao;

import java.util.List;

import com.ailk.eaap.op2.bo.ObjectInfoLog;
import com.linkage.rainbow.dao.SqlMapDAO;

public class ObjectIntfLogDAOImpl  implements ObjectIntfLogDAO {

	private SqlMapDAO sqlMapDao;
	
	public ObjectIntfLogDAOImpl() {
        super();
    }
    public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}
	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}

	public void insertObjectInftLog(ObjectInfoLog objectInfoLog){
		sqlMapDao.insert("objectInfoLog.insertObjectInftLog", objectInfoLog);
	}
    public void updateObjectInftLog(ObjectInfoLog objectInfoLog){
    	sqlMapDao.update("objectInfoLog.updateObjectInftLog", objectInfoLog);
    }
    @SuppressWarnings("unchecked")
	public List<ObjectInfoLog> qryselectObjectInftLog(ObjectInfoLog objectInfoLog){
    	return sqlMapDao.queryForList("objectInfoLog.selectObjectInftLog", objectInfoLog);
    }
}