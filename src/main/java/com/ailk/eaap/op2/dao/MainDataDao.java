package com.ailk.eaap.op2.dao;
 
import java.util.HashMap;
import java.util.List;

import com.ailk.eaap.op2.bo.MainData;

 

public interface MainDataDao {
	
	public List<MainData> selectMainData(MainData mainData);
	public List<HashMap> selectMainDataList(HashMap queryMap);
	
}
