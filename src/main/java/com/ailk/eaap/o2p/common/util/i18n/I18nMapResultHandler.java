package com.ailk.eaap.o2p.common.util.i18n;

import java.util.Map;

import com.ailk.eaap.op2.bo.i18n.I18nParamObj;
import com.asiainfo.integration.o2p.international.bmo.IResultObjHandler;
import com.linkage.rainbow.util.StringUtil;

/** 
 * @ClassName: I18nMapResultHandler 
 * @Description: 国际化接口的回调类，对于查询结果为Map类型进行处理
 * @author zhengpeng
 * @date 2014-11-6 下午8:46:54 
 * @version V1.0  
 */
public class I18nMapResultHandler implements IResultObjHandler<Map<String,Object>>{
	
	private Map<String,I18nParamObj> paramMap;
	private String propertyKey;
	
	public I18nMapResultHandler(Map<String,I18nParamObj> paramMap,String propertyKey){
		this.paramMap = paramMap;
		this.propertyKey = propertyKey;
	}
	
	/**
	 * 处理方法
	 */
	@Override
	public Map<String, Object> handler(Map<String, Object> srcObj,Map<String,String> localeValue) {
		I18nParamObj i18nParamObj = paramMap.get(propertyKey.toUpperCase());  
		String i18nValue = localeValue.get(i18nParamObj.getColumnName().toUpperCase());
		if(!StringUtil.isEmpty(i18nValue)){
			//将国际化值放入Map
			srcObj.put(propertyKey.toUpperCase(), i18nValue);
		}
		return srcObj;
	}

}
