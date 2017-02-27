package com.asiainfo.integration.o2p.international.bmo;


public interface IObjectLocaleService {
/**
 * 国际化的转换
 * @param tableName 注册国际化的表名
 * @param rowKey 表对应的行记录值
 * @param language 注册国际化的语言
 * @param country    注册国际化的国家
 * @param callBack 回调函数
 * @param srcObj 封装成源对象
 * @return
 */
	public Object getObjectLocalCfg(String tableName,int rowKey,String language, String country,IResultObjHandler callBack,Object srcObj);
	/**
	 * 装载数据到缓存
	 */
	public void loadLocaleCfg();
	
}
