package com.ailk.eaap.op2.dao;

import com.ailk.eaap.op2.bo.ProductServiceRel;
import com.linkage.rainbow.dao.SqlMapDAO;

public class ProductServiceDAOImpl implements ProductServiceDAO{

	private SqlMapDAO sqlMapDao;
	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}
	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}
	public void insertProductService(ProductServiceRel productServiceRel){
		sqlMapDao.insert("productServiceRel.insertProductService", productServiceRel);
	}
	public Integer updateProductService(ProductServiceRel productServiceRel){
		return sqlMapDao.update("productServiceRel.updateProductService", productServiceRel);
	}
	public ProductServiceRel queryProductService(ProductServiceRel productServiceRel){
		return (ProductServiceRel) sqlMapDao.queryForObject("productServiceRel.queryProductService", productServiceRel);
	}
	public void delProductService(String productId){
		sqlMapDao.delete("productServiceRel.delProductService", productId);
	}
}