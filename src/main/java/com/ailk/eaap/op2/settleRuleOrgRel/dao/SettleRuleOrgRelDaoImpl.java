/** 
 * Project Name:o2p-base-dao 
 * File Name:SettleRuleOrgRelDaoImpl.java 
 * Package Name:com.ailk.eaap.op2.settleRuleOrgRel.dao 
 * Date:2016年4月14日下午9:24:30 
 * Copyright (c) 2016, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.ailk.eaap.op2.settleRuleOrgRel.dao;  

import com.ailk.eaap.op2.bo.SettleRuleOrgRel;
import com.linkage.rainbow.dao.SqlMapDAO;

/** 
 * ClassName:SettleRuleOrgRelDaoImpl <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2016年4月14日 下午9:24:30 <br/> 
 * @author   wushuzhen 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class SettleRuleOrgRelDaoImpl implements SettleRuleOrgRelDao{
	private SqlMapDAO sqlMapDao;

    public SettleRuleOrgRelDaoImpl() {
        super();
    }
	@Override
	public void insert(SettleRuleOrgRel settleRuleOrgRel) {
		// TODO Auto-generated method stub
		if(settleRuleOrgRel.getOrgId()!=null&&settleRuleOrgRel.getOrgId()>0){
			sqlMapDao.insert("settleRuleOrgRel.insertSelective", settleRuleOrgRel);
		}
	}

	@Override
	public SettleRuleOrgRel querySettleRuleOrgRelInfo(
			SettleRuleOrgRel settleRuleOrgRel) {
		// TODO Auto-generated method stub
		return (SettleRuleOrgRel) sqlMapDao.queryForObject("settleRuleOrgRel.querySettleRuleOrgRelInfo", settleRuleOrgRel);
	}

	@Override
	public void updateSettleRuleOrgRelInfo(SettleRuleOrgRel settleRuleOrgRel) {
		// TODO Auto-generated method stub
		if(settleRuleOrgRel.getOrgId()!=null&&settleRuleOrgRel.getOrgId()>0){
			sqlMapDao.update("settleRuleOrgRel.updateSettleRuleOrgRelInfo", settleRuleOrgRel);
		}
	}

	@Override
	public void deleteSettleRuleOrgRelInfo(SettleRuleOrgRel settleRuleOrgRel) {
		// TODO Auto-generated method stub
		sqlMapDao.delete("settleRuleOrgRel.deleteSettleRuleOrgRelInfo", settleRuleOrgRel);
	}

	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}
	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}
}
