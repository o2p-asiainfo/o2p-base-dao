package com.asiainfo.integration.o2p.basedao.util;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ailk.eaap.o2p.common.cache.CacheKey;
import com.linkage.rainbow.util.context.ContextUtil;

public final class DataSourceRouteUtil {
	
	private DataSourceRouteUtil(){}
	/**
	 * 取得数据源缓存列表 
	 * @return  List<Map>  主要字段包括DATA_SOURCE_NAME,COMPONENT_ID,TAB_SUFFIX
	 */
	public static List getDataSourceRouteList(){
		return (List)DataSourceRouteCache.getDataSourceRouteMap().get("dataSourceRouteList");
	}
	
	/**
	 * 设置线程变量中的数据源名称
	 * @param dsName
	 */
	public static void putContextDataSourceName(String dsName){
		ContextUtil.put(CacheKey.DepDataSourceContextName,dsName); //数据源名称
	}
	
	/**
	 * 取得线程变量中的数据源名称
	 * @param dsName
	 */
	public static String getContextDataSourceName(){
		return (String)ContextUtil.get(CacheKey.DepDataSourceContextName); //数据源名称
	}
	
	/**
	 * 设置线程变量中的内部交易流水
	 * 根据内部交易取得物理表名后缀，并设置到线程变量中。
	 * @param cii 内部交易流水
	 */
	public static void putContexCii(String cii){
		ContextUtil.put(CacheKey.CiiContextName,cii); //内部交易流水
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dsName = (String)ContextUtil.get(CacheKey.DepDataSourceContextName); //数据源名称
		//取得数据源信息Map,主要字段包括DATA_SOURCE_NAME,COMPONENT_ID,TAB_SUFFIX
		Map dataSourceMap = (Map)((Map)DataSourceRouteCache.getDataSourceRouteMap().get("dataSourceMap")).get(dsName);
		String tabSuffix = (String)dataSourceMap.get("TAB_SUFFIX");
		
		String tabSuffixInst = "";
		if(tabSuffix==null){
			tabSuffixInst = getCyc(sdf.format(new Date()), "_YYMM");
		}else{
			if(cii.length()==28){
				String strCyc = tabSuffix.substring(tabSuffix.lastIndexOf((char)'_'));
				tabSuffixInst =  getCyc(cii.substring(10,18), strCyc);
			}else {
				tabSuffixInst = getCyc(sdf.format(new Date()), "_YYMM");
			}
		}
		
		ContextUtil.put(CacheKey.DepTabSuffixContextName,tabSuffixInst); //物理表名后缀		
		
	}
	
	/**
	 * 设置线程变量中的内部交易流水
	 * 根据日志的创建时间取得物理表名后缀，并设置到线程变量中。
	 * @param createTime 日志创建时间戳
	 */
	public static void putContexTableSubffix(Timestamp createTime){
		//ContextUtil.put(CacheKey.CiiContextName,cii); //内部交易流水
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dsName = (String)ContextUtil.get(CacheKey.DepDataSourceContextName); //数据源名称
		//取得数据源信息Map,主要字段包括DATA_SOURCE_NAME,COMPONENT_ID,TAB_SUFFIX
		Map dataSourceMap = (Map)((Map)DataSourceRouteCache.getDataSourceRouteMap().get("dataSourceMap")).get(dsName);
		String tabSuffix = (String)dataSourceMap.get("TAB_SUFFIX");
		
		String tabSuffixInst = "";
		if(tabSuffix==null){
			tabSuffixInst = getCyc(sdf.format(createTime), "_YYMM");
		}else{
			tabSuffixInst =  getCyc(sdf.format(createTime), tabSuffix);
		}
		
		ContextUtil.put(CacheKey.DepTabSuffixContextName,tabSuffixInst); //物理表名后缀		
		
	}
	
