package com.ailk.eaap.op2.informationArchiving.dao;

import com.ailk.eaap.op2.bo.informationArchiving.InsUserExt;

public interface InsUserExtDAO {
   public  void deleteByPrimaryKey(Long insUserExtId);
   public void insert(InsUserExt record);
   public  InsUserExt selectByPrimaryKey(Long insUserExtId);
   public void updateByPrimaryKey(InsUserExt record);
}