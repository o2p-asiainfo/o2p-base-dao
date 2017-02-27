package com.ailk.eaap.op2.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.bo.CharSpec;
import com.ailk.eaap.op2.bo.CharSpecCategory;
import com.ailk.eaap.op2.bo.CharSpecValue;
import com.linkage.rainbow.dao.SqlMapDAO;


public class CharSpecDaoImpl implements CharSpecDao{

	private SqlMapDAO sqlMapDao;
	
	public String getCharSpecSeq(){
		return (String) sqlMapDao.queryForObject("charSpec.getCharSpecSeq", null);
	}
	
	public String getCharSpecValueSeq(){
		return (String) sqlMapDao.queryForObject("charSpec.getCharSpecValueSeq", null);
	}
	
	public String getCharSpecCategorySeq(){
		return (String) sqlMapDao.queryForObject("charSpec.getCharSpecCategorySeq", null);
	}
	
	public CharSpec qryCharSpecById(Map paramMap){
	
		return (CharSpec) sqlMapDao.queryForObject("charSpec.qryCharSpecById", paramMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CharSpec> qryCharSpec(CharSpec charSpec) {
		return sqlMapDao.queryForList("charSpec.qryCharSpec", charSpec);
	}
	
	public String cntCharSpec(CharSpec charSpec){
		return (String) sqlMapDao.queryForObject("charSpec.cntCharSpec", charSpec);
	}
	
	public void insertCharSpec(CharSpec charSpec){
		sqlMapDao.insert("charSpec.insertCharSpec", charSpec);
	}
	
	public void updateCharSpec(CharSpec charSpec){
		sqlMapDao.update("charSpec.updateCharSpec", charSpec);
	}
	
	public void delCharSpec(Map paramMap){
		sqlMapDao.delete("charSpec.delCharSpec", paramMap);
	}
	
	public int isUD(Map paramMap){
		return (Integer) sqlMapDao.queryForObject("charSpec.isUD", paramMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getMappingIdList(Map<String, Object> map) {
		return sqlMapDao.queryForList("charSpec.getMappingIdList", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getPageInTypeIds(Map<String,Object> map){
		return sqlMapDao.queryForList("charSpec.getPageInTypeIds", map);
	}
	
	public Integer isExitAttrByCode(Map<String,String> params){
		return (Integer) sqlMapDao.queryForObject("charSpec.isExitAttrByCode", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<CharSpecValue> qryCharSpecValue(CharSpecValue charSpecValue){
		return sqlMapDao.queryForList("charSpec.qryCharSpecValue", charSpecValue);
	}
	
	public void insertCharSpecValue(CharSpecValue charSpecValue){
		sqlMapDao.insert("charSpec.insertCharSpecValue", charSpecValue);
	}
	
	public void updateCharSpecValue(CharSpecValue charSpecValue){
		sqlMapDao.update("charSpec.updateCharSpecValue", charSpecValue);
	}
	
	public void delCharSpecValue(Map paramMap){
		sqlMapDao.delete("charSpec.delCharSpecValue", paramMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CharSpecCategory> qryCharSpecCategory(CharSpecCategory charSpecCategory){
		return sqlMapDao.queryForList("charSpec.qryCharSpecValue", charSpecCategory);
	}
	
	public void insertCharSpecCategory(CharSpecCategory charSpecCategory){
		sqlMapDao.insert("charSpec.insertCharSpecCategory", charSpecCategory);
	}
	
	public void delCharSpecCategory(Map paramMap){
		sqlMapDao.delete("charSpec.delCharSpecCategory", paramMap);
	}
	
	////////////////////////////////////////////////////////////////
	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}
	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}

}
