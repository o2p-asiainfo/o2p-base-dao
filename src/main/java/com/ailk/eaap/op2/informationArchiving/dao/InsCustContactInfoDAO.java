package com.ailk.eaap.op2.informationArchiving.dao;

import com.ailk.eaap.op2.bo.informationArchiving.InsCustContactInfo;

public interface InsCustContactInfoDAO {
   public  void deleteByPrimaryKey(Long custId);
   public void insert(InsCustContactInfo record);
   public InsCustContactInfo selectByPrimaryKey(Long custId);
   public void updateByPrimaryKey(InsCustContactInfo record);
}