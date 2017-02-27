package com.asiainfo.integration.o2p.international.bmo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ailk.eaap.o2p.common.cache.CacheKey;
import com.ailk.eaap.op2.bo.LocaleRegConfig;
import com.ailk.eaap.op2.bo.LocaleRegConfigEx;
import com.ailk.eaap.op2.bo.LocaleSet;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.integration.o2p.international.dao.impl.BaseDaoImpl;
import com.asiainfo.integration.o2p.international.bmo.IResultObjHandler;
import com.linkage.rainbow.util.StringUtil;

public class ObjectLocaleServiceImpl implements IObjectLocaleService{

	private final static Logger logger = Logger.getLog(ObjectLocaleServiceImpl.class);
	private BaseDaoImpl baseDaoImpl;
	private MemcachedClient memcachedClient ;
	
	private boolean isInit = false;
	
	public void initialize(){
		if(!isInit){
			this.loadLocaleCfg();
			isInit = true;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object getObjectLocalCfg(String tableName,int rowKey,String language, String country,IResultObjHandler callBack,Object srcObject) {
		if(isInit){
			String objectId=tableName+"_"+rowKey;
			String localeInfo=language+"_"+country;
			String jsonData = null;
			String columnNames = null;
			try {
				columnNames=memcachedClient.get(CacheKey.LocaleRegConfig+tableName);
				if(null==columnNames){
					return null;	
				}
				jsonData = memcachedClient.get(CacheKey.LocaleSet+objectId+"_"+localeInfo);
			} catch (TimeoutException e) {
				logger.error(e.getMessage());
			} catch (InterruptedException e) {
				logger.error(e.getMessage());
			} catch (MemcachedException e) {
				logger.error(e.getMessage());
			}
			
			//注册多语言化的列名为key,解析对应的字符串
			if(!StringUtil.isEmpty(jsonData)){
				return callBack.handler(srcObject,parseJSON2Map(jsonData,columnNames));
			} 
		}
		  return null;
	}


	public  Map<String, Object> parseJSON2Map(String jsonStr,String columnNames){
        Map<String, Object> map = new HashMap<String, Object>();
        //最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        Set<Map.Entry<String, Object>> entry = json.entrySet();
        for(Map.Entry<String, Object> kv : entry){
        	//判断获取的key值是否为注册的国际化的列
        	if(columnNames.contains(kv.getKey().toString().trim())){
        		 Object v = json.get(kv.getKey()); 
                 //如果内层还是数组的话，继续解析
                 if(v instanceof JSONArray){
                     List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
                     Iterator<JSONObject> it = ((JSONArray)v).iterator();
                     while(it.hasNext()){
                         JSONObject json2 = it.next();
                         list.add(parseJSON2Map(json2.toString(),columnNames));
                     }
                     map.put(kv.getKey().toString().trim().toUpperCase(), list);
                 } else {
                     map.put(kv.getKey().toString().trim().toUpperCase(), v);
                 }
        	}           
        }
        return map;     
    }
	
	/**
	 * 
	 * 装载信息至缓存. 
	 *
	 */
	@SuppressWarnings("unchecked")
	public void loadLocaleCfg(){
		Map<String, String> mapTemp=new HashMap<String, String>();
		List<LocaleRegConfig> list=baseDaoImpl.getAllLocaleRegConfig();//得到所有的多语言注册表信息
		if(list!=null &&list.size()>0){
			for(LocaleRegConfig lrc : list){
				if(lrc.getTableName()!=null && ! "".equals(lrc.getTableName())){
					  List<LocaleRegConfigEx> localeRegConfigExs=	lrc.getLocaleRegConfigExs();
					  StringBuffer mapValue = new StringBuffer("");
					   for(LocaleRegConfigEx listEx:localeRegConfigExs){						
							mapValue.append(",").append(listEx.getColumnName());//注册多语言国际化的表中涉及多个列时，进行拼接
					   }
						mapTemp.put((String) lrc.getTableName(), mapValue.substring(mapValue.indexOf(",")+1));			
				}
			 				
			}
		}
		
		Set<Map.Entry<String, String>> entry = mapTemp.entrySet();
		for(Map.Entry<String, String> kv : entry){
			try {
				 String key = kv.getKey();
				 if(key!=null && mapTemp.get(key)!=null){
					 memcachedClient.set(CacheKey.LocaleRegConfig+key, 0, mapTemp.get(key));//存取的value值为列名，多个时逗号隔开
				 }
			} catch (TimeoutException e) {
				logger.error(e.getMessage());
			} catch (InterruptedException e) {
				logger.error(e.getMessage());
			} catch (MemcachedException e) {
				logger.error(e.getMessage());
			}
		}

		
		List<LocaleSet> allls=baseDaoImpl.getAllLocalSet();
		if(allls!=null &&allls.size()>0){
			for(LocaleSet ls : allls){
				try {
					String keyValue=CacheKey.LocaleSet+ls.getObjectId()+"_"+ls.getLanguage()+"_"+ls.getCountry();
					String localeValue = ls.getLocaleValue();
					if(keyValue!=null && localeValue!=null){
						memcachedClient.set(keyValue,0,localeValue);
					}
				} catch (TimeoutException e) {
					logger.error(e.getMessage());
				} catch (InterruptedException e) {
					logger.error(e.getMessage());
				} catch (MemcachedException e) {
					logger.error(e.getMessage());
				}
			}
		}
	}
	
	public void setBaseDaoImpl(BaseDaoImpl baseDaoImpl) {
		this.baseDaoImpl = baseDaoImpl;
	} 
	
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

}
