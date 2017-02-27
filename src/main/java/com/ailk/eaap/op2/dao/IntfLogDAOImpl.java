package com.ailk.eaap.op2.dao;

import java.util.List;

import com.ailk.eaap.op2.bo.IntfLog;
import com.linkage.rainbow.dao.SqlMapDAO;

public class IntfLogDAOImpl  implements IntfLogDAO {

	private SqlMapDAO sqlMapDao;
	
	public IntfLogDAOImpl() {
        super();
    }

    public void deleteByPrimaryKey(String contractInteractionId) {
        IntfLog _key = new IntfLog();
        _key.setContractInteractionId(contractInteractionId);
        sqlMapDao.delete("intf_log.deleteByPrimaryKey", _key);
    }


    public void insertSelective(IntfLog record) {
    	sqlMapDao.insert("intf_log.insertSelective", record);
    }
    
    public IntfLog selectIntfLog(IntfLog intfLog){
    	return (IntfLog) sqlMapDao.queryForObject("intf_log.selectIntfLog", intfLog);
    }

    public IntfLog selectByPrimaryKey(String contractInteractionId) {
        IntfLog _key = new IntfLog();
        _key.setContractInteractionId(contractInteractionId);
        return (IntfLog) sqlMapDao.queryForObject("intf_log.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(IntfLog record) {
    	return sqlMapDao.update("intf_log.updateByPrimaryKeySelective", record);
     }
    
    @SuppressWarnings("unchecked")
    public IntfLog selectIntfLogList(IntfLog intfLog){
		List<IntfLog> intfLogList = sqlMapDao.queryForList("intf_log.selectIntfLogList", intfLog);
    	IntfLog result = null;
    	if(intfLogList != null && intfLogList.size() > 0){
    		result = intfLogList.get(0);
    	}
    	return result;
    }
    
    public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}

	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}

}