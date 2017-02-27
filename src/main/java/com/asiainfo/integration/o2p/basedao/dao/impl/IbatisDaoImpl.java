package com.asiainfo.integration.o2p.basedao.dao.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import com.ailk.eaap.o2p.common.cache.CacheKey;
import com.ailk.eaap.o2p.common.util.Constant;
import com.ailk.eaap.o2p.fileExchange.model.DirBean;
import com.ailk.eaap.o2p.fileExchange.model.FileExchangeConstMap;
import com.ailk.eaap.o2p.fileExchange.model.FileExchangeContract2AttrSpec;
import com.ailk.eaap.o2p.fileExchange.model.FileExchangeContractAdapter;
import com.ailk.eaap.o2p.fileExchange.model.FileExchangeContractFormat;
import com.ailk.eaap.o2p.fileExchange.model.FileExchangeDynParamMap;
import com.ailk.eaap.o2p.fileExchange.model.FileExchangeEndpointSpecAttr;
import com.ailk.eaap.o2p.fileExchange.model.FileExchangeNodeDescMaper;
import com.ailk.eaap.o2p.fileExchange.model.FileExchangeNodeValAdapterReq;
import com.ailk.eaap.o2p.fileExchange.model.FileMoveRule;
import com.ailk.eaap.o2p.fileExchange.model.FileMoveSerInst;
import com.ailk.eaap.o2p.fileExchange.model.Host;
import com.ailk.eaap.o2p.fileExchange.model.RemoteAuth;
import com.ailk.eaap.op2.bo.Api;
import com.ailk.eaap.op2.bo.App;
import com.ailk.eaap.op2.bo.AppAccToken;
import com.ailk.eaap.op2.bo.AppApiList;
import com.ailk.eaap.op2.bo.AppUserInfo;
import com.ailk.eaap.op2.bo.AttrSpec;
import com.ailk.eaap.op2.bo.AttrValue;
import com.ailk.eaap.op2.bo.Auth;
import com.ailk.eaap.op2.bo.AuthObj;
import com.ailk.eaap.op2.bo.BizFunction;
import com.ailk.eaap.op2.bo.CacheObj;
import com.ailk.eaap.op2.bo.CacheStrategy;
import com.ailk.eaap.op2.bo.Component;
import com.ailk.eaap.op2.bo.ConfProperties;
import com.ailk.eaap.op2.bo.ContextCacheIns;
import com.ailk.eaap.op2.bo.ContextParam;
import com.ailk.eaap.op2.bo.ContextParamList;
import com.ailk.eaap.op2.bo.ContextType;
import com.ailk.eaap.op2.bo.Contract;
import com.ailk.eaap.op2.bo.ContractFormat;
import com.ailk.eaap.op2.bo.ContractNodeFuzzy;
import com.ailk.eaap.op2.bo.ContractVersion;
import com.ailk.eaap.op2.bo.CtlCounterms2Comp;
import com.ailk.eaap.op2.bo.DataType;
import com.ailk.eaap.op2.bo.DyScript;
import com.ailk.eaap.op2.bo.Endpoint;
import com.ailk.eaap.op2.bo.EndpointAttr;
import com.ailk.eaap.op2.bo.FuzzyEncryption;
import com.ailk.eaap.op2.bo.GetValueExpr;
import com.ailk.eaap.op2.bo.HideMethod;
import com.ailk.eaap.op2.bo.LogLevel;
import com.ailk.eaap.op2.bo.MainData;
import com.ailk.eaap.op2.bo.MessageFlow;
import com.ailk.eaap.op2.bo.NodeDesc;
import com.ailk.eaap.op2.bo.Org;
import com.ailk.eaap.op2.bo.PriDefine;
import com.ailk.eaap.op2.bo.ProofEffect;
import com.ailk.eaap.op2.bo.QuartzTaskContentBean;
import com.ailk.eaap.op2.bo.RegisterObject;
import com.ailk.eaap.op2.bo.RemoteCallInfo;
import com.ailk.eaap.op2.bo.RouteCondition;
import com.ailk.eaap.op2.bo.RouteEndpoint;
import com.ailk.eaap.op2.bo.RoutePolicy;
import com.ailk.eaap.op2.bo.SerInvokeIns;
import com.ailk.eaap.op2.bo.Service;
import com.ailk.eaap.op2.bo.ServiceRela;
import com.ailk.eaap.op2.bo.ServiceRouteConfig;
import com.ailk.eaap.op2.bo.TaskBase;
import com.ailk.eaap.op2.bo.TechImpContralPo;
import com.ailk.eaap.op2.bo.TechImpl;
import com.ailk.eaap.op2.bo.TechImplNode;
import com.ailk.eaap.op2.bo.Template;
import com.ailk.eaap.op2.bo.ToRouteEndpoint;
import com.ailk.eaap.op2.bo.UsableMonitorConf;
import com.ailk.eaap.op2.bo.UserInfo;
import com.ailk.eaap.op2.bo.WhiteList;
import com.ailk.eaap.op2.bo.XmlNameSpace;
import com.asiainfo.integration.o2p.basedao.dao.IIbatisDao;
import com.linkage.rainbow.dao.impl.IBatisSqlMapDAOImpl;
import com.linkage.rainbow.util.ExprUtil;
import com.linkage.rainbow.util.expr.core.Expr;

@SuppressWarnings("unchecked")
public class IbatisDaoImpl implements IIbatisDao {
	private static final Log log = LogFactory.getLog(IbatisDaoImpl.class);
	private IBatisSqlMapDAOImpl sqlMapDAOImpl;		
	private String tokenEnableTime;
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Api> getApi(Integer apiid) {
		List<Map<String,String>> resultMap;
		resultMap = sqlMapDAOImpl.queryForList("loadCommon.getApi", null);
		List<Api> resultList = new ArrayList<Api>();
		if(resultMap!=null){
			for (Map<String, String> map : resultMap) {
				Api api = new Api();
				api.setApiName(map.get("API_NAME"));
				api.setApiMethod(map.get("API_METHOD"));
				api.setContractVer(map.get("VERSION"));
				api.setApiId(ObjtoInt(map.get("API_ID")));
				api.setServiceId(ObjtoInt(map.get("SERVICE_ID")));
				api.setPriDefines(getPriDefine(ObjtoInt(map.get("API_ID"))));
				api.setServiceCode(map.get("SERVICE_CODE"));
				resultList.add(api);
			}
		}
		return resultList;
	}
	
	public List<String> getWhiteList(WhiteList whiteList) {
		List<Map<String,String>> resultMap = sqlMapDAOImpl.queryForList("loadCommon.getWhiteList", null);
		List<String> resultList = new ArrayList<String>();
		if(resultMap != null){
			for (Map<String, String> map : resultMap) {
				String macStr = map.get("MAC").toString();
				resultList.add(macStr);
			}
		}
		return resultList;
	}
	
	public List<PriDefine> getPriDefine(int apiId){
		List<Map<String,String>> resultMap = sqlMapDAOImpl.queryForList("loadCommon.getApiListBySApiID",apiId);
		if(resultMap!=null && resultMap.size()>0){
			List<PriDefine> priDefines = new ArrayList<PriDefine>();
			for (Map<String, String> map : resultMap) {
				PriDefine pdf = new PriDefine();
				pdf.setPriDefineDesc(map.get("PRI_DEFINE_DESC")==null?null:map.get("PRI_DEFINE_DESC").toString());
				pdf.setPriDefineType(map.get("PRI_DEFINE_TYPE")==null?null:map.get("PRI_DEFINE_TYPE").toString());
				pdf.setPriDefineName(map.get("PRI_DEFINE_NAME")==null?null:map.get("PRI_DEFINE_NAME").toString());
				pdf.setApiId(apiId);
				priDefines.add(pdf);
			}
			return priDefines;
		}
		return null;
	}

	public List<AppAccToken> getAppAccToken() {
		int tokencount = (Integer)sqlMapDAOImpl.queryForObject("loadCommon.selectAppAccTokenCount", null);
		for (int stri = 0; stri < tokencount; stri = stri+1000) {
			Map<String,Integer> inMap = new HashMap<String,Integer>();
			inMap.put("parm1", stri);
			inMap.put("parm2", stri + 1000);
			List<Map<String,String>> resultMap = sqlMapDAOImpl.queryForList("loadCommon.getAppAccTokenAndUserInfo",inMap);
			List<AppAccToken> resultList = new ArrayList<AppAccToken>();
			for (Map<String, String> map : resultMap) {
				AppAccToken appAccToken = new AppAccToken();
				appAccToken.setApp(this.getApp(ObjtoInt(map.get("APP_ID"))).get(0));
				appAccToken.setAccToken(map.get("ACC_TOKEN"));
				Object o = map.get("APP_ACC_TOK_ID");
				UserInfo user = new UserInfo();
				user.setProductNbr(map.get("PRODUCT_NBR")==null?null:map.get("PRODUCT_NBR").toString());
				appAccToken.setUserInfo(user);
				String s = o.toString();
				appAccToken.setAppAccTokId(Double.valueOf(s).intValue());
				appAccToken.setDisableTime(map.get("DISABLE_TIME"));
				String api_ids[] = map.get("OAUTH_API_LIST").toString().split(",");
				List<Api> apis = new ArrayList<Api>();
				for (int i = 0; i < api_ids.length; i++) { 
					List<Map<String,String>> apiMap = sqlMapDAOImpl.queryForList("loadCommon.queryApi",api_ids[i]);
					for (Map<String, String> map2 : apiMap) {
						Api api = new Api();
					    api.setApiId(ObjtoInt(map.get("API_ID")));
						api.setApiMethod(map2.get("API_METHOD").toString());
						api.setApiName(map2.get("API_NAME").toString());
						api.setServiceId(ObjtoInt(map2.get("SERVICE_ID")));
						apis.add(api);
					}
				}
				appAccToken.setApis(apis);
				resultList.add(appAccToken);
			}
		}
		return null;
	}
	
	private int ObjtoInt(Object obj){
		if(obj==null){
			return 0;
		}
		String routeidstr2 = obj.toString();
		return Double.valueOf(routeidstr2).intValue();
	}
	
	
	private String ObjtoString(Object obj){
		if(obj==null){
			return "";
		}
		return obj.toString();
	}

	public List<AppApiList> getAppList(int appId) {
		List<Map<String,Object>> tempMap = sqlMapDAOImpl.queryForList("loadCommon.getAppList",appId);
		
		if(tempMap != null){
			List<AppApiList> appApiLists = new ArrayList<AppApiList>();
			for (Map<String, Object> map : tempMap) {
				AppApiList aal = new AppApiList();
				String apiStr = map.get("API_ID").toString();
				aal.setApiId(Double.valueOf(apiStr).intValue());
				aal.setApiMethod(map.get("API_METHOD") == null ? "" : map.get("API_METHOD").toString());
				aal.setAppId(appId);
				appApiLists.add(aal);
			}
			return appApiLists;
		}
		return null;
	}
	
