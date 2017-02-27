/** 
 * Project Name:o2p-base-dao 
 * File Name:SettleRuleOrgRelDao.java 
 * Package Name:com.ailk.eaap.op2.settleRuleOrgRel.dao 
 * Date:2016年4月14日下午9:24:17 
 * Copyright (c) 2016, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.ailk.eaap.op2.settleRuleOrgRel.dao;  

import com.ailk.eaap.op2.bo.SettleRuleOrgRel;


/** 
 * ClassName:SettleRuleOrgRelDao <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2016年4月14日 下午9:24:17 <br/> 
 * @author   wushuzhen 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public interface SettleRuleOrgRelDao {
	   public void insert(SettleRuleOrgRel settleRuleOrgRel);
	   public  SettleRuleOrgRel querySettleRuleOrgRelInfo(SettleRuleOrgRel settleRuleOrgRel);
	   public  void updateSettleRuleOrgRelInfo(SettleRuleOrgRel settleRuleOrgRel);
	   public void deleteSettleRuleOrgRelInfo(SettleRuleOrgRel settleRuleOrgRel);
}
