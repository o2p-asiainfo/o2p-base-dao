/** 
 * Project Name:o2p-workflow-pro 
 * File Name:ExceptionLogDaoImpl.java 
 * Package Name:com.ailk.o2p.workflow.dao 
 * Date:2016年1月26日上午10:46:54 
 * Copyright (c) 2016, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.ailk.eaap.op2.dao;  

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import com.ailk.eaap.op2.bo.ExceptionLog;
import com.linkage.rainbow.dao.SqlMapDAO;

/** 
 * ClassName:ExceptionLogDaoImpl <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2016年1月26日 上午10:46:54 <br/> 
 * @author   wushuzhen 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
@Repository
public class ExceptionLogDaoImpl implements ExceptionLogDao{
	@Resource(name="iBatisSqlMapDAO")
	private SqlMapDAO sqlMapDao;
	public static final Integer MAX_LENGTH_RESULT = 2048;
	
	public ExceptionLogDaoImpl() {
        super();
    }
	@Override
	public void insertDataToTable(ExceptionLog exceptionLog) {
		// TODO Auto-generated method stub
		if(exceptionLog.getResult().length()>MAX_LENGTH_RESULT.intValue()){
			exceptionLog.setResult(exceptionLog.getResult().substring(0, MAX_LENGTH_RESULT));
		}else{
			exceptionLog.setResult(exceptionLog.getResult());
		}
		sqlMapDao.insert("act-exception-log.insertSelective", exceptionLog);
	}

	
	@Override
	     public ExceptionLog selectExceptionLogByActId(String activitiId,String objectId) {
		// TODO Auto-generated method stub
		Map queryMap=new HashedMap();
		queryMap.put("activitiId", activitiId);
	    queryMap.put("objectId", objectId);
		List<ExceptionLog> exceptionLogList=sqlMapDao.queryForList("act-exception-log.selectExceptionLogByActId", queryMap);
		return exceptionLogList.get(0);
	}
	
	/**
	 * 
	 * @param busiCode 业务编码(流程实例)
	 * @param objectId 对象ID
	 * @param exceptionType 异常类型
	 * @param tenantId 租户
	 * @return 异常日志对象
	 */
	public ExceptionLog selectExceptionLogByActId(String busiCode,String objectId,String exceptionType,String tenantId) {
		Map<String,String> queryMap = new HashMap<String,String>();
		queryMap.put("activitiId", busiCode);
	    queryMap.put("objectId", objectId);
	    queryMap.put("exceptionType", exceptionType);
	    queryMap.put("tenantId", tenantId);
	    
	    return (ExceptionLog) sqlMapDao.queryForList("act-exception-log.selectExceptionLog", queryMap).get(0);
	}
	
	 public void setSqlMapDao(SqlMapDAO sqlMapDao) {
			this.sqlMapDao = sqlMapDao;
	 }
}
