package com.ailk.eaap.op2.informationArchiving.dao;

import com.ailk.eaap.op2.bo.informationArchiving.InsProd;
import com.linkage.rainbow.dao.SqlMapDAO;

public class InsProdDAOImpl implements InsProdDAO {
	
	private SqlMapDAO sqlMapDao;
    public InsProdDAOImpl() {
        super();
    }

    public void deleteByPrimaryKey(Long prodInstId ) {
        sqlMapDao.delete("ins_prod.deleteByPrimaryKey", prodInstId);
    }
    
    public void insert(InsProd record) {
    	sqlMapDao.insert("ins_prod.insertSelective", record);
    }

    public InsProd selectByPrimaryKey(InsProd record) {
    	InsProd insProdNew=new InsProd();
    	insProdNew.setProdId(record.getProdId());
    	insProdNew.setUserId(record.getUserId());
        InsProd insProdAfter = (InsProd) sqlMapDao.queryForObject("ins_prod.selectByPrimaryKey", insProdNew);
        return insProdAfter;
    }

    public void updateByPrimaryKey(InsProd record) {
       sqlMapDao.update("ins_prod.updateByPrimaryKeySelective", record);
    }

	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}

	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}
    
    
}