	public List<Auth> getAuth(Integer SerInvokeInsId) {
		List<Map<String,String>> resultList = sqlMapDAOImpl.queryForList("loadCommon.getAuth",SerInvokeInsId);
		if(resultList!=null && resultList.size()>0){
			List<Auth> auths = new ArrayList<Auth>();
			for (Map<String, String> map : resultList) {
				Auth auth = new Auth();
				auth.setAuthFormula(map.get("AUTH_FORMULA")==null?null:map.get("AUTH_FORMULA").toString());
				auth.setAuthId(ObjtoInt(map.get("AUTH_ID")));
				auth.setAuthObjOp(map.get("AUTH_OBJ_OP")==null?null:map.get("AUTH_OBJ_OP").toString());
				auth.setAuthObjType(map.get("AUTH_OBJ_TYPE")==null?null:map.get("AUTH_OBJ_TYPE").toString());
				auth.setReqOrRsp(map.get("REQ_OR_RSP")==null?null:map.get("REQ_OR_RSP").toString());
				auth.setState(map.get("STATE")==null?null:map.get("STATE").toString());
				if(map.get("AUTH_PATH")!=null){
					AuthObj authObj = new AuthObj();
					authObj.setAuthObjId(ObjtoInt(map.get("AUTH_OBJ_ID")));
					authObj.setAuthPath(map.get("AUTH_PATH").toString());
					auth.setAuthObj(authObj);
				}
				List<Map<String,String>> attlist = sqlMapDAOImpl.queryForList("loadCommon.findAuthAttr", auth.getAuthId());
				if(attlist!=null && attlist.size()>0){
					Map<String, Object> attrMap = new Hashtable<String, Object>();
					for (Map<String, String> resultMap : attlist) {
						attrMap.put(resultMap.get("ATTR_SPEC_CODE").toString(), resultMap.get("ATTR_VALUE").toString());
					}
					auth.setAttrMap(attrMap);
				}
				auths.add(auth);
			}
			return auths;
		}
		return null;
	}

