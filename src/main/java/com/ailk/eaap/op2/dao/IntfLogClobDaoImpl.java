package com.ailk.eaap.op2.dao;

import com.ailk.eaap.op2.bo.IntfClobLog;
import com.linkage.rainbow.dao.SqlMapDAO;

/** 
 * @ClassName: IntfLogClobDaoImpl 
 * @Description: 
 * @author zhengpeng
 * @date 2014-12-1 下午3:57:38 
 * @version V1.0  
 */
public class IntfLogClobDaoImpl implements IntfLogClobDao{
	

	private SqlMapDAO sqlMapDao;
	
	public IntfLogClobDaoImpl() {
        super();
    }


    public void deleteByPrimaryKey(Long olcId) {
        IntfClobLog _key = new IntfClobLog();
        _key.setOlcId(olcId);
        sqlMapDao.delete("intf_log_clob.deleteByPrimaryKey", _key);
    }



    public void insertSelective(IntfClobLog record) {
    	sqlMapDao.insert("intf_log_clob.insertSelective", record);
    }


    public IntfClobLog selectByPrimaryKey(Long olcId) {
        IntfClobLog _key = new IntfClobLog();
        _key.setOlcId(olcId);
        return (IntfClobLog) sqlMapDao.queryForObject("intf_log_clob.selectByPrimaryKey", _key);
    }


    public int updateByPrimaryKeySelective(IntfClobLog record) {
    	return sqlMapDao.update("intf_log_clob.updateByPrimaryKeySelective", record);
    }
    
    public IntfClobLog selectIntfLogClob(IntfClobLog intfClobLog){
    	return (IntfClobLog) sqlMapDao.queryForObject("intf_log_clob.selectIntfLogClob", intfClobLog);
   }

    
    public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}


	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}

}
