package com.ailk.eaap.op2.dao;
  

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List; 

import com.ailk.eaap.op2.bo.MainData; 
import com.ailk.eaap.op2.bo.MainDataType;
import com.ailk.eaap.op2.bo.i18n.ProvideI18NData;
import com.ailk.eaap.op2.bo.i18n.ProvideI18NDatas;
import com.linkage.rainbow.dao.SqlMapDAO;
 
public class MainDataDaoImpl   implements MainDataDao {
	private SqlMapDAO sqlMapDao;
	
	@ProvideI18NDatas(values = { 
			@ProvideI18NData(tableName = "main_data", columnNames = "CEP_NAME", idName = "maindId", propertyNames = "CEPNAME") 
		}
	)
	public List<MainData> selectMainData(MainData mainData) {
		return (ArrayList<MainData>)sqlMapDao.queryForList("mainData.selectMainData", mainData) ;
	}
	
	@ProvideI18NDatas(values = { 
			@ProvideI18NData(tableName = "main_data", columnNames = "CEP_NAME", idName = "MAINDID", propertyNames = "CEPNAME") 
		}
	)
	public List<HashMap> selectMainDataList(HashMap queryMap){
		return (List<HashMap>) sqlMapDao.queryForList("mainData.selectMainDataList", queryMap);
	}
	
	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}
	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}
}
