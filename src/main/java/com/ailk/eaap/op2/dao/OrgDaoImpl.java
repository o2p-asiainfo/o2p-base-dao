package com.ailk.eaap.op2.dao;
  

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.bo.Area;
import com.ailk.eaap.op2.bo.Component;
import com.ailk.eaap.op2.bo.OrgRole;
import com.ailk.eaap.op2.bo.Org;
import com.linkage.rainbow.dao.SqlMapDAO;
 
public class OrgDaoImpl   implements OrgDao {
 	 
	private SqlMapDAO sqlMapDao;
	public Integer addOrg(Org orgBean ,OrgRole orgRoleBean){
		Integer orgId = (Integer)sqlMapDao.insert("org.insertOrg", orgBean) ;
		orgRoleBean.setOrgId(orgId) ;
		orgRoleBean.setTenantId(orgBean.getTenantId());
		 sqlMapDao.insert("org_role.insertOrgRole", orgRoleBean) ;
		   return orgId ;
	}
	
	public Integer addOrgRole(OrgRole orgRoleBean){
		return (Integer)sqlMapDao.insert("org_role.insertOrgRole", orgRoleBean) ;
	}
	
	public List<Org> selectOrg(Org orgBean){
		return (ArrayList<Org>)sqlMapDao.queryForList("org.selectOrg", orgBean) ;
	}
	
	public Org queryOrg(Org orgBean){
		return (Org) sqlMapDao.queryForObject("org.selectOrg", orgBean);
	}
	
	public Map loginOrg(Org orgBean){
		return (HashMap)sqlMapDao.queryForObject("org.selectOrg", orgBean) ;
	}
	
	public List<OrgRole> selectOrgRole(OrgRole orgRoleBean){
		return (ArrayList<OrgRole>)sqlMapDao.queryForList("org_role.selectOrgRole", orgRoleBean) ;
	}
	/**
	 * 修改机构信息
	 * @param orgBean
	 * @return
	 */
	public Integer updateOrgInfo(Org orgBean){
		return (Integer)sqlMapDao.update("org.updateOrg", orgBean) ;
	}
	
	public List<Map> queryCityForReg(Org orgBean){
		return (List<Map>)sqlMapDao.queryForList("org.queryCityForReg", orgBean) ;
	    
	}
	
	public List<Map> queryProvinceForReg(Org orgBean){
		return (List<Map>)sqlMapDao.queryForList("org.queryProvinceForReg", orgBean) ;
	    
	}
	
	
    public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}

	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}

	public Org selectOrgOne(Org org) {
		return (Org) sqlMapDao.queryForObject("org.selectOrg", org);
	}

	public Component selectComponentOne(Component component) {
		return (Component) sqlMapDao.queryForObject("component.selectComponent",component);
	}
	
	public List<Area> loadCityAreaList(HashMap paraMap){
		return (List<Area>)sqlMapDao.queryForList("org.selectCity", paraMap);
	}
	
	public List<Org> needToModifyPasswordOrgInfo(Org orgBean){
		return (List<Org>)sqlMapDao.queryForList("org.needToModifyPasswordOrgInfo", orgBean);
	}
	
	@SuppressWarnings("unchecked")
	public List<Component> qryComponent(Component component){
		return sqlMapDao.queryForList("component.selectComponent", component);
	}
}
