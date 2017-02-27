package com.ailk.eaap.op2.dao;

import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.bo.ServiceSpec;
import com.ailk.eaap.op2.bo.ServiceSpecAttr;
import com.ailk.eaap.op2.bo.ServiceSpecAttrValue;

public interface ServiceSpecDao {

	public String getServiceSpecSeq();
	public String getServiceSpecAttrRelSeq();
	public String getselectSeqServiceSpeceValueRelSeq();
	
	public void addServiceSpec(ServiceSpec serviceSpec);
	public void updateServiceSpec(ServiceSpec serviceSpec);
	public List<ServiceSpec> qryServiceSpeclist(ServiceSpec serviceSpec);
	public List<ServiceSpec> serviceSpecificationCodeCheck(ServiceSpec serviceSpec);
	public ServiceSpec qryServiceSpeclistById(ServiceSpec serviceSpec);
	public String cntServiceSpeclist(ServiceSpec serviceSpec);
	public List<ServiceSpec> selectServiceSpecByPer(ServiceSpec serviceSpec);
	public void delServiceSpec(String serviceId);
	
	public void addServiceSpecAttr(ServiceSpecAttr serviceSpecAttr);
	public void updateServiceSpecAttr(ServiceSpecAttr serviceSpecAttr);
	public void delServiceSpecAttr(ServiceSpecAttr serviceSpecAttr);
	public List<Map<String,Object>> qryServiceSpecAttr(Map<String,Object> params);
	
	public void addServiceSpecAttrValue(ServiceSpecAttrValue serviceSpecAttrValue);
	public void delServiceSpecAttrValue(ServiceSpecAttrValue serviceSpecAttrValue);
	public void delServiceSpecByServId(String servId);
	public List<ServiceSpecAttrValue> selectServiceSpecAttrValue(ServiceSpecAttrValue serviceSpecAttrValue);
}
