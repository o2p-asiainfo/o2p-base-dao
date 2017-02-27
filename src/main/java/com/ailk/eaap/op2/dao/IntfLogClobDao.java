package com.ailk.eaap.op2.dao;

import com.ailk.eaap.op2.bo.IntfClobLog;

/** 
 * @ClassName: IntfLogClobDao 
 * @Description: 
 * @author zhengpeng
 * @date 2014-12-1 下午3:56:55 
 * @version V1.0  
 */
public interface IntfLogClobDao {

    void deleteByPrimaryKey(Long olcId);


    void insertSelective(IntfClobLog record);


    IntfClobLog selectByPrimaryKey(Long olcId);


    int updateByPrimaryKeySelective(IntfClobLog record);
    
    
    IntfClobLog selectIntfLogClob(IntfClobLog intfClobLog);


}
