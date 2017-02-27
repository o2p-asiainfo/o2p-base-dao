package com.ailk.eaap.op2.dao;

import java.util.List;

import com.ailk.eaap.op2.bo.InterObjectAudit;
/**
 * 业务对象审核操作
 * @author zhuangjl
 *
 */
public interface InterObjectAuditDao {
	public String addInterObjectAudit(InterObjectAudit interObjectAudit);
	public Integer updateInterObjectAudit(InterObjectAudit interObjectAudit);
	public List<InterObjectAudit> qryInterObjectAudit(InterObjectAudit interObjectAudit);
}
