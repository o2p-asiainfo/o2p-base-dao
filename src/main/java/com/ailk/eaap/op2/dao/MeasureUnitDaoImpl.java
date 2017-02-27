package com.ailk.eaap.op2.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.ailk.eaap.op2.bo.Measure;
import com.ailk.eaap.op2.bo.MeasureUnitExchange;
import com.asiainfo.foundation.common.ExceptionCommon;
import com.asiainfo.foundation.exception.BusinessException;
import com.linkage.rainbow.dao.SqlMapDAO;

public class MeasureUnitDaoImpl implements MeasureUnitDao {

	private SqlMapDAO sqlMapDao;
	
	public Measure getMeasure(String measureTypeId,Integer precisionFlag,Integer isDisplay,Integer minRealunit,Integer tenantId){
		Measure measure = new Measure();
		measure.setMeasureTypeId(measureTypeId);
		measure.setPrecisionFlag(precisionFlag);
		measure.setIsDisplay(isDisplay);
		measure.setMinRealunit(minRealunit);
		measure.setTenantId(tenantId);
		
		measure = (Measure) sqlMapDao.queryForObject("measureUnit.getMeasure", measure);
		if(null == measure){
			throw new BusinessException(ExceptionCommon.WebExceptionCode,
					measureTypeId + "is not empty!" , null);
		}
		
		return measure;
	}
	
	public MeasureUnitExchange getMeasureUnitExchange(String measureId,String destMeasureId){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("measureId", measureId);
		params.put("destMeasureId", destMeasureId);
		MeasureUnitExchange measureUnitExchange = (MeasureUnitExchange) this.sqlMapDao
				.queryForObject("measureUnit.getMeasureUnitExchange", params);
		
		if(null == measureUnitExchange){
			throw new BusinessException(ExceptionCommon.WebExceptionCode,measureId + "==>" + destMeasureId + "is not exist!" , null);
		}
		return measureUnitExchange;
	}
	
	public String getMeasureUnitExchangeValue(double value,String measureId,String destMeasureId){
		if(null == measureId || destMeasureId == null){
			throw new BusinessException(ExceptionCommon.WebExceptionCode,measureId + "==>" + destMeasureId + "is not empty!" , null);
		}
		
		if(measureId.equals(destMeasureId)){
			return String.valueOf(value);
		}
		
		BigDecimal valueBigDecimal = new BigDecimal(value);
		
		MeasureUnitExchange measureUnit = this.getMeasureUnitExchange(measureId, destMeasureId);
		
		return this.changeNumber(valueBigDecimal.multiply(this.div(measureUnit.getExchangeNumerator(), 
				measureUnit.getExchangeDenominator(), 8)));
	}
	
	public String getMeasureUnitExchangeValueByUnitType(double value,String measureId,String unitType){
		return null;
	}
	
	public String isMeasureUnitExchange(String value,String measureId,String destMeasureId){
		String retValue = this.getMeasureUnitExchangeValue(Double.valueOf(value), measureId, destMeasureId);
		return retValue;
//		try{
//			return Integer.parseInt(retValue);
//		}catch (Exception e) {
//			throw new BusinessException(ExceptionCommon.WebExceptionCode,"the value is not exchange!" , null);
//		}
	}
	
	/**
	* 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
	* 定精度，以后的数字四舍五入。
	* @param v1 被除数
	* @param v2 除数
	* @param scale 表示表示需要精确到小数点以后几位。
	* @return 两个参数的商
	*/
	private BigDecimal div(Integer v1, Integer v2, int scale) {
	if (scale < 0) {
		throw new BusinessException(ExceptionCommon.WebExceptionCode,"The scale must be a positive integer or zero", null);
	}
	BigDecimal b1 = new BigDecimal(Double.toString(v1));
	BigDecimal b2 = new BigDecimal(Double.toString(v2));
	return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
	}
	
	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}
	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}
	
	public static void main(String[] args) {
//		BigDecimal b = value.multiply(div1(1, 1000000, 8));
		
	}
	
	private static BigDecimal div1(Integer v1, Integer v2, int scale) {
		if (scale < 0) {
			throw new BusinessException(ExceptionCommon.WebExceptionCode,"The scale must be a positive integer or zero", null);
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		System.out.println(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue());
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 省去数字后面的0
	 * @param num
	 * num=24.50000000   return 24.5
	 * num=0.00000004    return 0.00000004
	 * @return
	 */
	private String changeNumber(BigDecimal num){
		return BigDecimal.valueOf(Double.parseDouble(String.valueOf(num)))  
        .stripTrailingZeros().toPlainString();  
	}
	
	
}
