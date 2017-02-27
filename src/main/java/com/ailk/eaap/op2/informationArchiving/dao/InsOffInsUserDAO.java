package com.ailk.eaap.op2.informationArchiving.dao;

import com.ailk.eaap.op2.bo.informationArchiving.InsOffInsUser;

public interface InsOffInsUserDAO {
   public  void  deleteByPrimaryKey(Long userId);
   public void insert(InsOffInsUser record);
   public InsOffInsUser selectByPrimaryKey(Long userId);
   public void updateByPrimaryKey(InsOffInsUser record);
}