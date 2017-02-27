package com.ailk.eaap.op2.dao;

import com.ailk.eaap.op2.bo.IntfLog;

public interface IntfLogDAO {

    void deleteByPrimaryKey(String contractInteractionId);

    void insertSelective(IntfLog record);

    IntfLog selectByPrimaryKey(String contractInteractionId);
    
    IntfLog selectIntfLog(IntfLog intfLog);

    int updateByPrimaryKeySelective(IntfLog record);
    
    public IntfLog selectIntfLogList(IntfLog intfLog);

}