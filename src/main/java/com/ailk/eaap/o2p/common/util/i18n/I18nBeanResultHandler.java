package com.ailk.eaap.o2p.common.util.i18n;

import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import com.ailk.eaap.op2.bo.i18n.I18nParamObj;
import com.asiainfo.foundation.common.ExceptionCommon;
import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.integration.o2p.international.bmo.IResultObjHandler;
import com.linkage.rainbow.util.StringUtil;

/** 
 * @ClassName: I18nBeanResultHandler 
 * @Description: 国际化接口的回调类，对于查询结果为JavaBean类型进行处理
 * @author zhengpeng
 * @date 2014-11-6 下午8:52:08 
 * @version V1.0  
 */
public class I18nBeanResultHandler implements IResultObjHandler<Object>{
	
	private static final Logger log = Logger.getLog(I18nBeanResultHandler.class);
	private Map<String,I18nParamObj> paramMap;
	private String propertyKey;
	
	public I18nBeanResultHandler(Map<String,I18nParamObj> paramMap,String propertyKey){
		this.paramMap = paramMap;
		this.propertyKey = propertyKey;
	}
	
	/**
	 * 处理方法
	 */
	@Override
	public Object handler(Object srcObj, Map<String,String> localeValue) {
		I18nParamObj i18nParamObj = paramMap.get(propertyKey.toUpperCase());  
		String i18nValue = localeValue.get(i18nParamObj.getColumnName().toUpperCase());
		if(!StringUtil.isEmpty(i18nValue)){
			//将国际化值放入对象
			try {
				PropertyUtils.setSimpleProperty(srcObj, propertyKey, i18nValue); 
			} catch (Exception e) {
				log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,"I18nBeanResultHandler handler:" + e.getMessage(),e));
			}
		}
		return srcObj;
	}

}