	public Map<String, BizFunction> getBusCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Component> getComponent(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ContractVersion> getContractVer(String version) {
		List<Map<String,String>> resultMap = sqlMapDAOImpl.queryForList("loadCommon.getContractVer",null);
		List<ContractVersion> resultList = new ArrayList<ContractVersion>();
		if(resultMap!=null){
			for (Map<String, String> map : resultMap) {
				ContractVersion cv = new ContractVersion();
				cv.setState(map.get("STATE").toString());
				cv.setVersion(map.get("VERSION").toString());
				cv.setIsNeedCheck(map.get("IS_NEED_CHECK")==null?null:map.get("IS_NEED_CHECK").toString());
				Contract contract = new Contract();
				contract.setCode(map.get("CODE").toString());
				Object co = map.get("BASE_CONTRACT_ID");
				if(co!=null){
					String so = co.toString();
					contract.setBaseContractId(Double.valueOf(so).intValue());
				}
				cv.setContract(contract);
				cv.setContractVersionId(ObjtoInt(map.get("CONTRACT_VERSION_ID")));
				List<ContractFormat> contractFormats = getContractFormat(cv.getContractVersionId());
				for (ContractFormat contractFormat : contractFormats) {
					if(contractFormat.getReqRsp().equals("REQ")){
						cv.setReqContractFormat(contractFormat);
					}else{
						cv.setRspContractFormat(contractFormat);
					}
				}
				ContractVersion baseContractVersion = getContractFormatByContract(contract.getBaseContractId());
				cv.setBaseContractVersion(baseContractVersion);
				resultList.add(cv);
			}
		}
		return resultList;
	}
	
	public ContractVersion getContractFormatByContract(Integer contractid){
		List<Map<String,String>> resultMap = sqlMapDAOImpl.queryForList("loadCommon.getContractFormatByContract",contractid);
		if(resultMap!=null){
			ContractVersion cv = new ContractVersion();
			for (Map<String, String> map : resultMap) {
				Object o = map.get("CONTRACT_VERSION_ID");
				String s = o.toString();
				Integer contractVerId=Double.valueOf(s).intValue();
				List<ContractFormat> contractFormats = getContractFormat(contractVerId);
				String version = map.get("VERSION") == null ? null : map.get("VERSION").toString();
				cv.setVersion(version);
				for (ContractFormat contractFormat2 : contractFormats) {
					if(contractFormat2.getReqRsp().equals("REQ")){
						cv.setReqContractFormat(contractFormat2);
					}else{
						cv.setRspContractFormat(contractFormat2);
					}
				}
			}
			return cv;
		}
		return null;
	}
	
	public List<ContractFormat> getContractFormat(Integer contractVerId){
		List<Map<String,String>> formatresult = sqlMapDAOImpl.queryForList("loadCommon.getContractFormat",contractVerId);
		if(formatresult!=null){
			List<ContractFormat> contractFormats = new ArrayList<ContractFormat>();
			for (Map<String, String> formatMap: formatresult) {
				ContractFormat cf = new ContractFormat();
				cf.setConType(formatMap.get("CON_TYPE"));
				cf.setReqRsp(formatMap.get("REQ_RSP").toString());
				cf.setState(formatMap.get("STATE"));
				cf.setXsdDemo(getLongText(formatMap.get("XSD_DEMO")));
				cf.setXsdFormat(getLongText(formatMap.get("XSD_FORMAT")));
				cf.setXsdException(getLongText(formatMap.get("XSD_EXCEPTION")));
				cf.setXsdHeaderFor(getLongText(formatMap.get("XSD_HEADER_FOR")));
				Object tcpid = formatMap.get("TCP_CTR_F_ID");
				
				String tcpidStr = tcpid.toString();
				cf.setTcpCtrFId(Double.valueOf(tcpidStr).intValue());
			
				List<Map<String,Object>> nodeResult = sqlMapDAOImpl.queryForList("loadCommon.getNode",Integer.parseInt(tcpidStr));
				List<NodeDesc> nodeDescList = new ArrayList<NodeDesc>();
				for (Map<String, Object> nodeMap : nodeResult) {
					NodeDesc de = new NodeDesc();
					if (null != nodeMap.get("NODE_DESC_ID")) {
						de.setNodeDescId(Integer.valueOf(nodeMap.get("NODE_DESC_ID").toString()));
					}
					de.setIsNeedCheck(nodeMap.get("IS_NEED_CHECK")==null?null:nodeMap.get("IS_NEED_CHECK").toString());
					de.setNodeCode(nodeMap.get("NODE_CODE")==null?null:nodeMap.get("NODE_CODE").toString());
					de.setNodePath(nodeMap.get("NODE_PATH")==null?null:nodeMap.get("NODE_PATH").toString());
					if(nodeMap.get("NODE_LENGTH_CONS")!=null){
						de.setNodeLengthCons(nodeMap.get("NODE_LENGTH_CONS")==null?null:nodeMap.get("NODE_LENGTH_CONS").toString());
					}
					
					de.setNodeType(nodeMap.get("NODE_TYPE")==null?null:nodeMap.get("NODE_TYPE").toString());
					de.setNodeTypeCons(nodeMap.get("NODE_TYPE_CONS")==null?null:nodeMap.get("NODE_TYPE_CONS").toString());
					de.setNodeNumberCons(nodeMap.get("NODE_NUMBER_CONS")==null?null:nodeMap.get("NODE_NUMBER_CONS").toString());
					
					de.setNevlConsType(nodeMap.get("NEVL_CONS_TYPE")==null?null:nodeMap.get("NEVL_CONS_TYPE").toString());
					de.setNevlConsValue(nodeMap.get("NEVL_CONS_VALUE")==null?null:nodeMap.get("NEVL_CONS_VALUE").toString());
					de.setIsNeedSign(nodeMap.get("IS_NEED_SIGN")==null?null:nodeMap.get("IS_NEED_SIGN").toString());
					de.setJavaField(nodeMap.get("JAVA_FIELD")==null?null:nodeMap.get("JAVA_FIELD").toString());
					nodeDescList.add(de);
				}
				cf.setNodeDescs(nodeDescList);
				contractFormats.add(cf);
			}
			return contractFormats;
		}
		return null;
	}
	
	private  String getLongText(Object xsdFormatObj){
		String xmlDoc = "";
		InputStream is = null;
		if(xsdFormatObj instanceof byte[] ) {
			byte[] 	fileBytes = (byte[])xsdFormatObj;
			try {
				xmlDoc = new String(fileBytes, Constant.UTF8);
			} catch (UnsupportedEncodingException e) {
				log.error("get long text error!", e);
			}
		} else if (xsdFormatObj instanceof java.sql.Clob ) {
			java.sql.Clob xsdFormatClob = (java.sql.Clob)xsdFormatObj;
			byte[] data = null;
			try {
				int len = (int) xsdFormatClob.length();
				data = new byte[len];
				is = xsdFormatClob.getAsciiStream();
				if(is.read(data)==-1){
					throw new IOException();
				}
			} catch (SQLException e) {
				log.error("SQLException:"+e.getMessage()+",errorCode:"+e.getErrorCode());
			} catch (IOException e) {
				log.error("IOException:"+e.getMessage());
			} finally{
				if(is!=null){
					try {
						is.close();
					} catch (IOException e) {
						log.error("IOException:"+e.getMessage());
					}
				}
			}
			if(data != null) {
				try {
					xmlDoc = new String(data, Constant.UTF8);
				} catch (UnsupportedEncodingException e) {
					log.error("convert from byte array to string error!", e);
				}
			}
		} else if(xsdFormatObj instanceof String ) {
			xmlDoc = xsdFormatObj.toString();
		}
		return xmlDoc;
	}

	public List<CtlCounterms2Comp> getFlow() {
		List<Map<String,Object>> resultList = sqlMapDAOImpl.queryForList("loadCommon.getCTL_COUNTERMS_2_COMP",null);
		if(resultList!=null){
			List<CtlCounterms2Comp> flowList = new ArrayList<CtlCounterms2Comp>();
			for (Map<String, Object> map : resultList) {
				CtlCounterms2Comp flow = new CtlCounterms2Comp();
				flow.setSerInvokeInsId(ObjtoInt(map.get("SER_INVOKE_INS_ID")));
				flow.setCycleValue(ObjtoInt(map.get("CYCLE_VALUE")));
				flow.setCycleType(map.get("CYCLE_TYPE")==null?null:map.get("CYCLE_TYPE").toString());
				flow.setCutmsValue(ObjtoInt(map.get("CUTMS_VALUE")));
				flow.setCcCd(ObjtoInt(map.get("CC_CD")));
				String effectState = map.get("EFFECT_STATE")==null?"R":map.get("EFFECT_STATE").toString();
				flow.setEffectState(effectState);
				flowList.add(flow);
			}
			return flowList;
		}
		return null;
	}

	public Map<String, List<MainData>> getMainData() {
		try{
			Map<String,List<MainData>> resultMap = new Hashtable<String,List<MainData>>();
			List<Map<String,Object>> mainTypeMap = sqlMapDAOImpl.queryForList("loadCommon.getMainDataType",null);
			for (Map<String, Object> map : mainTypeMap) {
				String mdtSign = map.get("MDT_SIGN")==null?null:map.get("MDT_SIGN").toString();
				Integer mdtId = ObjtoInt(map.get("MDT_CD"));
				List<Map<String,Object>> mainMap  = sqlMapDAOImpl.queryForList("loadCommon.getMainDataForMDTypeID",mdtId);
				if(mainMap!=null && !mainMap.isEmpty()){
					List<MainData> mainDataList = new ArrayList<MainData>();
					for (Map<String, Object> mp : mainMap) {
						MainData mainData = new MainData();
						mainData.setMaindId(ObjtoInt(mp.get("MAIND_ID")));
						mainData.setCepValues(mp.get("CEP_VALUES")==null?null:mp.get("CEP_VALUES").toString());
						mainData.setCepCode(mp.get("CEP_CODE")==null?null:mp.get("CEP_CODE").toString());
						mainData.setCepDes(mp.get("CEP_DES")==null?null:mp.get("CEP_DES").toString());
						mainData.setCepName(mp.get("CEP_NAME")==null?null:mp.get("CEP_NAME").toString());
						if(null != mp.get("CREATE_TIME")){
							mainData.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").parse(mp.get("CREATE_TIME").toString()));
						}else{
							mainData.setCreateTime(null);
						}
						if(null != mp.get("LASTEST_TIME")){
							mainData.setLastestTime(new SimpleDateFormat("yyyy-MM-dd").parse(mp.get("LASTEST_TIME").toString()));
						}else{
							mainData.setLastestTime(null);
						}
						mainData.setMdtCd(mp.get("MDT_CD")==null?null:mp.get("MDT_CD").toString());
						mainData.setState(mp.get("STATE")==null?null:mp.get("STATE").toString());
						mainDataList.add(mainData);
					}
					if(mdtSign != null) {
						resultMap.put(CacheKey.MainData + mdtSign, mainDataList);
					}
				}
			}
			return resultMap;
		}catch(Exception e){
			log.error("get main data error!", e);
			return null;
		}
	}

	public Map<String, Org> getOrg(String orgCode) {
		String sql = "SELECT  * FROM ORG";
		List<Map<String,Object>> resultMap = null;
		if(orgCode!=null){
			sql = sql + " WHERE ORG_CODE=?";
			resultMap = jdbcTemplate.queryForList(sql,new Object[]{orgCode});
		}else{
			resultMap = jdbcTemplate.queryForList(sql);
		}
		Map<String,Org> resultList = new Hashtable<String,Org>();
		if(resultMap!=null){
			for (Map<String, Object> map : resultMap) {
				Org org = new Org();
				org.setOrgCode(map.get("ORG_CODE").toString());
				org.setState(map.get("STATE").toString());
				resultList.put(map.get("ORG_CODE").toString(), org);//存放于OrgCode为主建的Org对象
			}
		}
		return resultList;
	}

	public Map<String, ProofEffect> getProofValues(Integer servInvokeId) {
		List<Map<String,String>> resultMap = sqlMapDAOImpl.queryForList("loadCommon.getAllProofValues",servInvokeId);
		if(resultMap!=null && resultMap.size()>0){
			Map<String,ProofEffect> resultList = new Hashtable<String,ProofEffect>();
			for (Map<String, String> map : resultMap) {
				Object peIdObj = map.get("PROOFE_ID");
				String peIdstr = peIdObj.toString();
				ProofEffect pe = null;
				if(resultList.get(peIdstr)!=null){
					pe = (ProofEffect)resultList.get(peIdstr);
				}else{
					pe = new ProofEffect();
					pe.setProofeId(Double.valueOf(peIdObj.toString()).intValue());
					pe.setProofCode(map.get("PT_CODE").toString());
					Object o2 = map.get("SER_INVOKE_INS_ID");
					String s2 = o2.toString();
					pe.setSerInvokeInsId(Double.valueOf(s2).intValue());
					Object o3 = map.get("STATUS");
					String s3 = o3.toString();
					pe.setStatus(s3);
				}
				Object pvIdObj = map.get("PV_ID");
				if(pvIdObj!=null){
					Object attrValueObj = map.get("ATTR_VALUE");
					Object prAtrObj = map.get("PR_ATR_SPEC_CD");
					if(prAtrObj!=null && attrValueObj!=null){
						String  attrSpecId = prAtrObj.toString();
						String str = (String)sqlMapDAOImpl.queryForObject("loadCommon.getProofTypeAtrSpec", attrSpecId);
						if(str != null) {
							Object attobj = pe.getAtts().get(str);
							if(str.equals(EndpointAttr.IP_ADDRESS) || str.equals(EndpointAttr.MAC_ADDRESS)){
								List<String> addresslist = null;
								if(attobj==null){
									addresslist = new ArrayList<String>();
								}else{
									addresslist = (List<String>) attobj;
								}
								addresslist.add(attrValueObj.toString());
								pe.getAtts().put(str, addresslist);
							}else{
								pe.getAtts().put(str, attrValueObj.toString());
							}
						}
					}
				}
				resultList.put(pe.getProofeId()+"", pe);
			}
			return resultList;
		}
		return null;
	}

	public MessageFlow getMessageFlow(String messageflowId){
		List<Map<String,String>> msgFlowMaps = sqlMapDAOImpl.queryForList("loadCommon.getFlowByFlowID",messageflowId);
		if(msgFlowMaps!=null && msgFlowMaps.size()>0){
			Map<String ,String > msgFlowMap = msgFlowMaps.get(0);
			MessageFlow messageFlow = new MessageFlow();
			messageFlow.setMessageFlowId(Double.valueOf(messageflowId).intValue());
			messageFlow.setMessageFlowName(msgFlowMap.get("MESSAGE_FLOW_NAME"));
			messageFlow.setFirstEndpointId(ObjtoInt(msgFlowMap.get("FIRST_ENDPOINT_ID")));
			//init firstEndpoint
			messageFlow.setFirstEndpoint(this.getEndpoint(ObjtoInt(msgFlowMap.get("FIRST_ENDPOINT_ID"))));
			//init route configure
			messageFlow.setRouteConfigList(this.getServiceRouteConfig(messageFlow.getMessageFlowId()+""));
			//init route condition
			this.buildServiceRouteCondition(messageFlow);
			//init route
			this.buildRoute(messageFlow);
			//init endpoint attr item
			this.buildEndpointAttrMap(messageFlow);
			return messageFlow;
		}
		return null;
	}

	public List<SerInvokeIns> getSerInvokeIns(Integer servInvokeId) {
		List<Map<String,String>> resultMap = null;
		Map<String,Integer> paramMap = new HashMap<String,Integer>();
		paramMap.put("servInvokeId", servInvokeId);
		resultMap = sqlMapDAOImpl.queryForList("loadCommon.getSerInvokeInsAndService",paramMap);
		List<SerInvokeIns> serInvokeInsList = new ArrayList<SerInvokeIns>();
		for (Map<String, String> map : resultMap) {
			SerInvokeIns serInvokeIns = new SerInvokeIns();
			serInvokeIns.setComponentId(ObjtoString(map.get("COMPONENT_ID")));
			serInvokeIns.setSerInvokeInsName(map.get("SER_INVOKE_INS_NAME"));
			serInvokeIns.setSerInvokeInsId(ObjtoInt(map.get("SER_INVOKE_INS_ID")));
			serInvokeIns.setServiceId(ObjtoInt(map.get("SERVICE_ID")));
			serInvokeIns.setComponentCode(ObjtoString(map.get("CODE")));
			serInvokeIns.setState(ObjtoString(map.get("STATE")));
			serInvokeIns.setLogLevel(ObjtoString(map.get("LOG_LEVEL")));
//			serInvokeIns.setAuthExpress(ObjtoString(map.get("AUTH_EXPRESS")));
			if(map.get("SERVICE_ID") != null) {
				List<Service> service = this.getService(ObjtoInt(map.get("SERVICE_ID")));
				if(service!=null &&service.size()>0){
					serInvokeIns.setService(service.get(0));
				}
			}
			Object msgFlowId = map.get("MESSAGE_FLOW_ID");
			if(msgFlowId!=null){
				String msgflowidstr = msgFlowId.toString();
				serInvokeIns.setMessageFlow(getMessageFlow(msgflowidstr));
			}
			List<Auth> auths = this.getAuth(serInvokeIns.getSerInvokeInsId());
			serInvokeIns.setAuths(auths);
			serInvokeInsList.add(serInvokeIns);
		}
		return serInvokeInsList;
	}
	
	private void buildServiceRouteCondition(MessageFlow  service){
		if(service.getRouteConfigList()!=null && service.getRouteConfigList().length >0){
			for(ServiceRouteConfig config: service.getRouteConfigList()){
				if(RoutePolicy.CONTENT_BASED.equals(config.getRoutePolicy().getRuleStrategyCode())){
					config.getRoutePolicy().setRouteCondition(getTreeRouteConditionById(config.getRoutePolicy().getRouteCondId()));
				}
			}
		}
	}
	
public List<RouteCondition> findAllRouteConditionById(Integer routeCondId) {
    	List<Map<String,Object>> list = null;
    	List<Map<String,Object>> orderedList=new ArrayList<Map<String,Object>> ();
    	//mdify by zzq 2014/7/23 
		list =sqlMapDAOImpl.queryForList("loadCommon.findAllRouteConditionById", routeCondId.toString());
		orderAllRouteCondition(list,orderedList);
		list=orderedList;
		List<RouteCondition> routeConditionList = new ArrayList<RouteCondition>();
		if(list!=null && list.size()>0){
			for (Map<String, Object> map : list) {
				RouteCondition routeCondition = new RouteCondition();
				routeCondition.setRouteCondId(ObjtoInt(map.get("ROUTE_COND_ID")));
				routeCondition.setUpRouteCondId(ObjtoInt(map.get("UP_ROUTE_COND_ID")));
				routeCondition.setGetValueExprId(ObjtoInt(map.get("GET_VALUE_EXPR_ID")));
				routeCondition.setOperatorId(ObjtoInt(map.get("OPERATOR_ID")));
				if(map.get("OPERATOR_CODE")!=null){
					routeCondition.setOperatorCode(map.get("OPERATOR_CODE").toString());
				}
				if(map.get("MATCH_VALUE")!=null){
					routeCondition.setMatchValue(map.get("MATCH_VALUE").toString());
				}
				routeCondition.setCondRelation(map.get("COND_RELATION")==null?null:map.get("COND_RELATION").toString());
				routeConditionList.add(routeCondition);
			}
		}
		return routeConditionList;
	}
	/**
	 * @author 张志清
	 */
	public void orderAllRouteCondition(List<Map<String,Object>> sourceList,List<Map<String,Object>> orderedList){
		List<Map<String,Object>> childList=new ArrayList<Map<String,Object>> ();
		if(null!=sourceList&&sourceList.size()>0){
			for(Map<String, Object> map : sourceList){
				orderedList.add(map);
			}
			for(Map<String, Object> map : sourceList){
				String routeId=ObjtoString(map.get("ROUTE_COND_ID"));
				childList.addAll(sqlMapDAOImpl.queryForList("loadCommon.findAllRouteConditionByUpId", routeId));
			}	
			orderAllRouteCondition(childList,orderedList);
		}
	}

	public RouteCondition getTreeRouteConditionById(Integer routeCondId) {
    	List<RouteCondition> list = findAllRouteConditionById(routeCondId);
    	for(RouteCondition cond: list){
    		if(cond.getGetValueExprId()!= null){
    			cond.setGetValueExpr(getTreeExprById(cond.getGetValueExprId()));
    		}
    	}
    	RouteCondition routeCond = null;    	
    	if(!list.isEmpty()){
    		routeCond = list.get(0);
    		if(!(routeCond.getRouteCondId().intValue()==routeCondId.intValue())){
    			for(RouteCondition cond: list){    	    		
    	    		if(routeCondId.intValue()==cond.getRouteCondId().intValue()){
    	    			routeCond = cond;
    	    			break;
    	    		}
    			}
    		}
    		routeCond.setChildRouteConditions(getChildRouteCondition(list, routeCond.getRouteCondId()));
    	}
    	return routeCond;
    }

	private List<RouteCondition> getChildRouteCondition(List<RouteCondition> list, Integer routeCondId){
		List<RouteCondition> condList = new ArrayList<RouteCondition>();
		for(int i=0; i<list.size(); i++){  
			RouteCondition cond = list.get(i);
			if(routeCondId.intValue()==cond.getUpRouteCondId()) {
				condList.add(cond); 
				cond.setChildRouteConditions(getChildRouteCondition(list, cond.getRouteCondId()));
			}
		}
		return condList;
	}

	public List<GetValueExpr> findAllExprById(Integer exprId) {
		List<Map<String,Object>> resultList = sqlMapDAOImpl.queryForList("loadCommon.findAllExprById",exprId);
		List<Map<String,Object>> orderedList=new ArrayList<Map<String,Object>> ();
		orderAllExpr(resultList,orderedList);
		resultList=orderedList;
		if(resultList!=null && resultList.size()>0){
			List<GetValueExpr> getValueExprList = new ArrayList<GetValueExpr>();
			for (Map<String, Object> map : resultList) {
				GetValueExpr getValueExpr = new GetValueExpr();
				getValueExpr.setCondEvaluatorCode(map.get("COND_EVALUATOR_CODE").toString());
				getValueExpr.setCondEvaluatorId(Integer.valueOf(map.get("COND_EVALUATOR_ID").toString()));
				getValueExpr.setExprId(exprId);
				getValueExpr.setUpExprId(ObjtoInt(map.get("UP_EXPR_ID")));
				getValueExpr.setExpr(map.get("EXPR").toString());
				getValueExpr.setReqRsp(ObjtoString(map.get("REQ_RSP")));
				getValueExpr.setExprType(ObjtoString(map.get("EXPR_TYPE")));
				List<XmlNameSpace> xmlNameSpace = findNameSpaceByExprId(exprId.toString());
				getValueExpr.setXmlNameSpace(xmlNameSpace);
				getValueExprList.add(getValueExpr);
			}
			return getValueExprList;
		}else{
			return null;
		}
	}
	
	/**
	 * @author 张志清
	 */
	public void orderAllExpr(List<Map<String,Object>> sourceExpr,List<Map<String,Object>> orderedList){
		List<Map<String,Object>> childList=new ArrayList<Map<String,Object>> ();
		if(null!=sourceExpr&&sourceExpr.size()>0){
			for(Map<String, Object> map : sourceExpr){
				orderedList.add(map);
			}
			for(Map<String, Object> map : sourceExpr){
				int exprId=ObjtoInt(map.get("ROUTE_COND_ID"));
				childList.addAll(sqlMapDAOImpl.queryForList("loadCommon.findAllExprByUpExprId", exprId));
			}	
			orderAllExpr(childList,orderedList);
		}
	}

	private List<XmlNameSpace> findNameSpaceByExprId(String exprId){
		List<Map<String,Object>> resultList = sqlMapDAOImpl.queryForList("loadCommon.get0020",exprId);
		if(resultList!=null && resultList.size()>0){
			List<XmlNameSpace> xmlNameSpaceList = new ArrayList<XmlNameSpace>();
			for (Map<String, Object> map : resultList) {
				XmlNameSpace xmlNameSpace = new XmlNameSpace();
				xmlNameSpace.setNameSpaceDesc(map.get("NAME_SPACE_DESC").toString());
				xmlNameSpace.setNameSpaceUri(map.get("NAME_SPACE_URI").toString());
				xmlNameSpace.setNameSpacePrefix(map.get("NAME_SPACE_PREFIX").toString());
				xmlNameSpace.setNameSpaceId(Integer.valueOf(map.get("NAME_SPACE_ID").toString()));
				xmlNameSpaceList.add(xmlNameSpace);
			}
			return xmlNameSpaceList;
		}else{
			return null;
		}
	}

	public GetValueExpr getTreeExprById(Integer exprId) {
		GetValueExpr record = null;		
		List<GetValueExpr> list = findAllExprById(exprId);    
		if(list!=null){
	    	for(Object obj: list){
	    		GetValueExpr expr = (GetValueExpr)obj;
	    		buildNameSpaceMap(expr);
	    	}
	    	if(!list.isEmpty()){
	    		record = (GetValueExpr)list.get(0);
	    		if(!(record.getExprId().intValue()==exprId.intValue())){
	    			for(Object obj: list){
	    	    		GetValueExpr expr = (GetValueExpr)obj;
	    	    		if(exprId.intValue()==expr.getExprId().intValue()){
	    	    			record = expr;
	    	    			break;
	    	    		}
	    			}
	    		}
	    		record.setChildExpr(getChildExpr(list, record.getExprId()));
	    	}
		}
	    return record;
	}
	
	private GetValueExpr getChildExpr(List<GetValueExpr> list, Integer exprId){
    	GetValueExpr getValueExpr = null;
    	for(Object obj: list){
    		GetValueExpr expr = (GetValueExpr)obj;
    		if(exprId.intValue()==expr.getUpExprId()) {    			
    			expr.setChildExpr(getChildExpr(list, expr.getExprId()));
    			getValueExpr = expr;
    			break;
    		}
    	}
    	return getValueExpr;
	}
	
	private void buildNameSpaceMap(GetValueExpr expr){
    	if(expr.getXmlNameSpace()!=null){
    		if(!expr.getXmlNameSpace().isEmpty()){
        		Map<String, String> map = new HashMap<String, String>();
    	    	for(XmlNameSpace ns : expr.getXmlNameSpace()){
    	    		map.put(ns.getNameSpacePrefix(), ns.getNameSpaceUri());
    	    	}
    	    	expr.setNameSpaceMap(map);
    	    	expr.setXmlNameSpace(null);	    	
        	}
    	}
    }
	
		
	private String getDataType(int dateTypeId){
		return (String)sqlMapDAOImpl.queryForObject("loadCommon.getDataType",dateTypeId);
	}
	
	/************************************mySql level******************************************/
	static int level = 0;
	private void getLevel(String routeId,Map<String, Object> levelMap){
		if(levelMap.get(routeId) == null){
			level = level + 1;
			 return;
	    }else{
	    	String up_routeId = levelMap.get(routeId).toString();
	    	level = level + 1;
	    	getLevel(up_routeId,levelMap);
	    }
	}
	
	private int getRouteLevel(String routeId,Map<String, Object> levelMap){
	    level = 0;
		getLevel(routeId,levelMap);
		return level;
	}
	/************************************mySql level******************************************/

	
	private ServiceRouteConfig[] getServiceRouteConfig(String msgFlowid){
		List<Map<String,Object>> list = sqlMapDAOImpl.queryForList("loadCommon.getServiceRouteConfig",msgFlowid);
		Map<String, Object> levelMap = new HashMap<String, Object>();
		if(list!=null){
			for (Map<String, Object> map : list) {
				if(map.get("LEVEL") == null){
					levelMap.put(map.get("ROUTE_ID").toString(), map.get("UP_ID"));
				}
			}
		}
		if(list!=null){
			ServiceRouteConfig[] srcs = new ServiceRouteConfig[list.size()];
			int i = 0;
			for (Map<String, Object> map : list) {
				
				ServiceRouteConfig serviceRouteConfig = new ServiceRouteConfig();
				if(map.get("LEVEL") == null ){
					serviceRouteConfig.setLevel(this.getRouteLevel(map.get("ROUTE_ID").toString(), levelMap));
				}else{
					serviceRouteConfig.setLevel(Integer.valueOf(map.get("LEVEL").toString()));
				}
				serviceRouteConfig.setMessageFlowId(Integer.valueOf(msgFlowid));
				serviceRouteConfig.setSynAsyn(map.get("SYN_ASYN").toString());
				serviceRouteConfig.setState(map.get("STATE").toString());
				Object routeid = map.get("ROUTE_ID");
				String routeidstr = routeid.toString();
				serviceRouteConfig.setRouteId(Double.valueOf(routeidstr).intValue());
				int fromendpointId = ObjtoInt(map.get("FROM_ENDPOINT_ID"));
				int toendpointId = ObjtoInt(map.get("TO_ENDPOINT_ID"));
				Object routeidobj = map.get("ROUTE_POLICY_ID");
				String routeidstr2 = routeidobj.toString();
				String routepoliceId = Double.valueOf(routeidstr2).intValue()+"";
				Endpoint frompoint = getEndpoint(fromendpointId);
				Endpoint topoint = getEndpoint(toendpointId);				
				RoutePolicy rp = findRoutePolicy(routepoliceId);
				serviceRouteConfig.setFromEndpoint(frompoint);
				serviceRouteConfig.setToEndpoint(topoint);
				serviceRouteConfig.setRoutePolicy(rp);
				serviceRouteConfig.setFromEndpointId(Integer.valueOf(fromendpointId));
				serviceRouteConfig.setToEndpointId(Integer.valueOf(toendpointId));
				srcs[i++] = serviceRouteConfig;
			}
			return srcs;
		}else{
			return null;
		}
	}
	
	private RoutePolicy findRoutePolicy(String routePolicyId){
		List<Map<String,Object>> list = sqlMapDAOImpl.queryForList("loadCommon.findRoutePolicy",routePolicyId);
		if(list!=null &&list.size()>0){
			RoutePolicy routePolicy = new RoutePolicy();
			Map<String,Object> map = list.get(0);
			routePolicy.setRouteCondId(ObjtoInt(map.get("ROUTE_COND_ID")));
			routePolicy.setRoutePolicyId(ObjtoInt(map.get("ROUTE_POLICY_ID")));
			routePolicy.setRuleStrategyCode(map.get("RULE_STRATEGY_CODE").toString());
			routePolicy.setRuleStrategyId(ObjtoInt(map.get("RULE_STRATEGY_ID")));
			return routePolicy;
		}else{
			return null;
		}
	}
	
	public Endpoint getEndpoint(int endpointid){
		List<Map<String,Object>> endpoints = sqlMapDAOImpl.queryForList("loadCommon.getEndpoint",endpointid);
		if(endpoints!=null && endpoints.size()>0){
			Map<String,Object> map = endpoints.get(0);
			Endpoint endpoint = new Endpoint();
			endpoint.setEndpointName(map.get("ENDPOINT_NAME").toString());
			endpoint.setEndpointSpecCode(map.get("ENDPOINT_SPEC_CODE").toString());
			endpoint.setEndpointSpecEnableLog(map.get("ENABLE_LOG")==null?null:map.get("ENABLE_LOG").toString());
			endpoint.setEndpointId(Integer.valueOf(endpointid));
			if(map.get("IN_DATA_TYPE_ID")!=null){
				endpoint.setInDataTypeCode(getDataType(ObjtoInt(map.get("IN_DATA_TYPE_ID"))));
			}
			if(map.get("OUT_DATA_TYPE_ID")!=null){
				endpoint.setOutDataTypeCode(getDataType(ObjtoInt(map.get("OUT_DATA_TYPE_ID"))));
			}
			if(map.get("ENDPOINT_ID")!=null){
				endpoint.setEndpointId(Integer.valueOf(map.get("ENDPOINT_ID").toString()));
			}
			endpoint.setEnableInTrace(map.get("ENABLE_IN_TRACE")==null?null:map.get("ENABLE_IN_TRACE").toString());
			endpoint.setEnableOutTrace(map.get("ENABLE_OUT_TRACE")==null?null:map.get("ENABLE_OUT_TRACE").toString());
			endpoint.setEnableInLog(map.get("ENABLE_IN_LOG")==null?null:map.get("ENABLE_IN_LOG").toString());
			endpoint.setEnableOutLog(map.get("ENABLE_OUT_LOG")==null?null:map.get("ENABLE_OUT_LOG").toString());
			List<EndpointAttr> attrs = this.findEndpointAttr(endpointid);
			endpoint.setEndpointAttrList(attrs);
			return endpoint;
		}
		return null;
	}
	
	
	private List<EndpointAttr> findEndpointAttr(int endpointID){
		List<Map<String,Object>> list = sqlMapDAOImpl.queryForList("loadCommon.getEndpointAttr",endpointID);
		if(list!=null){
			List<EndpointAttr> endpointattrs = new ArrayList<EndpointAttr>();
			for (Map<String, Object> map : list) {
				EndpointAttr endpointAttr = new EndpointAttr();
				endpointAttr.setEndpointAttrValueId(Integer.valueOf(map.get("ENDPOINT_ATTR_VALUE_ID").toString()));
				endpointAttr.setEndpointId(Integer.valueOf(map.get("ENDPOINT_ID").toString()));
				endpointAttr.setAttrValue(map.get("ATTR_VALUE")==null?null:map.get("ATTR_VALUE").toString());
				endpointAttr.setEndpointSpecId(Integer.valueOf(map.get("ENDPOINT_SPEC_ID").toString()));
				endpointAttr.setEndpointSpecAttrId(Integer.valueOf(map.get("ENDPOINT_SPEC_ATTR_ID").toString()));
				endpointAttr.setAttrSpecId(Integer.valueOf(map.get("ATTR_SPEC_ID").toString()));
				endpointAttr.setAttrSpecName(map.get("ATTR_SPEC_NAME").toString());
				endpointAttr.setAttrSpecCode(map.get("ATTR_SPEC_CODE").toString());
				endpointAttr.setNullable(Boolean.valueOf(map.get("NULLABLE").toString()));
				endpointAttr.setUpAttrSpecId(map.get("UP_ATTR_SPEC_ID")==null?null:Integer.valueOf(map.get("UP_ATTR_SPEC_ID").toString()));
//				endpointAttr.setCacheStrategy(map.get("ATTR_VALUE")==null?null:map.get("ATTR_VALUE").toString());
				endpointattrs.add(endpointAttr);
			}
			return endpointattrs;
		}else{
			return null;
		}
	}
	
	private  Contract getContract(String contractId){
		List<Map<String,String>> resultMap = sqlMapDAOImpl.queryForList("loadCommon.getContract",contractId);
		if(resultMap!=null && resultMap.size()>0){
			Map<String,String> map = resultMap.get(0);
			Contract contract = new Contract();
			contract.setCode(map.get("CODE").toString());
			return contract;
		}
		return null;
	}
	
	public List<Service> getService(Integer serviceId) {
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("serviceId", serviceId);
		List<Map<String,String>> resultMap = sqlMapDAOImpl.queryForList("loadCommon.getAllService",paramMap);
		List<Service> resultList = new ArrayList<Service>();
		for (Map<String, String> map : resultMap) {
			ContractVersion cv = new ContractVersion();
			cv.setVersion(map.get("VERSION").toString());
			Contract ca = this.getContract(ObjtoInt(map.get("CONTRACT_ID"))+"");
			cv.setContract(ca);
			Service s = new Service();
			s.setServiceCnName(map.get("SERVICE_CN_NAME"));
			s.setServiceId(ObjtoInt(map.get("SERVICE_ID")));
			s.setServiceCode(map.get("SERVICE_CODE").toString());
			s.setServiceVersion(map.get("SERVICE_VERSION").toString());
			s.setState(map.get("STATE").toString());
			s.setContractVersion(cv);
			List<Map<String,String>> busCodeMap = sqlMapDAOImpl.queryForList("loadCommon.getFunctionCode", s.getServiceId());
			if(busCodeMap!=null){
				for (Map<String, String> map2 : busCodeMap) {
					String busCode = map2.get("CODE").toString();
					s.setBusCode(busCode);
				}
			}
			List<Map<String,String>> relaMap = sqlMapDAOImpl.queryForList("loadCommon.getServiceRela", s.getServiceId());
			if(relaMap!=null && relaMap.size()>0){
				ServiceRela serviceRela = new ServiceRela();
				for (Map<String, String> map3 : relaMap) {
					Object regObj = map3.get("REG_SERVICE_ID");
					String strObj = regObj.toString();
					serviceRela.setOpenServiceId(Double.valueOf(strObj).intValue());
					s.setRelaService(getService(Double.valueOf(strObj).intValue()).get(0));
					s.setServiceRelaType(map3.get("SER_RELA_TYPE"));
				}
			}
			resultList.add(s);
		}
		return resultList;
	}

	public List<TaskBase> getTaskBase() {
		List<Map<String,String>> resultList = sqlMapDAOImpl.queryForList("loadCommon.getTaskBase",null);
		if(resultList!=null && resultList.size()>0){
			List<TaskBase> tbs = new ArrayList<TaskBase>();
			for (Map<String, String> map : resultList) {
				TaskBase taskBase = new TaskBase();
				taskBase.getTaskManager().getGatherCycle().setGcSEExp(map.get("GC_S_E_EXP").toString());
				taskBase.getTaskManager().setThreadCount(ObjtoInt(map.get("THREAD_NUMBER")));
				taskBase.getTaskManager().setTaskType(ObjtoInt(map.get("TASK_TYPE_CD")));
				TechImpl techImpl = new TechImpl();
				techImpl.setAttrMap(getTechAttr(ObjtoInt(map.get("TECH_IMPL_ID"))));
				taskBase.setTaskCfg(techImpl);
				taskBase.setSerInvokeIns(getSerInvokeIns(ObjtoInt(map.get("SER_INVOKE_INS_ID"))).get(0));
				tbs.add(taskBase);
			}
			return tbs;
		}
		return null;
	}
	
	private Map<String, String> getTechAttr(int techId) {
		List<Map<String, String>> resultList = sqlMapDAOImpl.queryForList("loadCommon.getTechAttr", techId );
		if (resultList != null && resultList.size() > 0) {
			Map<String, String> attrMap = new Hashtable<String, String>();
			for (Map<String, String> map : resultList) {
				attrMap.put(map.get("ATTR_SPEC_CODE"), map
						.get("ATTR_SPEC_VALUE") == null ? "" : map.get(
						"ATTR_SPEC_VALUE").toString());
			}
			return attrMap;
		}
		return null;
	}

public List<TechImpl> getTechImpl() {
		List<Map<String,Object>> resultList = sqlMapDAOImpl.queryForList("loadCommon.getTechImpl",null);
		if(resultList!=null && resultList.size()>0){
			List<TechImpl> tis = new ArrayList<TechImpl>();
			for (Map<String, Object> map : resultList) {
				TechImpl ti = new TechImpl();
				ti.setTechImplId(Double.valueOf(map.get("TECH_IMPL_ID").toString()).intValue());
				ti.setTechImplName(map.get("TECH_IMPL_NAME")==null?null:map.get("TECH_IMPL_NAME").toString());
				if(map.get("COMM_PRO_CD")!=null){
					ti.setCommProCd(map.get("COMM_PRO_CD").toString());
				}
				if(map.get("COMPONENT_ID") != null) {
					ti.setComponentId(map.get("COMPONENT_ID").toString());
				}
				if(map.get("USEALBE_STATE") != null) {
					ti.setUsealbeState(map.get("USEALBE_STATE").toString());
				}
				if(map.get("SER_TECH_IMPL_ID") != null) {
					ti.setSerTechId(Double.valueOf(map.get("SER_TECH_IMPL_ID").toString()).intValue());
				}
				if(map.get("SERVICE_ID") != null) {
					ti.setServiceId(Double.valueOf(map.get("SERVICE_ID").toString()).intValue());
				}
				List<Service>  ss= this.getService(ti.getServiceId());
				if(ss!=null&&ss.size()>0){
					ti.setService(ss.get(0));
				}
				ti.setSerTechStatus(map.get("SER_TECH_STATE").toString());
				ti.setServiceCode(map.get("SERVICE_CODE").toString());
				ti.setServiceVersion(map.get("SERVICE_VERSION").toString());
				ti.setComponentCode(map.get("CODE").toString());
				if(map.get("TECH_IMP_CON_PO_ID")!=null){
					ti.setTechImpConPoId(Double.valueOf(map.get("TECH_IMP_CON_PO_ID").toString()).intValue());
					ti.setTico(getTicp(ti.getTechImpConPoId(),ti.getTechImplId()));
				}
				ti.setAttrMap(getTechAttr(ti.getTechImplId()));
				ti.setCtls(getCtlCounter(ti.getTechImplId()));
				ti.setTechImplNode(this.getTechImplNode(ti.getTechImplId()));
				tis.add(ti);
			}
			return tis;
		}
		return null;
	}
	
	
	public List<TechImplNode> getTechImplNode(int techImplId){
		
		List<Map<String,String>> resultList = sqlMapDAOImpl.queryForList("loadCommon.techImplNode",techImplId);
		if(resultList!=null && resultList.size()>0){
			List<TechImplNode> tcps = new ArrayList<TechImplNode>();
			
			
			for (Map<String, String> map : resultList) {
				TechImplNode tcp = new TechImplNode();
				tcp.setTechImplNodeId(ObjtoInt(map.get("TECH_IMPL_NODE_ID")));
				tcp.setTechImplId(techImplId);
				tcp.setNodeHost(map.get("NODE_HOST").toString());
				tcp.setNodeIp(map.get("NODE_IP"));
				tcp.setNodePort(map.get("NODE_PORT"));
				tcp.setRouteExpr(map.get("TECH_ROUTE_EXPR"));
				tcps.add(tcp);
			}
			log.debug("techImplId"+techImplId+tcps.size());
			return tcps;
		}
		return null;
	}

	public TechImpContralPo getTicp(int ticpId,int techImplId){
		List<Map<String,String>> resultList = sqlMapDAOImpl.queryForList("loadCommon.getTicp",ticpId);
		if(resultList!=null && resultList.size()>0){
			TechImpContralPo tcp = new TechImpContralPo();
			Map<String, String> map = resultList.get(0);
			tcp.setConAction(map.get("CON_ACTION").toString());
			tcp.setConCycle(map.get("CON_CYCLE").toString());
			tcp.setConState(map.get("CON_STATE").toString());
			tcp.setConType(map.get("CON_TYPE").toString());
			tcp.setConCycleValue(ObjtoInt(map.get("CON_CYCLE_VALUE")));
			tcp.setConVl(ObjtoInt(map.get("CON_VL")));
			tcp.setState(map.get("STATE").toString());
			tcp.setUsableMonitorConf(getUsable(ticpId, techImplId));
			tcp.setTechImpl(techImplId);
			return tcp;
		}
		return null;
	}
	
	public  List<App> getApp(Integer appId) {
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("appId", appId);
		List<Map<String,Object>> resultList = sqlMapDAOImpl.queryForList("loadCommon.getAppByAppID",paramMap);
		if(resultList!=null){
			List<App> apps = new ArrayList<App>();
			for (Map<String, Object> map : resultList) {
				App app = new App();
				app.setAppName(map.get("APP_NAME")==null?null:map.get("APP_NAME").toString());
				app.setComponentId(map.get("COMPONENT_ID")==null?null:map.get("COMPONENT_ID").toString());
				app.setAppkey(map.get("APPKEY")==null?null:map.get("APPKEY").toString());
				app.setAppDesc(map.get("APP_DESC")==null?null:map.get("APP_DESC").toString());
				app.setAppsecure(map.get("APPSECURE")==null?null:map.get("APPSECURE").toString());
				app.setAppOauthType(map.get("APP_OAUTH_TYPE")==null?null:map.get("APP_OAUTH_TYPE").toString());
				app.setAppCallbackUrl(map.get("APP_CALLBACK_URL")==null?null:map.get("APP_CALLBACK_URL").toString());
				app.setAppSumma(map.get("APP_SUMMA")==null?null:map.get("APP_SUMMA").toString());
				app.setTokenEnableTime(ObjtoInt(map.get("TOKEN_ENABLE_TIME")));
				app.setComponentCode(map.get("CODE")==null?null:map.get("CODE").toString());
				if(app.getTokenEnableTime()==0){
					app.setTokenEnableTime(Integer.valueOf(tokenEnableTime));
				}
				Object appObj = map.get("APP_ID");
				String appStr = appObj.toString();
				app.setAppId(Double.valueOf(appStr).intValue());
				app.setAppApiList(getAppList(Double.valueOf(appStr).intValue()));
				apps.add(app);
			}
			return apps;
		}
		return null;
	}
	
	public List<CtlCounterms2Comp> getCtlCounter(Integer techId) {
		if(techId!=null){
			List<Map<String,String>> resultList = sqlMapDAOImpl.queryForList("loadCommon.getCtlCounter",techId);
			if(resultList!=null && resultList.size()>0){
				List<CtlCounterms2Comp> ctls = new ArrayList<CtlCounterms2Comp>();
				for (Map<String, String> map : resultList) {
					CtlCounterms2Comp ct = new CtlCounterms2Comp();
					Object appObj = map.get("CUTMS_VALUE");
					String appObjStr = appObj.toString();
					ct.setCutmsValue(Double.valueOf(appObjStr).intValue());
					ct.setCycleType(map.get("CYCLE_TYPE").toString());
					Object cyO = map.get("CYCLE_VALUE");
					String cyStr = cyO.toString();
					ct.setCycleValue(Double.valueOf(cyStr).intValue());
					ct.setEffectState(map.get("EFFECT_STATE").toString());
					Object ccObj = map.get("CC_CD");
					String ccStr = ccObj.toString();
					ct.setCcCd(Double.valueOf(ccStr).intValue());
					ctls.add(ct);
				}
				return ctls;
			}
		}
		return null;
	}
	
	public List<UsableMonitorConf> getUsable(int techPoId,int techImplId){
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("techPoId", techPoId);
		paramMap.put("techImplId", techImplId);
		List<Map<String,String>> resultList = sqlMapDAOImpl.queryForList("loadCommon.getUsable",paramMap);
		if(resultList!=null && resultList.size()>0){
			List<UsableMonitorConf> umcs = new ArrayList<UsableMonitorConf>();
			for (Map<String, String> map : resultList) {
				UsableMonitorConf umc = new UsableMonitorConf();
				umc.setState(map.get("STATE").toString());
				umc.setXmlMsg(map.get("XML_MSG").toString());
				umc.setUtCd(ObjtoInt(map.get("UT_CD")));
				umc.setTechImpConPoId(techPoId);
				umc.setTechImplId(techImplId);
				umcs.add(umc);
			}
			return umcs;
		}
		return null;
	}

	public List<AppUserInfo> getUserInfo() {
		List<Map<String,Object>> resultList = sqlMapDAOImpl.queryForList("loadCommon.getUserInfo",null);
		if(resultList!=null && resultList.size()>0){
			List<AppUserInfo> appUserInfoList = new ArrayList<AppUserInfo>();
			for (Map<String, Object> map : resultList) {
				AppUserInfo appuserInfo = new AppUserInfo();
				UserInfo userInfo = new UserInfo();
				userInfo.setProductNbr(map.get("PRODUCT_NBR").toString());
				userInfo.setUserId(ObjtoInt(map.get("USER_ID")));
				userInfo.setUserCustId(map.get("USER_CUST_ID")==null?null:map.get("USER_CUST_ID").toString());
				appuserInfo.setUserInfo(userInfo);
				List<Map<String,Object>> oauthList = sqlMapDAOImpl.queryForList("loadCommon.getAppAccTokenAndApp",userInfo.getUserId());
				if(oauthList!=null && oauthList.size()>0){
					List<App> appList = new ArrayList<App>();
					for (Map<String, Object> authMap : oauthList) {
						String oauth = authMap.get("OAUTH_API_LIST")==null?null:authMap.get("OAUTH_API_LIST").toString();
						Object appObj = authMap.get("APP_ID");
						String appObjStr = appObj.toString();
						App app = this.getApp(Double.valueOf(appObjStr).intValue()).get(0);
						app.setAppId(Double.valueOf(appObjStr).intValue());
						appList.add(app);
						String[] oauths = oauth==null?null:oauth.split(",");
						if(oauths!=null && oauths.length>0){
							List<Api> api = new ArrayList<Api>();
							for (int i = 0; i < oauths.length; i++) {
								List<Api> queryApi = this.getApi(Integer.valueOf(oauths[i]));
								if(queryApi!=null && queryApi.size()>0){
									api.add(queryApi.get(0));
								}
							}
							app.setOauthApiList(api);
						}	
					}
					appuserInfo.setAppList(appList);
				}
				appUserInfoList.add(appuserInfo);
			}
			return appUserInfoList;
		}
		return null;
	}
	
	public String clobToString(Clob clob) throws Exception {
		BufferedReader br =null;
		try{
			 String reString = "";  
			 Reader reader = clob.getCharacterStream();
			 br = new BufferedReader(reader);  
			 String string = br.readLine();
			 StringBuffer sb = new StringBuffer();  
			 while (string != null) {
				 sb.append(string);  
				 string = br.readLine();  
			 }  
			 reString = sb.toString();  
			 return reString;
		}catch(Exception e){
			throw e;
		}finally{
			if(br!=null){
				br.close();
			}
		}
	}

	public Map<String, Object> loadDataSourceRoute() {
		Map<String, Object> dataSourceRouteMap = new Hashtable<String,Object>();
		List<Map<String,Object>> list  = sqlMapDAOImpl.queryForList("loadCommon.loadDataSourceRoute", null);
		Iterator<Map<String, Object>> it = list.iterator();
		while(it.hasNext()){
			Map<String, Object> map = (Map<String, Object>)it.next();
			String decisionRule = (String)map.get("DECISION_RULE");
			Expr expr = ExprUtil.createExpr(decisionRule); 
			map.put("EXPR", expr);
		}
		dataSourceRouteMap.put("dataSourceRouteList", list);
		list = sqlMapDAOImpl.queryForList("loadCommon.getDataRource",null);
		dataSourceRouteMap.put("dataSourceList", list);
		Map<String, Object> dataSourceMap = new ConcurrentHashMap<String, Object>();
		it = list.iterator();
		while(it.hasNext()){
			Map<String, Object> map = (Map<String, Object>)it.next();
			String dsName = (String)map.get("DATA_SOURCE_NAME");
			dataSourceMap.put(dsName, map);
		}
		dataSourceRouteMap.put("dataSourceMap", dataSourceMap);
		return dataSourceRouteMap;
	}

	public List<Component> getComponentList(String code) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("code", code);
		return sqlMapDAOImpl.queryForList("loadCommon.getComponent", paramMap);
	}

