package com.ailk.eaap.op2.informationArchiving.dao;

import com.ailk.eaap.op2.bo.informationArchiving.InsOffer;

public interface InsOfferDAO {
   public void deleteByPrimaryKey(Long offerInstId);
   public void insert(InsOffer record);
   public  InsOffer selectByPrimaryKey(Long offerInstId);
    public void  updateByPrimaryKey(InsOffer record);
}