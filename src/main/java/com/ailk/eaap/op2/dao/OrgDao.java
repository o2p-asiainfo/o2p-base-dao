package com.ailk.eaap.op2.dao;

import java.util.HashMap; 
import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.bo.Area;
import com.ailk.eaap.op2.bo.Component;
import com.ailk.eaap.op2.bo.Org;
import com.ailk.eaap.op2.bo.OrgRole;
 

public interface OrgDao {
	/**
	 * 用户注册操作
	 * @param statementName
	 * @param parameterObject
	 * @return
	 */
	public Integer addOrg(Org orgBean,OrgRole orgRoleBean);
	public Integer addOrgRole(OrgRole orgRoleBean);
	public List<Org> selectOrg(Org orgBean) ;
	public Org queryOrg(Org orgBean);
	public Map loginOrg(Org orgBean) ;
	public List<OrgRole> selectOrgRole(OrgRole orgRoleBean) ;
	public Integer updateOrgInfo(Org orgBean) ;
	public List<Map> queryProvinceForReg(Org orgBean);
	public List<Map> queryCityForReg(Org orgBean);
	public Org selectOrgOne(Org org);
	public Component selectComponentOne(Component component);
	public List<Area> loadCityAreaList(HashMap paraMap);
	public List<Org> needToModifyPasswordOrgInfo(Org orgBean);
	
	public List<Component> qryComponent(Component component);
}
