package com.ailk.eaap.op2.informationArchiving.dao;

import com.ailk.eaap.op2.bo.informationArchiving.InsUserExt;
import com.linkage.rainbow.dao.SqlMapDAO;

public class InsUserExtDAOImpl implements InsUserExtDAO {
	private SqlMapDAO sqlMapDao;
    public InsUserExtDAOImpl() {
        super();
    }
    public void deleteByPrimaryKey(Long insUserExtId) {
        InsUserExt _key = new InsUserExt();
        _key.setInsUserExtId(insUserExtId);
        sqlMapDao.delete("ins_user_ext.deleteByPrimaryKey", _key);
    }
    public void insert(InsUserExt record) {
    	sqlMapDao.insert("ins_user_ext.insertSelective", record);
    }

    public InsUserExt selectByPrimaryKey(Long insUserExtId) {
        InsUserExt _key = new InsUserExt();
        _key.setInsUserExtId(insUserExtId);
        InsUserExt record = (InsUserExt)sqlMapDao.queryForObject("ins_user_ext.selectByPrimaryKey", _key);
        return record;
    }
    public void updateByPrimaryKey(InsUserExt record) {
       sqlMapDao.update("ins_user_ext.updateByPrimaryKeySelective", record);
    }
	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}
	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}
    
    
}