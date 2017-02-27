package com.ailk.eaap.op2.dao;

import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.bo.ServiceSpec;
import com.ailk.eaap.op2.bo.ServiceSpecAttr;
import com.ailk.eaap.op2.bo.ServiceSpecAttrValue;
import com.linkage.rainbow.dao.SqlMapDAO;

public class ServiceSpecDaoImpl implements ServiceSpecDao{
	private SqlMapDAO sqlMapDao;
	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}
	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}
	
	public String getServiceSpecSeq(){
		return (String) sqlMapDao.queryForObject("serviceSpec.selectSeqServiceSpec",  null);
	}
	
	public String getServiceSpecAttrRelSeq(){
		return (String) sqlMapDao.queryForObject("serviceSpec.selectSeqServiceSpeceAttrRel",  null);
	}
	
	public String getselectSeqServiceSpeceValueRelSeq(){
		return (String) sqlMapDao.queryForObject("serviceSpec.selectSeqServiceSpeceValueRel",  null);
	}
	
	public void addServiceSpec(ServiceSpec serviceSpec){
		sqlMapDao.insert("serviceSpec.insertServiceSpec", serviceSpec);
	}
	
	public void updateServiceSpec(ServiceSpec serviceSpec){
		sqlMapDao.update("serviceSpec.updateServiceSpec", serviceSpec);
	}
	
	public ServiceSpec qryServiceSpeclistById(ServiceSpec serviceSpec){
		return (ServiceSpec) sqlMapDao.queryForObject("serviceSpec.selectServiceSpecById", serviceSpec);
	}
	
	public void delServiceSpec(String serviceId){
		sqlMapDao.delete("serviceSpec.delServiceSpec", serviceId);
	}
	
	@SuppressWarnings("unchecked")
	public List<ServiceSpec> qryServiceSpeclist(ServiceSpec serviceSpec){
		return sqlMapDao.queryForList("serviceSpec.selectServiceSpec", serviceSpec);
	}

	@SuppressWarnings("unchecked")
	public List<ServiceSpec> serviceSpecificationCodeCheck(ServiceSpec serviceSpec){
		return sqlMapDao.queryForList("serviceSpec.serviceSpecificationCodeCheck", serviceSpec);
	}
	
	@SuppressWarnings("unchecked")
	public List<ServiceSpec> selectServiceSpecByPer(ServiceSpec serviceSpec){
		return sqlMapDao.queryForList("serviceSpec.selectServiceSpecByPer", serviceSpec);
	}
	
	public String cntServiceSpeclist(ServiceSpec serviceSpec){
		return (String) sqlMapDao.queryForObject("serviceSpec.selectServiceSpecNum", serviceSpec);
	}
	
	public void addServiceSpecAttr(ServiceSpecAttr serviceSpecAttr){
		sqlMapDao.insert("serviceSpec.addServiceSpecAttr", serviceSpecAttr);
	}
	
	public void updateServiceSpecAttr(ServiceSpecAttr serviceSpecAttr){
		sqlMapDao.insert("serviceSpec.updateServiceSpecAttr", serviceSpecAttr);
	}
	
	public void delServiceSpecAttr(ServiceSpecAttr serviceSpecAttr){
		sqlMapDao.insert("serviceSpec.delServiceSpecAttr", serviceSpecAttr);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> qryServiceSpecAttr(Map<String,Object> params){
		return (List<Map<String, Object>>) sqlMapDao.queryForList("serviceSpec.selectServiceSpecAttr", params);
	}
	
	/**
	 * c.attr_spec_id ,
       c.attr_spec_code ,
       c.attr_spec_desc ,
       c.attr_spec_name ,
       c.page_in_type,
       c.IS_CUSTOMIZED ,
       sr.UPC_CHAR_SPEC_ID 
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> selAttrByUpcCharSpecId(String upcCharSpecId){
		return (List<Map<String, Object>>) sqlMapDao.queryForObject("serviceSpec.selAttrByUpcCharSpecId", upcCharSpecId);
	}
	
	public void mapServiceSpecAttrUpcCharSpecId(ServiceSpecAttr serviceSpecAttr){
		sqlMapDao.update("serviceSpec.mapServiceSpecAttrUpcCharSpecId", serviceSpecAttr);
	}
	
	public void addServiceSpecAttrValue(ServiceSpecAttrValue serviceSpecAttrValue){
		sqlMapDao.insert("serviceSpec.addServiceSpecAttrValue", serviceSpecAttrValue);
	}
	
	public void delServiceSpecByServId(String servId){
		sqlMapDao.delete("serviceSpec.delServiceSpecByServId", servId);
	}
	
	public void delServiceSpecAttrValue(ServiceSpecAttrValue serviceSpecAttrValue){
		sqlMapDao.delete("serviceSpec.delServiceSpecAttrValue", serviceSpecAttrValue);
	}
	
	@SuppressWarnings("unchecked")
	public List<ServiceSpecAttrValue> selectServiceSpecAttrValue(ServiceSpecAttrValue serviceSpecAttrValue){
		return sqlMapDao.queryForList("serviceSpec.selectServiceSpecAttrValue", serviceSpecAttrValue);
	}
}
