package com.ailk.eaap.op2.dao;
import java.util.Map;
import java.util.List;

import com.ailk.eaap.op2.bo.FileShare;
 
 

public interface FileShareDao {
	/**
	 * 用户注册操作
	 * @param statementName
	 * @param parameterObject
	 * @return
	 */
	public Integer addFileShare(FileShare fileShareBean);
	public List<Map>  selectFileShare(FileShare fileShareBean) ;
}
