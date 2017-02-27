package com.ailk.eaap.op2.dao;

import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.bo.CharSpec;
import com.ailk.eaap.op2.bo.CharSpecCategory;
import com.ailk.eaap.op2.bo.CharSpecValue;

public interface CharSpecDao {
	
	public String getCharSpecSeq();
	public String getCharSpecValueSeq();
	public String getCharSpecCategorySeq();
	
	public CharSpec qryCharSpecById(Map paramMap);
	public List<CharSpec> qryCharSpec(CharSpec charSpec);
	public String cntCharSpec(CharSpec charSpec);
	public void insertCharSpec(CharSpec charSpec);
	public void updateCharSpec(CharSpec charSpec);
	public void delCharSpec(Map paramMap);
	public int isUD(Map paramMap);
	public List<Map<String,Object>> getPageInTypeIds(Map<String,Object> map);
	public List<Integer> getMappingIdList(Map<String,Object> map);
	public Integer isExitAttrByCode(Map<String,String> params);
	
	public List<CharSpecValue> qryCharSpecValue(CharSpecValue charSpecValue);
	public void insertCharSpecValue(CharSpecValue charSpecValue);
	public void updateCharSpecValue(CharSpecValue charSpecValue);
	public void delCharSpecValue(Map paramMap);
	
	public List<CharSpecCategory> qryCharSpecCategory(CharSpecCategory charSpecCategory);
	public void insertCharSpecCategory(CharSpecCategory charSpecCategory);
	public void delCharSpecCategory(Map paramMap);
	
}
