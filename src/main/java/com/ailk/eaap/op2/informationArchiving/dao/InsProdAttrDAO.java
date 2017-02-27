package com.ailk.eaap.op2.informationArchiving.dao;

import java.util.List;

import com.ailk.eaap.op2.bo.informationArchiving.InsProdAttr;

public interface InsProdAttrDAO {
    public void  deleteByPrimaryKey(Long priceAttrInstId);
    public void insert(InsProdAttr record);
    public InsProdAttr selectByPrimaryKey(InsProdAttr insProdAttr);
    public void updateByPrimaryKey(InsProdAttr record);
    public void insertInsProdAttrList(List<InsProdAttr> insProdAttrList);
    
}