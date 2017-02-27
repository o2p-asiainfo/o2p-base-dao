package com.ailk.eaap.op2.informationArchiving.dao;

import com.ailk.eaap.op2.bo.informationArchiving.InsUser;

public interface InsUserDAO {
   public  void deleteByPrimaryKey(Long userId);
   public  void insert(InsUser record);
   public  InsUser selectByPrimaryKey(Long userId);
   public  void updateByPrimaryKey(InsUser record);
}