package com.ailk.eaap.op2.informationArchiving.dao;

import com.ailk.eaap.op2.bo.informationArchiving.InsCustInfo;

public interface InsCustInfoDAO {
   public  void deleteByPrimaryKey(Long custId);
   public void insert(InsCustInfo record);
   public  InsCustInfo selectByPrimaryKey(Long custId);
   public  void updateByPrimaryKey(InsCustInfo record);
}