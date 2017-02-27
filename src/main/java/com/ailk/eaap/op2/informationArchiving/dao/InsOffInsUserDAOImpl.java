package com.ailk.eaap.op2.informationArchiving.dao;

import com.ailk.eaap.op2.bo.informationArchiving.InsOffInsUser;
import com.linkage.rainbow.dao.SqlMapDAO;

public class InsOffInsUserDAOImpl implements InsOffInsUserDAO {
	private SqlMapDAO sqlMapDao;
    public InsOffInsUserDAOImpl() {
        super();
    }
    public void deleteByPrimaryKey(Long userId) {
        InsOffInsUser _key = new InsOffInsUser();
        _key.setUserId(userId);
        sqlMapDao.delete("ins_off_ins_user.deleteByPrimaryKey", _key);
    }

    public void insert(InsOffInsUser record) {
    	sqlMapDao.insert("ins_off_ins_user.insertSelective", record);
    }

    public InsOffInsUser selectByPrimaryKey(Long userId) {
        InsOffInsUser _key = new InsOffInsUser();
        _key.setUserId(userId);
        InsOffInsUser record = (InsOffInsUser)sqlMapDao.queryForObject("ins_off_ins_user.selectByPrimaryKey", _key);
        return record;
    }
    public void updateByPrimaryKey(InsOffInsUser record) {
        sqlMapDao.update("ins_off_ins_user.updateByPrimaryKeySelective", record);
    }
	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}
	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}
    
    
}