package com.asiainfo.integration.o2p.international.dao;

import java.util.List;

import com.ailk.eaap.op2.bo.LocaleRegConfig;
import com.ailk.eaap.op2.bo.LocaleSet;

public interface BaseDao {
	
	/**
	 * 
	 * getAllLocaleRegConfig:  获取所有的多元化注册表信息. <br/> 
	 * @author wushuzhen 
	 * @return 
	 * @since JDK 1.7
	 */
	public List<LocaleRegConfig>  getAllLocaleRegConfig();
	
	/**
	 * 
	 * getAllLocalSet: 获取所有的语言集信息 <br/> 
	 * @author wushuzhen 
	 * @return 
	 * @since JDK 1.7
	 */
	public List<LocaleSet> getAllLocalSet();
}
