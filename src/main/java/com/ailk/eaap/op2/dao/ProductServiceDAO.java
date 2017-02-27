package com.ailk.eaap.op2.dao;

import com.ailk.eaap.op2.bo.ProductServiceRel;

public interface ProductServiceDAO {

	public void insertProductService(ProductServiceRel productServiceRel);
	public Integer updateProductService(ProductServiceRel productServiceRel);
	public ProductServiceRel queryProductService(ProductServiceRel productServiceRel);
	public void delProductService(String productId);

}