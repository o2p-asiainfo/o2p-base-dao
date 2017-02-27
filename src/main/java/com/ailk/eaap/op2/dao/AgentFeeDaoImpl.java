package com.ailk.eaap.op2.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.common.EAAPException;
import com.ailk.eaap.op2.common.EAAPTags;

import com.linkage.rainbow.dao.SqlMapDAO;

@SuppressWarnings("unchecked")
public class AgentFeeDaoImpl implements AgentFeeDao{

	private SqlMapDAO sqlMapDao;

	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}

	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}
	
	public String getCustOrderId() {
		List<HashMap> retList = sqlMapDao.queryForList("eaap-op2-serviceAgent-agentFee.getCustOrderlId", null);
		return ((HashMap)retList.get(0)).get("CUST_ORDER_ID").toString();
	}
	
	public String getOrderItemId() {
		List<HashMap> retList = sqlMapDao.queryForList("eaap-op2-serviceAgent-agentFee.getOrderlItemId", null);
		return ((HashMap)retList.get(0)).get("ORDER_ITEM_ID").toString();
	}
	
	public void addCustomOrder(Map inMap){
		sqlMapDao.update("eaap-op2-serviceAgent-agentFee.addCustomOrder", inMap);
	}
	
	public void addOrderItem(Map inMap){
		sqlMapDao.update("eaap-op2-serviceAgent-agentFee.addOrderItem", inMap);
	}
	
	public void addOrderItemRel(Map inMap){
		sqlMapDao.update("eaap-op2-serviceAgent-agentFee.addOrderItemRel", inMap);
	}
	
	public void addAcctItem(Map inMap){
		sqlMapDao.update("eaap-op2-serviceAgent-agentFee.addAcctItem", inMap);
	}
	
	public Map queryOrderRealInfo(Map inMap) throws EAAPException{
		List<HashMap> retList = sqlMapDao.queryForList("eaap-op2-serviceAgent-agentFee.queryOrderRealInfo", inMap);
		if(retList == null || retList.size() == 0){
			throw new EAAPException( EAAPTags.SEG_DRAVER_SIGN,"9999","the order not found！");
		}
		return (HashMap)retList.get(0);
	}
	
	public void addProdInstOrder(Map inMap){
		sqlMapDao.update("eaap-op2-serviceAgent-agentFee.addProdInstOrder", inMap);
	}
	
	public String getAlipayTransactionID(){
		List<HashMap> retList = sqlMapDao.queryForList("eaap-op2-serviceAgent-agentFee.getTransactionID", null);
		return ((Map)retList.get(0)).get("TRANSACTION_ID").toString();
	}
	
	public void updateOrderItem(Map inMap) throws Exception{
		if(sqlMapDao.update("eaap-op2-serviceAgent-agentFee.updateOrderItem", inMap) != 1){
			throw new Exception("update orderItem exception");
		}
	}
	
	public Map getOrderInfo(Map inMap)throws Exception{
		List<HashMap> retList = sqlMapDao.queryForList("eaap-op2-serviceAgent-agentFee.getOrderInfo", inMap);
		if(retList == null || retList.size() == 0){
			throw new Exception("can not find order by tb_order_id");
		}
		return retList.get(0);
	}
	
	public Map getOrderStateNum(Map inMap)throws Exception{
		List<HashMap> retList = sqlMapDao.queryForList("eaap-op2-serviceAgent-agentFee.getOrderStateNum", inMap);
		return retList.get(0);
	}
	
	public void updateCustOrder(Map inMap) throws Exception{
		if(sqlMapDao.update("eaap-op2-serviceAgent-agentFee.updateCustOrder", inMap) != 1){
			throw new Exception("update custOrder exception");
		}
	}
}
