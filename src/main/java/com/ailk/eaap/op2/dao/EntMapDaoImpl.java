package com.ailk.eaap.op2.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.bo.EntMap;
import com.ailk.eaap.op2.common.EAAPConstants;
import com.asiainfo.foundation.common.ExceptionCommon;
import com.asiainfo.foundation.exception.BusinessException;
import com.linkage.rainbow.dao.SqlMapDAO;

public class EntMapDaoImpl implements EntMapDao{

	private SqlMapDAO sqlMapDao;
	
	public String getEntMapSeq(){
		return (String) sqlMapDao.queryForObject("entMap.getEntMapSeq", null);
	}
	
	public EntMap qryEntMapById(String entMapId){
		
		Map map = new HashMap();
		map.put("entMapId", entMapId);
		return (EntMap) sqlMapDao.queryForObject("entMap.qryEntMapById", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<EntMap> qryEntMap(EntMap entMap){
		return sqlMapDao.queryForList("entMap.qryEntMap", entMap);
	}
	
	public void addEntMap(EntMap entMap){
		sqlMapDao.insert("entMap.addEntMap", entMap);
	}
	
	public void addEntMap(String entId,String srcEntId,String srcEntType,String entType,String srcCode,Integer tenantId){
		EntMap entMap = new EntMap();
		entMap.setEntMapId(this.getEntMapSeq());
		entMap.setEntId(entId);
		entMap.setSrcEntId(srcEntId);
		entMap.setSrcEntType(srcEntType);
		entMap.setEntType(entType);
		entMap.setSrcCode(srcCode);
		entMap.setTenantId(tenantId);
		entMap.setStatusCd(EAAPConstants.COMM_STATE_ONLINE);
		
		sqlMapDao.insert("entMap.addEntMap", entMap);
	}
	
	public void updateEntMap(EntMap entMap){
		sqlMapDao.update("entMap.updateEntMap", entMap);
	}
	
	public void updateEntMap(String entId,String srcEntId,String srcEntType,String entType,String srcCode,Integer tenantId){
		EntMap entMap = new EntMap();
		entMap.setEntId(entId);
		entMap.setSrcEntId(srcEntId);
		entMap.setSrcEntType(srcEntType);
		entMap.setEntType(entType);
		entMap.setSrcCode(srcCode);
		entMap.setTenantId(tenantId);
		
		sqlMapDao.update("entMap.updateEntMap", entMap);
	}
	
	public void delEntMap(String entMapId){
		
		Map map = new HashMap();
		map.put("entMapId", entMapId);
		sqlMapDao.delete("entMap.delEntMap", map);
	}
	
	public void delEntMapByIdAndType(String srcEntId,String srcEntType,String statusCd,String srcCode,Integer tenantId){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("srcEntId", srcEntId);
		params.put("srcEntType", srcEntType);
		params.put("srcCode", srcCode);
		params.put("statusCd", statusCd);
		params.put("tenantId", tenantId);
		sqlMapDao.delete("entMap.delEntMapByIdAndType", params);
	}
	
	public EntMap qeyEntMap(String srcEntId,String srcEntType,String entType,String srcCode,Integer tenantId) throws BusinessException{
		EntMap entMap = new EntMap();
		entMap.setEntType(entType);
		entMap.setSrcEntId(srcEntId);
		entMap.setSrcEntType(srcEntType);
		entMap.setSrcCode(srcCode);
		entMap.setTenantId(tenantId);
		entMap.setStatusCd(EAAPConstants.COMM_STATE_ONLINE);
		List<EntMap> entMapList = this.qryEntMap(entMap);
		if(entMapList==null || entMapList.size()!=1){
			throw new BusinessException(ExceptionCommon.WebExceptionCode,"System error!--> not find srcEntId:"+srcEntId+
					"|srcEntType:"+srcEntType, null);
		}
		
		return entMapList.get(0);
	}
	
	public String qeySrcEntIdByMap(String entId,String entType,String srcCode,String statusCd,Integer tenantId){
		EntMap entMap = new EntMap();
		entMap.setEntType(entType);
		entMap.setEntId(entId);
		entMap.setSrcEntType(entType);
		entMap.setSrcCode(srcCode);
		entMap.setTenantId(tenantId);
		entMap.setStatusCd(statusCd);
		List<EntMap> entMapList = this.qryEntMap(entMap);
		if(entMapList != null && entMapList.size() > 0){
			entMap = entMapList.get(0);
		}
		return entMap.getSrcEntId();
	}
	
	public String qeyEntIdByMap(String srcEntId,String srcEntType,String entType,String srcCode,Integer tenantId){
		EntMap entMap = new EntMap();
		entMap.setEntType(entType);
		entMap.setSrcEntId(srcEntId);
		entMap.setSrcEntType(srcEntType);
		entMap.setSrcCode(srcCode);
		entMap.setTenantId(tenantId);
		entMap.setStatusCd(EAAPConstants.COMM_STATE_ONLINE);
		List<EntMap> entMapList = this.qryEntMap(entMap);
		if(entMapList != null && entMapList.size() > 0){
			entMap = entMapList.get(0);
		}
		return entMap.getEntId();
	}
	
	public String qeyEntIdByMap(String srcEntId,String srcEntType,String entType,String srcCode,String statusCd,Integer tenantId){
		EntMap entMap = new EntMap();
		entMap.setEntType(entType);
		entMap.setSrcEntId(srcEntId);
		entMap.setSrcEntType(srcEntType);
		entMap.setSrcCode(srcCode);
		entMap.setTenantId(tenantId);
		entMap.setStatusCd(statusCd); 
		List<EntMap> entMapList = this.qryEntMap(entMap);
		if(entMapList != null && entMapList.size() > 0){
			entMap = entMapList.get(0);
		}
		return entMap.getEntId();
	}
	
	////////////////////////////////////////////////////////////////
	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}
	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}
}
