package com.asiainfo.integration.o2p.basedao.util;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public final class DataSourceRouteCache implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -291612420355960646L;

	//数据源路由缓存,chenliang
	//Map内存的信息包括：
	//数据源列表：dataSourceList=> List<Map>  主要字段包括DATA_SOURCE_NAME,COMPONENT_ID,TAB_SUFFIX
	//数据源路出规则列表:dataSourceRouteList=> List<Map>  主要字段包括DECISION_RULE,DATA_SOURCE_NAME,COMPONENT_ID,TAB_SUFFIX,EXPR
	//根据数据源名称取得数据源信息：dataSourceMap=>Map<String,Map> Map主要字段包括DATA_SOURCE_NAME,COMPONENT_ID,TAB_SUFFIX
	private static Map<String, Object> dataSourceRouteMap = new ConcurrentHashMap<String, Object>();
	
	private static Map<String, Object> dataSourceUseAbleMap = new ConcurrentHashMap<String, Object>();

	private DataSourceRouteCache(){}
	
	public static Map<String, Object> getDataSourceRouteMap() {
		return dataSourceRouteMap;
	}

	public static void setDataSourceRouteMap(Map<String, Object> dataSourceRouteMap) {
		DataSourceRouteCache.dataSourceRouteMap = dataSourceRouteMap;
	}

	public static Map<String, Object> getDataSourceUseAbleMap() {
		return dataSourceUseAbleMap;
	}

	public static void setDataSourceUseAbleMap(
			Map<String, Object> dataSourceUseAbleMap) {
		DataSourceRouteCache.dataSourceUseAbleMap = dataSourceUseAbleMap;
	}
	
}