package com.ailk.eaap.op2.dao;


import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ailk.eaap.op2.bo.AppAccToken;
import com.ailk.eaap.op2.bo.UserInfo;

public class AppAccTokenDaoImpl implements AppAccTokenDao {

	private JdbcTemplate jdbcTemplate;
	private static final Logger log = Logger.getLogger(AppAccTokenDaoImpl.class);
	public AppAccToken findAppAccToken(int appId,String productNbr){
		String sql = "SELECT * FROM OP2_CONF.APP_ACC_TOKEN A,USER_INFO U "+
					 " WHERE A.USER_ID=U.USER_ID "+
					 " AND A.APP_ID=? AND U.PRODUCT_NBR=? AND DISABLE_TIME>SYSDATE AND ENABLE_TIME<SYSDATE";
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql,new Object[]{appId,productNbr});
		if(list!=null && list.size()>0){
			Map<String,Object> map = list.get(0);
			AppAccToken aat = new AppAccToken();
			aat.setUserId(Double.valueOf(map.get("USER_ID").toString()).intValue());
			aat.setRefToken(map.get("REF_TOKEN")==null?null:map.get("REF_TOKEN").toString());
			aat.setAccToken(map.get("ACC_TOKEN").toString());
			return aat;
		}
		return null;
	}

	public int findUserInfo(String productNbr){
		String sql = "SELECT USER_ID FROM OP2_CONF.USER_INFO U WHERE U.PRODUCT_NBR=?";
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql,new Object[]{productNbr});
		if(list!=null && list.size()>0){
			Map<String,Object> map = list.get(0);
			return Double.valueOf(map.get("USER_ID").toString()).intValue();
		}else{
			return 0;
		}
	}

	
	public void addAppAccTokenDao(AppAccToken aat) {
		// TODO Auto-generated method stub
		try{
			String userInfoId = null;
			if(aat.getUserInfo()!=null){
				userInfoId = addUserInfo(aat.getUserInfo())+"";
			}
			
			if(findAppAccToken(aat.getAppId(),aat.getUserInfo().getProductNbr())!=null){
				String sql = "UPDATE OP2_CONF.APP_ACC_TOKEN SET ACC_TOKEN=?,REF_TOKEN=?,OAUTH_API_LIST=?,DISABLE_TIME=SYSDATE+10 WHERE USER_ID=? AND APP_ID=?";
				jdbcTemplate.update(sql, new Object[]{aat.getAccToken(),aat.getRefToken(),aat.getOauthApiList(),userInfoId,aat.getAppId()});
			}
			else{
				String sql = "INSERT INTO OP2_CONF.APP_ACC_TOKEN "+
				" (APP_ACC_TOK_ID,"+
				"	APP_ID,"+
				" USER_ID,"+
				" ACC_TOKEN,"+
				" REF_TOKEN,"+
				" OAUTH_TIME,"+
				" OAUTH_API_LIST,"+
				" ENABLE_TIME,"+
				" DISABLE_TIME,"+
				" REF_ENABLE_TIME,"+
				" REF_DISABLE_TIME)"+
				" VALUES"+
				" (SEQ_APP_ACC_TOK.NEXTVAL,"+
				" ?,"+
				" ?,"+
				" ?,"+
				" ?,"+
				" SYSDATE,"+
				" ?,"+
				" SYSDATE,"+
				" SYSDATE+10,"+
				" SYSDATE,"+
				" SYSDATE)";

				jdbcTemplate.update(sql, new Object[]{aat.getAppId(),userInfoId,aat.getAccToken(),aat.getRefToken(),aat.getOauthApiList()});
			}
			
		}catch(Exception e){
			log.error("log apptoken error APP_ACC_TOKEN",e);
			
		}
		
	}
	
	public Integer addUserInfo(UserInfo userInfo){
		
		int count = findUserInfo(userInfo.getProductNbr());
		if(count>0){
			return count;
		}
		String getSeq = "SELECT SEQ_USER_INFO.NEXTVAL FROM DUAL";
		int userInfoId = jdbcTemplate.queryForInt(getSeq);
		String sql = "INSERT INTO USER_INFO(USER_ID,PRODUCT_NBR,USER_CUST_ID) VALUES(?,?,?)";
		jdbcTemplate.update(sql, new Object[]{userInfoId,userInfo.getProductNbr(),userInfo.getUserCustId()});
		return userInfoId;
	}
	
	
	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public AppAccToken disableAppAccToken(String productNbr, int appId) {
		// TODO Auto-generated method stub
		try{
			AppAccToken aat = findAppAccToken(appId,productNbr);
			if(aat!=null){
				String sql = "UPDATE OP2_CONF.APP_ACC_TOKEN SET DISABLE_TIME=SYSDATE WHERE USER_ID=? AND APP_ID=?";
				jdbcTemplate.update(sql, new Object[]{aat.getUserId(),appId});
			}
			return aat;
		}catch(Exception e){
			log.error(e.getStackTrace());
			return null;
		}
		
	}
	


}
