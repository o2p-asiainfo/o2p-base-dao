package com.ailk.eaap.op2.informationArchiving.dao;


import com.ailk.eaap.op2.bo.informationArchiving.InsCustContactInfo;
import com.linkage.rainbow.dao.SqlMapDAO;

public class InsCustContactInfoDAOImpl  implements InsCustContactInfoDAO {
	private SqlMapDAO sqlMapDao;

    public InsCustContactInfoDAOImpl() {
        super();
    }

    public  void deleteByPrimaryKey(Long custId){
        InsCustContactInfo _key = new InsCustContactInfo();
        _key.setCustId(custId);
        sqlMapDao.delete("ins_cust_contact_info.deleteByPrimaryKey", _key);
    }
    
    public void insert(InsCustContactInfo record) {
    	sqlMapDao.insert("ins_cust_contact_info.insertSelective", record);
    }
    
    public InsCustContactInfo selectByPrimaryKey(Long custId) {
        InsCustContactInfo _key = new InsCustContactInfo();
        _key.setCustId(custId);
        InsCustContactInfo record = (InsCustContactInfo) sqlMapDao.queryForObject("ins_cust_contact_info.selectByPrimaryKey", _key);
        return record;
    }

    public void updateByPrimaryKey(InsCustContactInfo record) {
        sqlMapDao.update("ins_cust_contact_info.updateByPrimaryKeySelective", record);
    }

	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}

	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}
    
    
}