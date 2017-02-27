package com.ailk.eaap.op2.informationArchiving.dao;

import com.ailk.eaap.op2.bo.informationArchiving.InsOfferRelate;
import com.linkage.rainbow.dao.SqlMapDAO;

public class InsOfferRelateDAOImpl implements InsOfferRelateDAO {
	private SqlMapDAO sqlMapDao;
    public InsOfferRelateDAOImpl() {
        super();
    }
    public void deleteByPrimaryKey(Long relatId) {
        sqlMapDao.delete("ins_offer_relat.deleteByPrimaryKey", relatId);
    }

    public void insert(InsOfferRelate record) {
    	sqlMapDao.insert("ins_offer_relat.insertSelective", record);
    }

    public InsOfferRelate selectByPrimaryKey(Long relatId) {
    	InsOfferRelate _key = new InsOfferRelate();
        _key.setOfferInstId(relatId);
        InsOfferRelate record = (InsOfferRelate) sqlMapDao.queryForObject("ins_offer_relat.selectByPrimaryKey", relatId);
        return record;
    }
    public void updateByPrimaryKey(InsOfferRelate record) {
        sqlMapDao.update("ins_offer_relat.updateByPrimaryKeySelective", record);
    }
	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}
	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}
    
    
}