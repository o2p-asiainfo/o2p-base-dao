package com.ailk.eaap.op2.informationArchiving.dao;

import com.ailk.eaap.op2.bo.informationArchiving.InsProd;

public interface InsProdDAO {
    public void deleteByPrimaryKey(Long prodInstId);
    public void insert(InsProd record);
    public InsProd selectByPrimaryKey(InsProd record);
    public void updateByPrimaryKey(InsProd record);
}