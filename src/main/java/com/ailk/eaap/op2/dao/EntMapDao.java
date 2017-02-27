package com.ailk.eaap.op2.dao;

import java.util.List;

import com.ailk.eaap.op2.bo.EntMap;
import com.asiainfo.foundation.exception.BusinessException;

public interface EntMapDao {

	public String getEntMapSeq();
	public EntMap qryEntMapById(String entMapId);
	public List<EntMap> qryEntMap(EntMap entMap);
	public void addEntMap(EntMap entMap);
	public void addEntMap(String entId,String srcEntId,String srcEntType,String entType,String srcCode,Integer tenantId);
	public void updateEntMap(EntMap entMap);
	public void updateEntMap(String entId,String srcEntId,String srcEntType,String entType,String srcCode,Integer tenantId);
	public void delEntMap(String entMapId);
	public void delEntMapByIdAndType(String srcEntId,String srcEntType,String statusCd,String srcCode,Integer tenantId);
	
	public EntMap qeyEntMap(String srcEntId,String srcEntType,String entType,String srcCode,Integer tenantId) throws BusinessException;
	
	public String qeyEntIdByMap(String srcEntId,String srcEntType,String entType,String srcCode,Integer tenantId);
	
	public String qeyEntIdByMap(String srcEntId,String srcEntType,String entType,String srcCode,String statusCd,Integer tenantId);
	
	public String qeySrcEntIdByMap(String entId,String entType,String srcCode,String statusCd,Integer tenantId);
	
}
