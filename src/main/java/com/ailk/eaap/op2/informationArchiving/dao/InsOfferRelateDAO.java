package com.ailk.eaap.op2.informationArchiving.dao;

import com.ailk.eaap.op2.bo.informationArchiving.InsOfferRelate;

public interface InsOfferRelateDAO {
    public void deleteByPrimaryKey(Long relatId);
    public void insert(InsOfferRelate record);
    public InsOfferRelate selectByPrimaryKey(Long relatId);
    public void updateByPrimaryKey(InsOfferRelate record);
}