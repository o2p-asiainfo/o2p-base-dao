package com.ailk.eaap.op2.informationArchiving.dao;

import com.ailk.eaap.op2.bo.informationArchiving.InsOffer;
import com.linkage.rainbow.dao.SqlMapDAO;

public class InsOfferDAOImpl  implements InsOfferDAO {
	private SqlMapDAO sqlMapDao;
    public InsOfferDAOImpl() {
        super();
    }

    public void deleteByPrimaryKey(Long offerInstId) {
       sqlMapDao.delete("ins_offer.deleteByPrimaryKey", offerInstId);
    }

    public void insert(InsOffer record) {
    	sqlMapDao.insert("ins_offer.insertSelective", record);
    }


    public InsOffer selectByPrimaryKey(Long offerInstId) {
    	InsOffer _key = new InsOffer();
         _key.setOfferInstId(offerInstId);
        InsOffer record = (InsOffer) sqlMapDao.queryForObject("ins_offer.selectByPrimaryKey", _key);
        return record;
    }


    public void updateByPrimaryKey(InsOffer record) {
       sqlMapDao.update("ins_offer.updateByPrimaryKeySelective", record);
    }

	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}

	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}
    
    
}