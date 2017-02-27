/** 
 * Project Name:o2p-log-service 
 * File Name:ILogStatisticStoreService.java 
 * Package Name:com.ailk.eaap.op2.logserver.deal.service 
 * Date:2015年1月7日下午3:29:09 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.basedao.dao;  

import java.util.List;

import com.ailk.eaap.op2.bo.RegStatRecent;
import com.ailk.eaap.op2.bo.RegStatSec;
import com.ailk.eaap.op2.bo.UseStatCntRecent;
import com.ailk.eaap.op2.bo.UseStatCntSec;

/** 
 * ClassName:ILogStatisticStoreService <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年1月7日 下午3:29:09 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public interface ILogStatisticDao {

	public void batchInsertNormalRegSec(List<RegStatSec> list);
	public void batchInsertNormalRegOther(List<RegStatRecent> list);
	public void batchInsertNormalUseSec(List<UseStatCntSec> list);
	public void batchInsertNormalUseOther(List<UseStatCntRecent> list);
}
