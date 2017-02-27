package com.ailk.eaap.op2.dao;
import java.util.ArrayList;  
import java.util.List; 
import com.ailk.eaap.op2.bo.MainDataType; 
import com.linkage.rainbow.dao.SqlMapDAO;
 
public class MainDataTypeDaoImpl   implements MainDataTypeDao {
	private SqlMapDAO sqlMapDao;
	
	@SuppressWarnings("unchecked")
	public List<MainDataType> selectMainDataType(MainDataType mainDataType){
		return (ArrayList<MainDataType>)sqlMapDao.queryForList("mainDataType.selectMainDataType", mainDataType) ;
	}
	
	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}
	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}
}
