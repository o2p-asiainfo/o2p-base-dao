package com.ailk.eaap.op2.dao;
 
import java.util.List;
 
import com.ailk.eaap.op2.bo.MainDataType;
 
 

public interface MainDataTypeDao {
	public List<MainDataType> selectMainDataType(MainDataType mainDataType);
}
