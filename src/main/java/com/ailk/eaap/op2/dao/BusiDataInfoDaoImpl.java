/** 
 * Project Name:o2p-base-dao 
 * File Name:BusiDataInfoDaoImpl.java 
 * Package Name:com.ailk.eaap.op2.dao 
 * Date:2016年3月13日下午5:17:32 
 * Copyright (c) 2016, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.ailk.eaap.op2.dao;  


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ailk.eaap.op2.bo.BusiDataInfo;
import com.linkage.rainbow.dao.SqlMapDAO;

/** 
 * ClassName:BusiDataInfoDaoImpl <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2016年3月13日 下午5:17:32 <br/> 
 * @author   wushuzhen 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
@Repository
public class BusiDataInfoDaoImpl implements BusiDataInfoDao{
	@Resource(name="iBatisSqlMapDAO")
	private SqlMapDAO sqlMapDao;
	
	
	@Override
	public void insertDataToTable(BusiDataInfo busiDataInfo) {
		// TODO Auto-generated method stub
		sqlMapDao.insert("busi-data-info.insertSelective", busiDataInfo);
	}

	@Override
	public BusiDataInfo qryBusiDataInfoByName(Map paramMap) {
		// TODO Auto-generated method stub
		return (BusiDataInfo)sqlMapDao.queryForObject("busi-data-info.qryBusiDataInfoByName", paramMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BusiDataInfo> loadBusiDataInfo(Map paramMap){
		 return sqlMapDao.queryForList("busi-data-info.loadBusiDataInfo", paramMap);
	 }

	@Override
	public void updateBusiDataInfo(BusiDataInfo busiDataInfo) {
		// TODO Auto-generated method stub
		sqlMapDao.update("busi-data-info.updateBusiDataInfo", busiDataInfo);
	}

	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}

	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}

	
}
