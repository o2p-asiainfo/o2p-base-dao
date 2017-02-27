package com.asiainfo.integration.o2p.basedao.dao.impl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ailk.eaap.op2.bo.NodeValReq;
import com.ailk.eaap.op2.bo.ParamVarMap;
import com.ailk.eaap.op2.bo.TransformerRule;
import com.asiainfo.integration.o2p.basedao.dao.ITransformerRuleDao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransformerRuleDaoImpl extends SqlMapClientDaoSupport implements ITransformerRuleDao, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public TransformerRule getTransformerRuleById(Integer id) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("contractAdapterId", id);
        return (TransformerRule) this.getSqlMapClientTemplate().queryForObject("transformerRule.selectTransformerRuleById", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransformerRule> getAllUsable() {
		return this.getSqlMapClientTemplate().queryForList("transformerRule.selectAllUseable");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParamVarMap> getParamVarMapByType() {
		return this.getSqlMapClientTemplate().queryForList("transformerRule.selectParamVarMap");
	}

	@Override
	public NodeValReq queryNodeValReqByNodeId(Integer nodeDescId, Integer transformerRuleId) {
		Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("nodeDescId", nodeDescId);
        map.put("transformerRuleId", transformerRuleId);
		return (NodeValReq)this.getSqlMapClientTemplate().queryForObject("transformerRule.selectNodeValReqByNodeId", map);
	}

}
