package com.ailk.eaap.op2.informationArchiving.dao;

import com.ailk.eaap.op2.bo.informationArchiving.InsUser;
import com.linkage.rainbow.dao.SqlMapDAO;

public class InsUserDAOImpl  implements InsUserDAO {
	private SqlMapDAO sqlMapDao;
    public InsUserDAOImpl() {
        super();
    }
    public void deleteByPrimaryKey(Long userId) {
        InsUser _key = new InsUser();
        _key.setUserId(userId);
        sqlMapDao.delete("ins_user.deleteByPrimaryKey", _key);
    }

    public void insert(InsUser record) {
    	sqlMapDao.insert("ins_user.insertSelective", record);
    }


    public InsUser selectByPrimaryKey(Long userId) {
        InsUser _key = new InsUser();
        _key.setUserId(userId);
        InsUser record = (InsUser) sqlMapDao.queryForObject("ins_user.selectByPrimaryKey", _key);
        return record;
    }


    public void updateByPrimaryKey(InsUser record) {
       sqlMapDao.update("ins_user.updateByPrimaryKeySelective", record);
    }
	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}
	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}
    
    
}