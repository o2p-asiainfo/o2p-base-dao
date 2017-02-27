package com.ailk.eaap.op2.informationArchiving.dao;

import java.util.List;

import com.ailk.eaap.op2.bo.informationArchiving.InsProd;
import com.ailk.eaap.op2.bo.informationArchiving.InsProdAttr;
import com.linkage.rainbow.dao.SqlMapDAO;

public class InsProdAttrDAOImpl  implements InsProdAttrDAO {
	private SqlMapDAO sqlMapDao;

    public InsProdAttrDAOImpl() {
        super();
    }

    public void deleteByPrimaryKey(Long priceAttrInstId) {
         sqlMapDao.delete("ins_prod_attr.deleteByPrimaryKey", priceAttrInstId);
    }
    public void insert(InsProdAttr record) {
    	sqlMapDao.insert("ins_prod_attr.insertSelective", record);
    }


    public InsProdAttr selectByPrimaryKey(InsProdAttr insProdAttr) {
    	InsProdAttr insProdAttrNew=new InsProdAttr();
    	insProdAttrNew.setRelatInstId(insProdAttr.getRelatInstId());
    	insProdAttrNew.setUserId(insProdAttr.getUserId());
    	insProdAttrNew.setActType(insProdAttr.getActType());
    	insProdAttrNew.setAttrText(insProdAttr.getAttrText());
        InsProdAttr record = (InsProdAttr) sqlMapDao.queryForObject("ins_prod_attr.selectByPrimaryKey", insProdAttrNew);
        return record;
    }

    public void updateByPrimaryKey(InsProdAttr record) {
      sqlMapDao.update("ins_prod_attr.updateByPrimaryKeySelective", record);
    }

	@Override
	public void insertInsProdAttrList(List<InsProdAttr> insProdAttrList) {
		    	sqlMapDao.insert("ins_prod_attr.insertProdInstAttrList", insProdAttrList);
	}

	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}

	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}
	
	
}