package com.asiainfo.integration.o2p.basedao.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ailk.eaap.o2p.security.SecurityCert;
import com.ailk.eaap.o2p.security.SecurityKey;
import com.ailk.eaap.o2p.security.SecurityKeyStore;
import com.ailk.eaap.o2p.security.SecurityType;
import com.ailk.eaap.o2p.security.SecurityVariable;
import com.asiainfo.integration.o2p.basedao.dao.ISecurityDBDao;

public class SecurityDBDaoImpl implements ISecurityDBDao {
	private SqlMapClientTemplate sqlMapClientTemplate;

	@Override
	public SecurityType getSecurityTypeByCode(String code) {
		SecurityType securityType = (SecurityType) sqlMapClientTemplate
				.queryForObject("securityService.getSecurityTypeByCode", code);
		return fillSecuritType(securityType);
	}

	@Override
	public SecurityType getSecurityType(int typeId) {
		SecurityType securityType = (SecurityType) sqlMapClientTemplate
				.queryForObject("securityService.getSecurityType", typeId);
		return fillSecuritType(securityType);
	}

	@SuppressWarnings("unchecked")
	private SecurityType fillSecuritType(SecurityType securityType) {
		if (securityType == null){
			return null;
		}
		List<SecurityKey> keys = getSecurityKeyByTypeId(securityType.getId());
		List<SecurityCert> certs = (List<SecurityCert>)sqlMapClientTemplate
				.queryForList("securityService.getSecurityCertByTypeId",
						securityType.getId());
		List<SecurityKeyStore> keyStores = (List<SecurityKeyStore>)sqlMapClientTemplate.queryForList("securityService.getSecurityKeyStoreByTypeId", securityType.getId());
		if (keys != null && keys.size() > 0){
			if ("Y".equalsIgnoreCase(securityType.getIsSymmety())) {
				securityType.getKeys().put(SecurityVariable.secretKey,
						keys.get(0));
			} else {
				for (int i = 0; i < keys.size(); i++) {
					if (SecurityVariable.pubKey.equals(keys.get(0).getPubPri())){
						securityType.getKeys().put(SecurityVariable.pubKey,keys.get(i));
					}
					else if (SecurityVariable.priKey.equals(keys.get(0).getPubPri())){
						securityType.getKeys().put(SecurityVariable.priKey,keys.get(i));
					}
				}
			}
		}
		if (certs != null && certs.size() > 0){
			securityType.setCert(certs.get(0));
		}
		if(keyStores != null && keyStores.size() > 0){
			securityType.setKeyStore(keyStores.get(0));
		}
		if (securityType.getUpSecurityType() != null){
			fillSecuritType(securityType.getUpSecurityType());
		}
		return securityType;
	}

	@Override
	public SecurityKey getSecurityKeyById(int id) {
		return (SecurityKey) sqlMapClientTemplate.queryForObject(
				"securityService.getSecurityKeyById", id);
	}

	@SuppressWarnings("unchecked")
	public List<SecurityKey> getSecurityKeyByTypeId(int typeId) {
		List<SecurityKey> keys = (List<SecurityKey>) sqlMapClientTemplate
				.queryForList("securityService.getSecurityKeyByTypeId", typeId);
		return keys;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SecurityType> getSecurityTypes() {
		List<SecurityType> securityTypes = (List<SecurityType>) sqlMapClientTemplate
				.queryForList("securityService.getSecurityTypes", null);
		for (SecurityType type : securityTypes){
			fillSecuritType(type);
		}
		return securityTypes;
	}

	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(
			SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

}
