package com.ailk.eaap.op2.dao;

import java.util.Map;

import com.ailk.eaap.op2.common.EAAPException;

@SuppressWarnings("unchecked")
public interface AgentFeeDao {

	public String getAlipayTransactionID();
	
	public String getCustOrderId();
	
	public String getOrderItemId();
	
	public void addCustomOrder(Map inMap);
	
	public void addOrderItem(Map inMap);
	
	public void addOrderItemRel(Map inMap);
	
	public void addAcctItem(Map inMap);
	
	public Map queryOrderRealInfo(Map inMap) throws EAAPException;
	
	public void addProdInstOrder(Map inMap);
	
	public void updateOrderItem(Map inMap) throws Exception;
	
	public Map getOrderInfo(Map inMap)throws Exception;
	
	public Map getOrderStateNum(Map inMap)throws Exception;
	
	public void updateCustOrder(Map inMap) throws Exception;
}
