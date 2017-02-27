/** 
 * Project Name:o2p-log-service 
 * File Name:LogStatisticStoreMysqlService.java 
 * Package Name:com.ailk.eaap.op2.logserver.deal.service.mysql.impl 
 * Date:2015年1月7日下午3:25:34 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.basedao.dao.impl;  

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ailk.eaap.o2p.common.util.date.UTCTimeUtil;
import com.ailk.eaap.op2.bo.RegStatRecent;
import com.ailk.eaap.op2.bo.RegStatSec;
import com.ailk.eaap.op2.bo.UseStatCntRecent;
import com.ailk.eaap.op2.bo.UseStatCntSec;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.integration.o2p.basedao.dao.ILogStatisticDao;
import com.asiainfo.integration.o2p.basedao.util.DataSourceRouteUtil;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.linkage.rainbow.dao.impl.IBatisSqlMapDAOImpl;

/** 
 * ClassName:LogStatisticStoreMysqlService <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年1月7日 下午3:25:34 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class LogStatisticDaoImpl implements ILogStatisticDao{

	private IBatisSqlMapDAOImpl sqlMapDAOImpl;
	private static final Logger log = Logger.getLog(LogStatisticDaoImpl.class);
	
	public static <T> Map<String,List<T>>  spiltListByDataSource(List<T> list,String MethodName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		Map<String,List<T>> listMap = new HashMap<String,List<T>>();//集合数组
		Set<String> dataSourceKeySet = new HashSet<String>();
		for(T t:list){
			Method method = t.getClass().getMethod(MethodName);
			String key = (String)method.invoke(t);
			dataSourceKeySet.add(key);
		}
		
		for(String key:dataSourceKeySet){
			listMap.put(key, new ArrayList<T>());
		}
		String dsKey = "";
		for(T t:list){
			Method method = t.getClass().getMethod(MethodName);
			dsKey = (String)method.invoke(t);
			listMap.get(dsKey).add(t);
		}
		return listMap;
	}
	
	public synchronized void setDataSourceKey(String dsName){
		DataSourceRouteUtil.putContextDataSourceName(dsName);
	}
	
	
	
	@SuppressWarnings("unused")
	@Override
	public void batchInsertNormalRegSec(final List<RegStatSec> list) {
		final Map<String,List<RegStatSec>> ListMap = new HashMap<String,List<RegStatSec>>();//集合数组
		final Set<String> dataSourceKeySet = new HashSet<String>();
		for(RegStatSec regSec:list){//动态判断key的个数
			dataSourceKeySet.add(regSec.getDataSourceKey());
		}
		for(String key:dataSourceKeySet){//为每个key指定一个list
			ListMap.put(key, new ArrayList<RegStatSec>());
		}
		String dsKey = "";
		for(RegStatSec regSec:list){//为每个key对应的list放入元素
			dsKey = regSec.getDataSourceKey();
			ListMap.get(dsKey).add(regSec);
		}
		Iterator<String> it = dataSourceKeySet.iterator();
		while(it.hasNext()){
			String ds = it.next();
			setDataSourceKey(ds);
			final List<RegStatSec> lit = ListMap.get(ds);
			int insertResult = (Integer)sqlMapDAOImpl.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					for(RegStatSec r:list){
						SimpleDateFormat sFormat = new SimpleDateFormat("_yyMM");
						r.setTableId(sFormat.format(new Date()));
						r.setCountTime(UTCTimeUtil.getUTCTimestamp(new Date()));
						executor.insert("regStatSec.insertRegStaSec", r);
					}
					return executor.executeBatch();
				}
			});
			if(log.isDebugEnabled()){
				log.debug("submit {0} rows, batch insert RegStatSec {1} rows", list.size(), insertResult);
			}
		}	
		
	}

	@Override
	public void batchInsertNormalRegOther(final List<RegStatRecent> list) {
		final Map<String,List<RegStatRecent>> ListMap = new HashMap<String,List<RegStatRecent>>();//集合数组
		final Set<String> dataSourceKeySet = new HashSet<String>();
		for(RegStatRecent regOther:list){//动态判断key的个数
			dataSourceKeySet.add(regOther.getDataSourceKey());
		}
		for(String key:dataSourceKeySet){//为每个key指定一个list
			ListMap.put(key, new ArrayList<RegStatRecent>());
		}
		String dsKey = "";
		for(RegStatRecent regOther:list){//为每个key对应的list放入元素
			dsKey = regOther.getDataSourceKey();
			ListMap.get(dsKey).add(regOther);
		}
		Iterator<String> it = dataSourceKeySet.iterator();
		while(it.hasNext()){
			String ds = it.next();
			setDataSourceKey(ds);
			final List<RegStatRecent> lit = ListMap.get(ds);
			//返回0即未执行过插入		
			int insertResult = (Integer)sqlMapDAOImpl.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					for(RegStatRecent r:lit){
						r.setCountTime(UTCTimeUtil.getUTCTimestamp(new Date()));
						executor.insert("regStatRecent.insertRegStaRecent", r);
					}
					return executor.executeBatch();
				}
			});
			if(log.isDebugEnabled()){
				log.debug("submit {0} rows, batch insert RegStatRecent {1} rows", list.size(), insertResult);
			}
		}	
	}

	
	@Override
	public void batchInsertNormalUseSec(final List<UseStatCntSec> list) {
		
		final Map<String,List<UseStatCntSec>> ListMap = new HashMap<String,List<UseStatCntSec>>();//集合数组
		final Set<String> dataSourceKeySet = new HashSet<String>();
		for(UseStatCntSec useSec:list){//动态判断key的个数
			dataSourceKeySet.add(useSec.getDataSourceKey());
		}
		for(String key:dataSourceKeySet){//为每个key指定一个list
			ListMap.put(key, new ArrayList<UseStatCntSec>());
		}
		String dsKey = "";
		for(UseStatCntSec useSec:list){//为每个key对应的list放入元素
			dsKey = useSec.getDataSourceKey();
			ListMap.get(dsKey).add(useSec);
		}
		Iterator<String> it = dataSourceKeySet.iterator();
		while(it.hasNext()){
			String ds = it.next();
			setDataSourceKey(ds);
			int insertResult = (Integer)sqlMapDAOImpl.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					for(UseStatCntSec u:list){
						SimpleDateFormat sFormat = new SimpleDateFormat("_yyMM");
						u.setTableId(sFormat.format(new Date()));
						u.setCountTime(UTCTimeUtil.getUTCTimestamp(new Date()));
						executor.insert("useStatCntSec.insertUseStatCntSec", u);
					}
					return executor.executeBatch();
				}
			});
			if(log.isDebugEnabled()){
				log.debug("submit {0} rows, batch insert UseStatCntSec {1} rows", list.size(), insertResult);
			}
		}	
	}

	@Override
	public void batchInsertNormalUseOther(final List<UseStatCntRecent> list) {
		
		final Map<String,List<UseStatCntRecent>> ListMap = new HashMap<String,List<UseStatCntRecent>>();//集合数组
		final Set<String> dataSourceKeySet = new HashSet<String>();
		for(UseStatCntRecent useOther:list){//动态判断key的个数
			dataSourceKeySet.add(useOther.getDataSourceKey());
		}
		for(String key:dataSourceKeySet){//为每个key指定一个list
			ListMap.put(key, new ArrayList<UseStatCntRecent>());
		}
		String dsKey = "";
		for(UseStatCntRecent useOther:list){//为每个key对应的list放入元素
			dsKey = useOther.getDataSourceKey();
			ListMap.get(dsKey).add(useOther);
		}
		Iterator<String> it = dataSourceKeySet.iterator();
		while(it.hasNext()){
			String ds = it.next();
			setDataSourceKey(ds);
			final List<UseStatCntRecent> lit = ListMap.get(ds);
			int insertResult = (Integer)sqlMapDAOImpl.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
					public Object doInSqlMapClient(SqlMapExecutor executor)
							throws SQLException {
						executor.startBatch();
						for(UseStatCntRecent r:lit){
							r.setCountTime(UTCTimeUtil.getUTCTimestamp(new Date()));
							executor.insert("useStatCntRecent.insertUseStatCntRecent", r);
						}
						return executor.executeBatch();
					}
				});
				if(log.isDebugEnabled()){
					log.debug("submit {0} rows, batch insert UseStatCntRecent {1} rows", list.size(), insertResult);
				}
		}	
	}
	
	
	public  <T> boolean  hasExist(String statement,T t){
		boolean hasExist = true;
		int affectRow = this.sqlMapDAOImpl.update(statement, t);
		if(affectRow == 0){
			hasExist = false;
		}
		return hasExist;
		
	}

	public IBatisSqlMapDAOImpl getSqlMapDAOImpl() {
		return sqlMapDAOImpl;
	}

	public void setSqlMapDAOImpl(IBatisSqlMapDAOImpl sqlMapDAOImpl) {
		this.sqlMapDAOImpl = sqlMapDAOImpl;
	}

	
}
