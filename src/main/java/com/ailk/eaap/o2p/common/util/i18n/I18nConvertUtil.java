package com.ailk.eaap.o2p.common.util.i18n;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;

import com.ailk.eaap.op2.bo.i18n.I18nParamObj;
import com.ailk.eaap.op2.bo.i18n.ProvideI18NData;
import com.asiainfo.foundation.common.ExceptionCommon;
import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.integration.o2p.international.bmo.ObjectLocaleServiceImpl;
import com.linkage.rainbow.util.StringUtil;

/** 
 * @ClassName: I18NConvertUtil 
 * @Description: 国际化转化的工具类，主要提供给O2P-Conf和O2P-Portal工程的 I18nAspectForSpring这个拦截器使用
 * @author zhengpeng
 * @date 2014-11-3 下午4:37:07 
 * @version V1.0  
 */
public final class I18nConvertUtil {
	
	private static final Logger log = Logger.getLog(I18nConvertUtil.class); 
	
	private I18nConvertUtil(){}
	
	/**
	 * 验证ProvideI18NData数据的完整性
	 * @param tableName
	 * @param columnNames
	 * @param idName
	 * @return
	 */
	public static boolean checkProvideI18NData(ProvideI18NData i18nData){
		if(StringUtil.isEmpty(i18nData.tableName())){
			return false;
		}
		if(StringUtil.isEmpty(i18nData.columnNames())){
			return false;
		}
		if(StringUtil.isEmpty(i18nData.idName())){
			return false;
		}
		return true;
	}
	
	/**
	 * 获取需要国际化的属性
	 * @param columnNameStr
	 * @param propertyNameStr
	 * @return
	 */
	public static String[] getPropertyNames(String columnNameStr,String propertyNameStr){
		if(!StringUtil.isEmpty(propertyNameStr)){
			return  propertyNameStr.split(",");
		}else{
			return  columnNameStr.split(",");
		}
	}
	
	/**
	 * 获取需要国际化的列和信息
	 * @param i18nData
	 * @param paramMap
	 */
	public static void setParamMap(ProvideI18NData i18nData,Map<String,I18nParamObj> paramMap){
		String propertyNameStr = i18nData.propertyNames();
		String columnNameStr = i18nData.columnNames();
		if(I18nConvertUtil.checkProvideI18NData(i18nData)){
			try{
				String[] columnNames = columnNameStr.split(",");
				String[] propertyNames = I18nConvertUtil.getPropertyNames(columnNameStr, propertyNameStr);
				for (int i = 0; i < propertyNames.length; i++) {
					I18nParamObj paramObj = new I18nParamObj();
					paramObj.setTableName(i18nData.tableName());
					paramObj.setColumnName(columnNames[i].toUpperCase()); 
					paramObj.setPropertyName(propertyNames[i]);
					paramObj.setIdName(i18nData.idName()); 
					paramMap.put(propertyNames[i].toUpperCase(), paramObj); 
				}
			}catch (Exception e) {
				log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,"I18NAspectForSpring setParamMap:" + e.getMessage(),e));
			}
		}
	}
	
	/**
	 * 获取类取定的方法
	 * @param clazz
	 * @param name
	 * @return
	 */
	public static Method findMethod(Class clazz, String name) {
		 Class searchType = clazz;
		 Method[] methods = (searchType.isInterface() ? searchType.getMethods() : searchType.getDeclaredMethods());
		 for (int i = 0; i < methods.length; i++){
			 Method method = methods[i];
			 if (name.equals(method.getName())){
				 return method; 
			 }
		 }
		 return null;
	}
	
	/**
	 * 判断DAO是否是获取总数的
	 * @param paramObj
	 * @return
	 */
	public static boolean isQryAllNum(Object[] args){
		boolean isQryAllNum =  false;
		if(args != null && args.length > 0){
			if(args[0] instanceof HashMap){
				if("ALLNUM".equals(String.valueOf(((HashMap)args[0]).get("queryType")))){
					isQryAllNum = true;
				}
			}
		}
		return isQryAllNum;
	}
	
	/**
	 * 对结果为Map的获得国际化的值
	 * @param paramMap
	 * @param resultMap
	 */
	public static void getI18nValueByMap(Map<String,I18nParamObj> paramMap,
			Map<String,Object> resultMap,
			String language,String country,
			ObjectLocaleServiceImpl transform){
		
		Set<Map.Entry<String, Object>> entries = resultMap.entrySet();
		for(Map.Entry<String, Object> entry : entries){
			I18nParamObj i18nParamObj = paramMap.get(entry.getKey().toUpperCase()); 
			if(i18nParamObj != null){
				Object value = null;
				try{
					value = resultMap.get(i18nParamObj.getIdName().toUpperCase()); 
					if(value != null && !StringUtil.isEmpty(value.toString())){
						//获到表记录对应的主键值
						int objectId = Integer.valueOf(value.toString());
						//调用接口，获取到国际化值
						I18nMapResultHandler handler = new I18nMapResultHandler(paramMap,i18nParamObj.getPropertyName()); 
						transform.getObjectLocalCfg(i18nParamObj.getTableName(),objectId,language,country,handler,resultMap);
					}
				}catch(Exception e){
					log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,"I18NAspectForSpring getI18nValueByMap ObjectValue:" + value + " || message:"+ e.getMessage(),e));
				}
			}
		}
	}
	
	/**
	 * 对结果为Object的获取国际化的值
	 * @param paramMap
	 * @param resultObj
	 */
	public static void getI18nValueByObj(Map<String,I18nParamObj> paramMap,
			Object resultObj,String language,String country,
			ObjectLocaleServiceImpl transform){
		
		Class objectClass = resultObj.getClass();
		Field[] fields = objectClass.getDeclaredFields();
		for (Field field : fields){
			I18nParamObj i18nParamObj = paramMap.get(field.getName().toUpperCase());
			if(i18nParamObj != null){
				Object value = null;
				try {
					//获到表记录对应的主键值
					value = PropertyUtils.getSimpleProperty(resultObj, i18nParamObj.getIdName());
					if(!StringUtil.isEmpty(value.toString())){
						int objectId = Integer.valueOf(value.toString());
						//调用接口，获取到国际化值
						I18nBeanResultHandler handler = new I18nBeanResultHandler(paramMap,field.getName());  
						transform.getObjectLocalCfg(i18nParamObj.getTableName(),objectId,language,country,handler,resultObj); 
					}
				} catch (Exception e) {
					log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,"I18NAspectForSpring getI18nValueByObj ObjectValue:" + value + " || message:"+ e.getMessage(),e));
				}
			}
		}
	}

}
