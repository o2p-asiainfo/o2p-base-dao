package com.asiainfo.integration.o2p.basedao.dao;

import java.util.List;

import com.ailk.eaap.op2.bo.NodeValReq;
import com.ailk.eaap.op2.bo.ParamVarMap;
import com.ailk.eaap.op2.bo.TransformerRule;


public interface ITransformerRuleDao{
	public TransformerRule getTransformerRuleById(Integer id);
	public List<TransformerRule> getAllUsable();
	public List<ParamVarMap> getParamVarMapByType();
	public NodeValReq queryNodeValReqByNodeId(Integer nodeDescId, Integer transformerRuleId);
}
