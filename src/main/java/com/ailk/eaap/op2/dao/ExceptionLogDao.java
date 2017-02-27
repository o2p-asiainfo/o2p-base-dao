/** 
 * Project Name:o2p-workflow-pro 
 * File Name:ExceptionLogDao.java 
 * Package Name:com.ailk.o2p.workflow.dao 
 * Date:2016年1月26日上午10:46:06 
 * Copyright (c) 2016, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.ailk.eaap.op2.dao;  

import com.ailk.eaap.op2.bo.ExceptionLog;

/** 
 * ClassName:ExceptionLogDao <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2016年1月26日 上午10:46:06 <br/> 
 * @author   wushuzhen 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public interface ExceptionLogDao {
    void insertDataToTable(ExceptionLog exceptionLog);
    public ExceptionLog selectExceptionLogByActId(String activitiId,String objectId) ;
    public ExceptionLog selectExceptionLogByActId(String activitiId,String objectId,String exceptionType,String tenantId);
}
