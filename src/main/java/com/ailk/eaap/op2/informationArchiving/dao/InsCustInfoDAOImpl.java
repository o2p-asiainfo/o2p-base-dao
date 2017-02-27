package com.ailk.eaap.op2.informationArchiving.dao;

import com.ailk.eaap.op2.bo.informationArchiving.InsCustInfo;
import com.linkage.rainbow.dao.SqlMapDAO;

public class InsCustInfoDAOImpl implements InsCustInfoDAO {
	private SqlMapDAO sqlMapDao;

    public InsCustInfoDAOImpl() {
        super();
    }

    public void deleteByPrimaryKey(Long custId) {
        InsCustInfo _key = new InsCustInfo();
        _key.setCustId(custId);
        sqlMapDao.delete("ins_cust_info.deleteByPrimaryKey", _key);
    }
    
    public void insert(InsCustInfo record) {
    	sqlMapDao.insert("ins_cust_info.insertSelective", record);
    }

    public InsCustInfo selectByPrimaryKey(Long custId) {
        InsCustInfo _key = new InsCustInfo();
        _key.setCustId(custId);
        InsCustInfo record = (InsCustInfo) sqlMapDao.queryForObject("ins_cust_info.selectByPrimaryKey", _key);
        return record;
    }
    public void updateByPrimaryKey(InsCustInfo record) {
       sqlMapDao.update("ins_cust_info.updateByPrimaryKeySelective", record);
    }

	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}

	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}
    
    
}