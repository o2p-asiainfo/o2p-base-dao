package com.asiainfo.integration.o2p.basedao.dao;

import java.util.List;

import com.ailk.eaap.o2p.security.SecurityKey;
import com.ailk.eaap.o2p.security.SecurityType;

public interface ISecurityDBDao {
	public SecurityType getSecurityTypeByCode(String code);
	public SecurityType getSecurityType(int typeId);
	public List<SecurityType> getSecurityTypes();
	SecurityKey getSecurityKeyById(int id);
	List<SecurityKey> getSecurityKeyByTypeId(int typeId);
}
