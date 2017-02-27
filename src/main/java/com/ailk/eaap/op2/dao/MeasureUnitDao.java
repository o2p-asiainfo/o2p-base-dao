package com.ailk.eaap.op2.dao;

import com.ailk.eaap.op2.bo.Measure;
import com.ailk.eaap.op2.bo.MeasureUnitExchange;

public interface MeasureUnitDao {
	public static final Integer MEASURE_PRECISION_FLAG_0 = 0;
	public static final Integer MEASURE_PRECISION_FLAG_1 = 1;
	
	public static final Integer MEASURE_MIN_REALUNIT_0 = 0;
	public static final Integer MEASURE_MIN_REALUNIT_1 = 1;
	
	public static final Integer MEASURE_IS_DISPLAY_TRUE = 1;
	public static final Integer MEASURE_IS_DISPLAY_FLASE = 0;
	
	/**
	 * 基本资费                    precision_flag=1
	 * 固费/一次性费         MIN_REALUNIT=1
	 * @param measureTypeId   货币类型，区分不同币种
	 * @param precisionFlag   
	 * @param isDisplay       货币显示单位
	 * @param minRealunit
	 * @return
	 */
	public Measure getMeasure(String measureTypeId,Integer precisionFlag,Integer isDisplay,Integer minRealunit,Integer tenantId);
	
	/**
	 * 获取单位转换对象 
	 * @param measureId 原始单位
	 * @param destMeasureId 目标单位
	 * @return
	 */
	public MeasureUnitExchange getMeasureUnitExchange(String measureId,String destMeasureId);
	
	/**
	 * 
	 * @param value 原始值
	 * @param measureId 原始单位
	 * @param destMeasureId 目标单位
	 * @return  转换后的数值
	 * 若没有原始单位与目标单位的转换关系，抛出异常
	 */
	public String getMeasureUnitExchangeValue(double value,String measureId,String destMeasureId);
	
	/**
	 * 根据应用配置的最小单位作为目标单位转换
	 * @param value 原始值
	 * @param measureId 原始单位
	 * @param unitType  单位类型
	 * @return
	 * 若没有原始单位与目标单位的转换关系，抛出异常
	 */
	public String getMeasureUnitExchangeValueByUnitType(double value,String measureId,String unitType);
	
	/**
	 * 判断数值是否可以根据单位转换，并返回转换值
	 * @param value 原始值
	 * @param measureId 原始单位
	 * @param destMeasureId 目标单位
	 * @return 整型
	 */
	public String isMeasureUnitExchange(String value,String measureId,String destMeasureId);
	
	
}
