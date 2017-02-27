package com.ailk.eaap.op2.common.dao;

import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.bo.BizFunction;

public interface IGetConfigDao {

	public List<BizFunction> getBusCode(String code);
	public Map<String,Object> loadDataSourceRoute();
}