	public List<BizFunction> getBusCodeList(String code) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("code", code);
		return sqlMapDAOImpl.queryForList("loadCommon.getBusCode", paramMap);
	}

	public List<Org> getOrgList(String orgCode) {
		return sqlMapDAOImpl.queryForList("loadCommon.getAllOrg", orgCode);
	}
	public void buildRoute(MessageFlow service){
		Endpoint firstPoint = null;
		RouteEndpoint firstRouteEndpoint = new RouteEndpoint();
		if(service.getRouteConfigList().length<1){
			return;
		}
		firstPoint = service.getFirstEndpoint();
		for(ServiceRouteConfig config : service.getRouteConfigList()){
			if(config.getLevel().intValue() == ServiceRouteConfig.ROOT_LEVEL){
				firstPoint = config.getFromEndpoint();
				break;
			}
		}		
		firstRouteEndpoint.setCurrentEndpoint(firstPoint);	
		int count = 0;
		buildRoute(service, firstRouteEndpoint, count);
		service.setFirstRouteEndpoint(firstRouteEndpoint);
	}

	private void buildEndpointAttrMap(Endpoint endpoint){
		Map<String, Object> attrMap = new HashMap<String, Object>();
		for(EndpointAttr attr: endpoint.getEndpointAttrList()){			
			if(DataType.STRING.equalsIgnoreCase(attr.getDataTypeCode())){
				attrMap.put(attr.getAttrSpecCode(), attr.getAttrValue());
			}else if(DataType.INT.equalsIgnoreCase(attr.getDataTypeCode())){
				Integer intValue = Integer.valueOf(-9999);
				intValue = Integer.valueOf(attr.getAttrValue());
				attrMap.put(attr.getAttrSpecCode(), intValue);
			}else if(DataType.LONG.equalsIgnoreCase(attr.getDataTypeCode())){
				Long lValue = Long.valueOf(-9999);
				lValue = Long.valueOf(attr.getAttrValue());
				attrMap.put(attr.getAttrSpecCode(), lValue);
			}else if(DataType.DOUBLE.equalsIgnoreCase(attr.getDataTypeCode())){
				Double dValue = Double.valueOf(-9999);
				Double.valueOf(attr.getAttrValue());
				attrMap.put(attr.getAttrSpecCode(), dValue);
			}else {		
				attrMap.put(attr.getAttrSpecCode(), attr.getAttrValue());
			}		
		}	
		endpoint.setAttrMap(attrMap);
	}

	private void buildEndpointAttrMap(MessageFlow service){
		if(service.getFirstEndpoint() != null && service.getFirstEndpoint().getAttrMap()==null){
			buildEndpointAttrMap(service.getFirstEndpoint());
		}
		for(ServiceRouteConfig src: service.getRouteConfigList()){
			if(src.getFromEndpoint()!=null && src.getFromEndpoint().getAttrMap() == null){
				buildEndpointAttrMap(src.getFromEndpoint());
			}
			if(src.getToEndpoint()!=null && src.getToEndpoint().getAttrMap() == null){
				buildEndpointAttrMap(src.getToEndpoint());
			}
		}
	}

			
	private static void buildRoute(MessageFlow service, RouteEndpoint firstPoint, int count){
		ArrayList<ToRouteEndpoint> list = new ArrayList<ToRouteEndpoint>();
		for(ServiceRouteConfig config : service.getRouteConfigList()){
			if(firstPoint.getCurrentEndpoint()!=null && config.getFromEndpoint()!=null && firstPoint.getCurrentEndpoint().getEndpointId()==config.getFromEndpoint().getEndpointId()){
				ToRouteEndpoint tre = new ToRouteEndpoint();
				tre.setRoutePolicy(config.getRoutePolicy());	
				RouteEndpoint re = new RouteEndpoint();
				re.setCurrentEndpoint(config.getToEndpoint());
				tre.setToEndpoint(re);
				list.add(tre);
			}
		}
		count++;
		if(list.size()>0){
			firstPoint.setChildEndpoints(list);
			for(ToRouteEndpoint toEndpoint : list){	
				buildRoute(service, toEndpoint.getToEndpoint(),count);
			}
		}else{
			firstPoint.setChildEndpoints(null);	
		}
	}

	public IBatisSqlMapDAOImpl getSqlMapDAOImpl() {
		return sqlMapDAOImpl;
	}

	public void setSqlMapDAOImpl(IBatisSqlMapDAOImpl sqlMapDAOImpl) {
		this.sqlMapDAOImpl = sqlMapDAOImpl;
	}

	public String getTokenEnableTime() {
		return tokenEnableTime;
	}

	public void setTokenEnableTime(String tokenEnableTime) {
		this.tokenEnableTime = tokenEnableTime;
	}

	public List<AppAccToken> getAppAccToken(int begin, int end) {
        Map<String, Integer> param = new HashMap<String, Integer>();
        param.put("begin", begin);
        param.put("end", end);
		List<Map<String,String>> resultMap = sqlMapDAOImpl.queryForList("loadCommon.getAppAccToken",param);
		List<AppAccToken> resultList = new ArrayList<AppAccToken>();
		for (Map<String, String> map : resultMap) {
			AppAccToken aat = new AppAccToken();
			aat.setApp(this.getApp(ObjtoInt(map.get("APP_ID"))).get(0));
			aat.setAccToken(map.get("ACC_TOKEN"));
			UserInfo user = getUserInfo(ObjtoInt(map.get("USER_ID")));
			aat.setUserInfo(user);
			aat.setAppAccTokId(ObjtoInt(map.get("APP_ACC_TOK_ID")));
			aat.setDisableTime(map.get("DISABLE_TIME"));
			String str[] = map.get("OAUTH_API_LIST").toString().split(",");
			List<Api> apis = new ArrayList<Api>();
			for (int i = 0; i < str.length; i++) { 
				List<Map<String,String>> apiMap = sqlMapDAOImpl.queryForList("loadCommon.getApiw2ByApiID",str[i]);
				for (Map<String, String> map2 : apiMap) {
					Api api = new Api();
					api.setApiId(ObjtoInt(map2.get("API_ID")));
					api.setApiMethod(map2.get("API_METHOD").toString());
					api.setApiName(map2.get("API_NAME").toString());
					api.setServiceId(ObjtoInt(map2.get("SERVICE_ID")));
					apis.add(api);
				}
			}
			aat.setApis(apis);
			resultList.add(aat);
		}
		return resultList;
	}
	
	private UserInfo getUserInfo(int userId){
		List<Map<String,String>> resultMap = sqlMapDAOImpl.queryForList("loadCommon.getUserInfoByUserId",userId);
		if(resultMap!=null){
			UserInfo user = new UserInfo();
			Map<String,String> map = new Hashtable<String,String>();
			user.setProductNbr(map.get("PRODUCT_NBR")==null?null:map.get("PRODUCT_NBR").toString());
			return user;
		}else{
			return null;
		}
	}

	public int getAppAccTokenCount() {
		return (Integer)sqlMapDAOImpl.queryForObject("loadCommon.getAppAccTokenCount", null);
	}

	public List<Map<String, Object>> getWsdlFileList() {
		return sqlMapDAOImpl.queryForList("loadCommon.getWsdlFileList",null);
	}

	public List<Map<String, Object>> getApiOperationList() {
		return sqlMapDAOImpl.queryForList("loadCommon.getApiOperationList",null);
	}

	public List<Map<String, Object>> getHeadNodeDescList() {
		return sqlMapDAOImpl.queryForList("loadCommon.getHeadNodeDescList",null);
	}

	public List<ContractVersion> getHeadContractVerList() {
		return  sqlMapDAOImpl.queryForList("loadCommon.getHeadContractVer",null);
	}

	public List<Map<String, Object>> getContractOperationList(Map<String, String> paramMap) {
		return sqlMapDAOImpl.queryForList("loadCommon.getContractOperationList",paramMap);
	}

	@Override
	public List<Map<String, Object>> getRestConfigList(Map<String, String> param) {
		return sqlMapDAOImpl.queryForList("loadCommon.getRestConfigList",param);
	}

	/**
	 * 得到所有的模糊注册表信息
	 */
	@Override
	public List<RegisterObject> getAllRegTable() {
		return sqlMapDAOImpl.queryForList("loadCommon.getAllRegTable",null);
}

	/**
	 * 得到对应用表的模糊配置
	 * @param register_object_id
	 * @return
	 */
	@Override
	public Map<String, HideMethod> getAllHideProcessById(String register_object_id) {
		List<RegisterObject> list = sqlMapDAOImpl.queryForList("loadCommon.getAllHideProcessById",register_object_id);
		Map<String, HideMethod> map = new HashMap<String,HideMethod>();
		for(RegisterObject regObj : list){
			HideMethod hideMethod = new HideMethod();
			hideMethod.setHide_type_id(regObj.getHide_type_id());
			hideMethod.setIs_split(regObj.getIs_split());
			hideMethod.setReplace_expression(regObj.getReplace_expression());
			hideMethod.setReplace_sign(regObj.getReplace_sign());
			hideMethod.setSplit_expression(regObj.getSplit_expression());
			hideMethod.setSplit_sign(regObj.getSplit_sign());
			map.put(regObj.getHide_field_name(), hideMethod);
		}
		return map;
	}

	/**
	 * 获得所有自定义端点的配置信息
	 */
	@Override
	public List<ContextParamList> getAllAutoEndPoints() {
		List<ContextParamList> listContextParamList = new ArrayList<ContextParamList>();
		//得到所有自定义端点的ID
		List<String> end_point_ids = sqlMapDAOImpl.queryForList("loadCommon.getAllAutoEndPointIds", null);
		if(null != end_point_ids && end_point_ids.size() > 0){
			for(String end_point_id : end_point_ids){
				//由端点ID得到自定义的参数列表
				List<ContextParam> listContextParam = sqlMapDAOImpl.queryForList("loadCommon.getContextParamById", end_point_id);
				//获取动态脚本内容
				List<DyScript> listDyScript = sqlMapDAOImpl.queryForList("loadCommon.getDyScriptById",end_point_id);
				if(null != listContextParam && listContextParam.size() > 0){
					for(ContextParam param : listContextParam){
						if(ContextType.CACTHE.toString().equals(param.getContext_type_name())){//缓存类型参数
							String param_id = param.getContext_param_id();
							//得到缓存实例配置
							List<ContextCacheIns> listContextCacheIns = sqlMapDAOImpl.queryForList("loadCommon.getContextCacheInsById",param_id);
							if(null != listContextCacheIns && listContextCacheIns.size() > 0){
								param.setListContextCacheIns(listContextCacheIns);
							}
						}
					}
				}
				ContextParamList contextParamList = new ContextParamList();
				contextParamList.setEnd_point_id(end_point_id);
				contextParamList.setContextParamList(listContextParam);//设置参数列表
				if(null != listDyScript && listDyScript.size() > 0){
					contextParamList.setDy_script_content(listDyScript.get(0).getDy_script_content());//设置动态脚本内容
				}
				listContextParamList.add(contextParamList);
			}
		}
		return listContextParamList;
	}

	@Override
	public List<Map<String, Object>> getContConverter() {
		return sqlMapDAOImpl.queryForList("fileExchange.ALL_SQL_CONT_CONVERTER",null);
	}

	@Override
	public Map<String, String> getVersions() {
		Map<String, String> map = new HashMap<String, String>();
		List<Map<String, String>> list = sqlMapDAOImpl.queryForList("loadCommon.getVersionList", null);
		Iterator<Map<String, String>> it = list.iterator();
		while(it.hasNext()){
			Map<String, String> mapTemp = (Map<String, String>) it.next();
			map.put((String)mapTemp.get("MODULE_NAME"), mapTemp.get("VERSION").toString());
		}
		return map;
	}

	@Override
	public List<QuartzTaskContentBean> getQuartzTaskRun() {
		return sqlMapDAOImpl.queryForList("loadCommon.GET_QUARTZ_TASK_LIST",null);
	}

	@Override
	public List<Map<String, String>> getFileExFtpUserHome() {
		return sqlMapDAOImpl.queryForList("fileExchange.getFTPUserHome",null);
	}

	@Override
	public List<String> getIsExistsSerivce() {
		
		Map map = new HashMap();
		return sqlMapDAOImpl.queryForList("fileExchange.isExistsSerivce", map);
	}

	@Override
	public List<DirBean> getFileExDir() {
		return sqlMapDAOImpl.queryForList("fileExchange.getDir",null);
	}

	@Override
	public List<Host> getFileExHost() {
		return sqlMapDAOImpl.queryForList("fileExchange.getHost",null);
	}

	@Override
	public List<RemoteAuth> getFileExRemoteAuth() {
		return sqlMapDAOImpl.queryForList("fileExchange.getRemoteAuth",null);
	}

	@Override
	public List<DirBean> getFileExUserUploadDir() {
		return sqlMapDAOImpl.queryForList("fileExchange.getFTPUserHome",null);
	}

	@Override
	public List<FileMoveRule> getFileExFileMvRule() {
		return sqlMapDAOImpl.queryForList("fileExchange.getFTPUserHome",null);
	}

	@Override
	public List<FileMoveSerInst> getFileExFileMoveSerInst() {
		return sqlMapDAOImpl.queryForList("fileExchange.getFTPUserHome",null);
	}

	@Override
	public List<SerInvokeIns> getFileExSerInvokeInst() {
		return sqlMapDAOImpl.queryForList("fileExchange.getFTPUserHome",null);
	}

	@Override
	public List<Service> getFileExServiceById() {
		return sqlMapDAOImpl.queryForList("fileExchange.getFTPUserHome",null);
	}

	@Override
	public List<ContractVersion> getFileExContractVersionById() {
		return sqlMapDAOImpl.queryForList("fileExchange.getFTPUserHome",null);
	}

	@Override
	public List<Contract> getFileExContractById() {
		return sqlMapDAOImpl.queryForList("fileExchange.getFTPUserHome",null);
	}

	@Override
	public List<FileExchangeContractAdapter> getFileExContractAdapterById() {
		return sqlMapDAOImpl.queryForList("fileExchange.getFTPUserHome",null);
	}

	@Override
	public List<FileExchangeContract2AttrSpec> getFileExContract2AttrSpecByTcpCtrFId() {
		return sqlMapDAOImpl.queryForList("fileExchange.getFTPUserHome",null);
	}

	@Override
	public List<FileExchangeNodeValAdapterReq> getFileExNodeValAdapterReqByContractAdapterId() {
		return sqlMapDAOImpl.queryForList("fileExchange.getFTPUserHome",null);
	}

	@Override
	public List<FileExchangeNodeDescMaper> getFileExNodeDescMaperByContractAdapterId() {
		return sqlMapDAOImpl.queryForList("fileExchange.getFTPUserHome",null);
	}

	@Override
	public List<FileExchangeConstMap> getFileExConstMap() {
		return sqlMapDAOImpl.queryForList("fileExchange.getFTPUserHome",null);
	}

	@Override
	public List<FileExchangeDynParamMap> getFileExDynParamMap() {
		return sqlMapDAOImpl.queryForList("fileExchange.getFTPUserHome",null);
	}

	@Override
	public List<FileExchangeContractFormat> getFileExContractFormatByContractVersionId() {
		return sqlMapDAOImpl.queryForList("fileExchange.getFTPUserHome",null);
	}

	@Override
	public List<FileExchangeContractFormat> getFileExContractFormatByTcpCtrFId() {
		return sqlMapDAOImpl.queryForList("fileExchange.getFTPUserHome",null);
	}

	@Override
	public List<NodeDesc> getFileExNodeDescByContractFormatId() {
		return sqlMapDAOImpl.queryForList("fileExchange.getFTPUserHome",null);
	}

	@Override
	public List<NodeDesc> getFileExNodeDescByNodeDescId() {
		return sqlMapDAOImpl.queryForList("fileExchange.getFTPUserHome",null);
	}

	@Override
	public List<FileExchangeEndpointSpecAttr> getFileExEndpointSpecAttrByEndpointSpecId() {
		return sqlMapDAOImpl.queryForList("fileExchange.getFTPUserHome",null);
	}

	@Override
	public List<AttrSpec> getFileExAttrSpecByAttrSpecId() {
		return sqlMapDAOImpl.queryForList("fileExchange.getFTPUserHome",null);
	}

	@Override
	public List<AttrSpec> getFileExEndpointAttrValueByEndpointId() {
		return sqlMapDAOImpl.queryForList("fileExchange.getFTPUserHome",null);
	}

	@Override
	public List<AttrSpec> getFileExAttrSpecByEndpointAttrValueId() {
		return sqlMapDAOImpl.queryForList("fileExchange.getFTPUserHome",null);
	}

	@Override
	public List<AttrSpec> getFileExAttrSpec() {
		return sqlMapDAOImpl.queryForList("fileExchange.getFTPUserHome",null);
	}

	private String getStatementName(String selectId){
		return MAPPER_NAMESPACE+"."+selectId;
	}

	public List<DirBean> getDir() {
		return (List<DirBean>) sqlMapDAOImpl.queryForList(getStatementName("getDir"), null);
	}

	public List<Host> getHost() {
		return (List<Host>) sqlMapDAOImpl.queryForList(getStatementName("getHost"), null);
	}

	public List<RemoteAuth> getRemoteAuth() {
		return (List<RemoteAuth>) sqlMapDAOImpl.queryForList(getStatementName("getRemoteAuth"), null);
	}

	public List<DirBean> getUserRequestDir() {
		return sqlMapDAOImpl.queryForList(getStatementName("getUserUploadDir"), null) ;
	}

	public List<FileMoveSerInst> getFileMoveSerInst() {
		return (List<FileMoveSerInst>) sqlMapDAOImpl.queryForList(getStatementName("getFileMoveSerInst"), null);
	}

	public List<SerInvokeIns> getSerInvokeInst() {
		return (List<SerInvokeIns>) sqlMapDAOImpl.queryForList(getStatementName("getSerInvokeInst"), null);
	}

	@Override
	public List<Service> selectService() {
		List<Service> obj = (List<Service>) sqlMapDAOImpl.queryForList(getStatementName("selectService"), null);
		if(obj!=null && !obj.isEmpty()){
			List<Service> services = new ArrayList<Service>();
			for(Service item : obj){
				ContractVersion contractVersion = new ContractVersion();
				ContractVersion cv = item.getContractVersion();
				if(cv!=null){
					contractVersion.setBaseContractVersion(cv.getBaseContractVersion());
					contractVersion.setContractId(cv.getContractId());
					contractVersion.setContractVersionId(cv.getContractVersionId());
					contractVersion.setCreateTime(cv.getCreateTime());
					contractVersion.setDescriptor(cv.getDescriptor());
					contractVersion.setEffDate(cv.getEffDate());
					contractVersion.setExpDate(cv.getExpDate());
					contractVersion.setIsNeedCheck(cv.getIsNeedCheck());
					contractVersion.setLastestTime(cv.getLastestTime());
					contractVersion.setReqContractFormat(cv.getReqContractFormat());
					contractVersion.setRspContractFormat(cv.getRspContractFormat());
					contractVersion.setState(cv.getState());
					contractVersion.setVersion(cv.getVersion());
					Contract contract = new Contract();
					Contract c = cv.getContract();
					if(c!=null){
						contract.setBaseContractId(c.getBaseContractId());
						contract.setCode(c.getCode());
						contract.setContractId(c.getContractId());
						contract.setCreateTime(c.getCreateTime());
						contract.setDescriptor(c.getDescriptor());
						contract.setIsBase(c.getIsBase());
						contract.setLastestTime(c.getLastestTime());
						contract.setName(c.getName());
						contract.setState(c.getState());
						contractVersion.setContract(contract);
					}else{
						contractVersion.setContract(null);
					}
					item.setContractVersion(contractVersion);
				}
				services.add(item);
			}
			return services;
		}
		return obj;
	}

	@Override
	public void updateModuleVersion(String moduleName) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("moduleName", moduleName);
		paramMap.put("version", (new java.text.SimpleDateFormat("yyyyMMddHHmmssSSS")).format(new Date()));
		sqlMapDAOImpl.update("loadCommon.updateModuleVersion", paramMap);
	}

	@Override
	public void insertModuleVersion(String moduleName) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("moduleName", moduleName);
		paramMap.put("version", (new java.text.SimpleDateFormat("yyyyMMddHHmmssSSS")).format(new Date()));
		sqlMapDAOImpl.insert("loadCommon.insertModuleVersion", paramMap);
	}

	@Override
	public List<Map<String, Object>> getException() {
		return sqlMapDAOImpl.queryForList("loadCommon.getException",null);
	}

	@Override
	public List<RemoteCallInfo> getRemoteCallInfoList() {
		return sqlMapDAOImpl.queryForList("loadCommon.getRemoteCallInfo",null);
	}
	
	@Override
	public List<FuzzyEncryption> getFuzzyEncryption() {
		return sqlMapDAOImpl.queryForList("loadCommon.getFuzzyEncryption",null);
	}
	
	@Override
	public List<ContractNodeFuzzy> getContractNodeFuzzy() {
		return sqlMapDAOImpl.queryForList("loadCommon.getContractNodeFuzzy",null);
	}

	@Override
	public Map<String, Integer> getTranIdSeqMap() {
		List<Map<String, Object>> list = sqlMapDAOImpl.queryForList("loadCommon.getTranIdSeqList", null);
		if(list==null){
			return null;
		}
		Map<String, Integer> returnMap = new HashMap<String, Integer>();
		Iterator<Map<String, Object>> it = list.iterator();
		while(it.hasNext()){
			Map<String, Object> map = (Map<String, Object>)it.next();
			if(map.get("TRANIDSEQ")!=null){
				returnMap.put(map.get("LOCALSEQ").toString() + map.get("COMPONENT_CODE").toString(), Integer.valueOf(map.get("TRANIDSEQ").toString()));
			}else{
				returnMap.put(map.get("LOCALSEQ").toString() + map.get("COMPONENT_CODE").toString(), null);
			}
		}
		return returnMap;
	}

	@Override
	public void insertServerComponentSeq(Map<String, String> paramMap) {
		sqlMapDAOImpl.insert("loadCommon.insertServerComponentSeq", paramMap);
	}
	
	@Override
	public void updateServerComponentSeq(Map<String, String> paramMap) {
		sqlMapDAOImpl.update("loadCommon.updateServerComponentSeq", paramMap);
	}
	
	@Override
	public Map<String, String> getServerLocalLogoMap() {
		List<Map<String, Object>> list = sqlMapDAOImpl.queryForList("loadCommon.getServerLocalLogoList", null);
		if(list==null){
			return null;
		}
		Map<String, String> returnMap = new HashMap<String, String>();
		Iterator<Map<String, Object>> it = list.iterator();
		while(it.hasNext()){
			Map<String, Object> map = (Map<String, Object>)it.next();
			returnMap.put(map.get("IPPORT").toString(), map.get("LOCALSEQ").toString());
		}
		return returnMap;
	}

	@Override
	public void insertServerLocalLogo(Map<String, String> paramMap) {
		sqlMapDAOImpl.insert("loadCommon.insertServerLocalLogo", paramMap);
	}

	@Override
	public void insertServerLocalSeq(int seq) {
		sqlMapDAOImpl.insert("loadCommon.insertServerLocalSeq", seq);
	}

	@Override
	public void updateServerLocalSeq(int seq) {
		sqlMapDAOImpl.update("loadCommon.updateServerLocalSeq", seq);
	}

	@Override
	public int getServerLocalSeq() {
		Object seq = sqlMapDAOImpl.queryForObject("loadCommon.getServerLocalSeq", null);
		return seq==null?-1:((Integer)seq);
	}
	
	@Override
	public List<AttrValue> getAttrValue(){
		return sqlMapDAOImpl.queryForList("loadCommon.getAttrValue",null);
	}

	@Override
	public List<ConfProperties> getConfProperties() {
		return sqlMapDAOImpl.queryForList("loadCommon.getConfProperties",null);
	}

	@Override
	public List<LogLevel> getLogLevel(Object object) {
		List<Map<String, Object>> logLevelList = sqlMapDAOImpl.queryForList("loadCommon.getLogLevels", null);
		LogLevel logLevel = null;
		List<LogLevel> logLevels = new ArrayList<LogLevel>();
		for(Map<String, Object> map: logLevelList) {
			logLevel = new LogLevel();
			String logOutObject = (String)map.get("LOG_OUT_OBJECT");
			if(StringUtils.hasText(logOutObject)){
				logLevel.setLogOutObjects(Arrays.asList(logOutObject.split(",")));
			}
			else {
				logLevel.setLogOutObjects(new ArrayList<String>());
			}
			logLevel.setLogLevel((String)map.get("LOG_LEVEL"));
			logLevel.setRunningStatus((String)map.get("RUN_STATUS"));
			logLevels.add(logLevel);
		}
		return logLevels;
	}
	
	@Override
	public List<Api> getNeedUserAuthApi() {
		List<Map<String,String>> resultMap = sqlMapDAOImpl.queryForList("loadCommon.getNeedUserAuthApi",null);
		List<Api> resultList = new ArrayList<Api>();
		if (resultMap!=null) {
			for (Map<String, String> map : resultMap) {
				Api api = new Api();
				api.setApiName(map.get("API_NAME"));
				api.setApiMethod(map.get("API_METHOD"));
				api.setApiId(ObjtoInt(map.get("API_ID")));
				api.setServiceId(ObjtoInt(map.get("SERVICE_ID")));
				resultList.add(api);
			}			
		}	
		return resultList;
	}
	
	@Override
	public List<Template> getTemplate() {
		List<Template> list = sqlMapDAOImpl.queryForList("loadCommon.getTemplate",null);
		
		return list;
	}

	@Override
	public List<CacheStrategy> getCacheStragety(String tenantId) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("tenantId", tenantId);
		List<Map<String,Object>> CacheStrategyListDB = sqlMapDAOImpl.queryForList("loadCommon.getCacheStrategys", paramMap);
		
		List<CacheStrategy> cacheStrategys = new ArrayList<CacheStrategy>();
		for(Map<String, Object> map: CacheStrategyListDB) {
			//缓存策略
			CacheStrategy cacheStrategy = new CacheStrategy();
			cacheStrategy.setId(map.get("ID")==null?null:Integer.parseInt(map.get("ID").toString()));
			cacheStrategy.setCode(map.get("CODE")+"");
			cacheStrategy.setName(map.get("NAME")+"");
			cacheStrategy.setCacheType(map.get("CACHE_TYPE")+"");
			cacheStrategy.setState(map.get("STATE")+"");
			cacheStrategy.setForceRefresh(map.get("FORCE_REFRESH")+"");
			cacheStrategy.setExpireTime(map.get("EXPIRE_TIME")==null?null:Integer.parseInt(map.get("EXPIRE_TIME").toString()));
			cacheStrategy.setExpireTimePath(map.get("EXPIRE_TIME_PATH")+"");
			cacheStrategy.setTenantId(map.get("TEANANT_ID")==null?null:Integer.parseInt(map.get("TEANANT_ID").toString()));
			
			//相关联的缓存对象
			Map<String, String> paramMapCacheObj = new HashMap<String, String>();
			paramMapCacheObj.put("cacheStrategyId", cacheStrategy.getId().toString());
			List<Map<String,Object>> cacheObjListDB = sqlMapDAOImpl.queryForList("loadCommon.getCacheObjs", paramMapCacheObj);
			List<CacheObj> cacheObjsReturn = new ArrayList<CacheObj>();
			
			for(Map<String,Object> mapCacheObj: cacheObjListDB) {
				CacheObj cacheObj = new CacheObj();
				cacheObj.setId(mapCacheObj.get("ID")==null?null:Integer.parseInt(mapCacheObj.get("ID").toString()));
				cacheObj.setCode(mapCacheObj.get("CODE")+"");
				cacheObj.setKeyRule(mapCacheObj.get("KEY_RULE")+"");
				cacheObj.setState(mapCacheObj.get("STATE")+"");
				cacheObj.setValuePath(mapCacheObj.get("VALUE_PATH")+"");
				cacheObj.setCacheStrategyId(mapCacheObj.get("CACHE_STRATEGY_ID")==null?null:Integer.parseInt(mapCacheObj.get("CACHE_STRATEGY_ID").toString()));
				cacheObj.setTenantId(mapCacheObj.get("TENANT_ID")==null?null:Integer.parseInt(mapCacheObj.get("TENANT_ID").toString()));
				cacheObjsReturn.add(cacheObj);
			}
			cacheStrategy.setCacheObjs(cacheObjsReturn);
			
			cacheStrategys.add(cacheStrategy);
		}
		return cacheStrategys;
	}

}