	/**
	 * 判断物理表名后缀中的年，月，季，周
	 * 当前支持的包括：
	 * _YYYY 年
	 * _YYMM  年月
	 * _YYMMW 年月周，周是每个月前七天为第一周。
	 * _YYQ 年季
	 * _YYBB 年双月,表示两个月一个分区
	 * @param result
	 * @param strDate
	 * @param ff
	 */
	private static void replace(StringBuffer result,String strDate,String ff){
		if(ff.equalsIgnoreCase("YYYY")){
			result.append(strDate.substring(0,4));
		}else if(ff.equalsIgnoreCase("YY")){
			result.append(strDate.substring(2,4));
		}else if(ff.equalsIgnoreCase("MM")){
			result.append(strDate.substring(4,6));
		}else if(ff.equalsIgnoreCase("W")){
			int iDate = Integer.parseInt(strDate.substring(6,8));
			result.append((iDate-1)/7+1);
		}else if(ff.equalsIgnoreCase("Q")){
			int iMon = Integer.parseInt(strDate.substring(4,6));
			result.append((iMon-1)/3+1);
		}else if(ff.equalsIgnoreCase("BB")){
			int iMon = Integer.parseInt(strDate.substring(4,6));
			result.append((iMon-1)/2+1);
		}else{
			result.append(ff);
		}
	}
	
	/**
	 * 根据日期，格式取得结果
	 * @param strDate YYYYMMDD日期格式串
	 * @param format 返回格式，如YYMM,YYMMW,YYQ,YYBM
	 * @return
	 */
	public static String getCyc(String strDate,String format){
		StringBuffer result = new StringBuffer();
		if(format != null){
			int iLength = format.length();
			if(iLength>0){			
				char preChar = format.charAt(0);
				int iPreIndex=0;
				for(int i=1;i<iLength;i++){
					char c = format.charAt(i);
					if(c!=preChar){
						String ff =  format.substring(iPreIndex,i);
						replace(result,strDate,ff);
						preChar = c;
						iPreIndex = i;
					}
				}
				String ff =  format.substring(iPreIndex,iLength);
				replace(result,strDate,ff);
			}
		}
//		
//		String result= "";
//		Integer iYear = Integer.parseInt(strDate.substring(0,4));
//		String strMon = strDate.substring(4,6);
//		int iMon = Integer.parseInt(strMon);
//		Integer iDate = Integer.parseInt(strDate.substring(6,8));
//		result = format.replaceAll("YYYY", iYear.toString());
//		result = result.replaceAll("YY", iYear.toString().substring(2, 4));
//		result = result.replaceAll("MM", strMon);
//		result = result.replaceAll("W",new Integer((iDate-1)/7+1).toString());
//		result = result.replaceAll("Q",new Integer((iMon-1)/4+1).toString());
		return result.toString();
	}
	
	/**
	 * 取得线程变量中的物理表名后缀
	 * @return
	 */
	public static String getContextTabSuffix(){
		return (String)ContextUtil.get(CacheKey.DepTabSuffixContextName); //数据源名称
	}
	
	/**
	 * 设置线程变量中的物理表名后缀
	 * @param tabSuffix
	 */
	public static void putContextTabSuffix(String tabSuffix){
		ContextUtil.put(CacheKey.DepTabSuffixContextName,tabSuffix); //数据源名称
	}

	/**
	 * 取得数据源中的组件编码，用于生成内部交易流水时使用。
	 * @return
	 */
	public static String getComponentId(){
		String dsName = (String)ContextUtil.get(CacheKey.DepDataSourceContextName); //数据源名称
		Map dataSourceMap = (Map)((Map)DataSourceRouteCache.getDataSourceRouteMap().get("dataSourceMap")).get(dsName);
		return (String)dataSourceMap.get("COMPONENT_ID");
	}
	
	/**
	 * 替换SQL中的物理表名后缀。
	 * 例SQL编写是为：insert into tabname${DepTabSuffix},
	 * 替换后为：insert into tabname_1101,
	 * @param sql
	 * @return
	 */
	public static String replaceSqlTabSuffix(String sql){
		//		取得物理表名后缀。
		String tabSuffix = DataSourceRouteUtil.getContextTabSuffix();
		//将SQL中的物理表名后缀替换
		if(tabSuffix != null){
			return sql.replaceAll(CacheKey.DepTabSuffixSQLPattern,tabSuffix);
		}else{
			return sql;
		}
	}
}