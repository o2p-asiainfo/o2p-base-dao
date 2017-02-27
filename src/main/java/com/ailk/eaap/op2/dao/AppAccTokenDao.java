package com.ailk.eaap.op2.dao;

import com.ailk.eaap.op2.bo.AppAccToken;

public interface AppAccTokenDao {

	public void addAppAccTokenDao(AppAccToken aat);
	public AppAccToken disableAppAccToken(String productNbr,int appId);
	
	
}
