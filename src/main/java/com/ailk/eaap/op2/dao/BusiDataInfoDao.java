/** 
 * Project Name:o2p-base-dao 
 * File Name:BusiDataInfoDao.java 
 * Package Name:com.ailk.eaap.op2.dao 
 * Date:2016年3月13日下午5:17:18 
 * Copyright (c) 2016, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.ailk.eaap.op2.dao;  

import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.bo.BusiDataInfo;

/** 
 * ClassName:BusiDataInfoDao <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2016年3月13日 下午5:17:18 <br/> 
 * @author   wushuzhen 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public interface BusiDataInfoDao {
	  public void insertDataToTable(BusiDataInfo busiDataInfo);
	  public BusiDataInfo qryBusiDataInfoByName(Map paramMap) ;
	  public void updateBusiDataInfo(BusiDataInfo busiDataInfo) ;
	  public List<BusiDataInfo> loadBusiDataInfo(Map paramMap);
}
