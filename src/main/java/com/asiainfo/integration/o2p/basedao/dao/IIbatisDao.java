package com.asiainfo.integration.o2p.basedao.dao;

import java.util.List;
import java.util.Map;

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
import com.ailk.eaap.op2.bo.BizFunction;
import com.ailk.eaap.op2.bo.CacheStrategy;
import com.ailk.eaap.op2.bo.Component;
import com.ailk.eaap.op2.bo.ConfProperties;
import com.ailk.eaap.op2.bo.ContextParamList;
import com.ailk.eaap.op2.bo.Contract;
import com.ailk.eaap.op2.bo.ContractNodeFuzzy;
import com.ailk.eaap.op2.bo.ContractVersion;
import com.ailk.eaap.op2.bo.CtlCounterms2Comp;
import com.ailk.eaap.op2.bo.Endpoint;
import com.ailk.eaap.op2.bo.FuzzyEncryption;
import com.ailk.eaap.op2.bo.HideMethod;
import com.ailk.eaap.op2.bo.LogLevel;
import com.ailk.eaap.op2.bo.MainData;
import com.ailk.eaap.op2.bo.NodeDesc;
import com.ailk.eaap.op2.bo.Org;
import com.ailk.eaap.op2.bo.ProofEffect;
import com.ailk.eaap.op2.bo.QuartzTaskContentBean;
import com.ailk.eaap.op2.bo.RegisterObject;
import com.ailk.eaap.op2.bo.RemoteCallInfo;
import com.ailk.eaap.op2.bo.SerInvokeIns;
import com.ailk.eaap.op2.bo.Service;
import com.ailk.eaap.op2.bo.TaskBase;
import com.ailk.eaap.op2.bo.TechImpl;
import com.ailk.eaap.op2.bo.Template;
import com.ailk.eaap.op2.bo.WhiteList;

public interface IIbatisDao {
	
	public Map<String, Integer> getTranIdSeqMap();
	public void insertServerComponentSeq(Map<String, String> paramMap);
	public void updateServerComponentSeq(Map<String, String> paramMap);
	public Map<String, String> getServerLocalLogoMap();
	public void insertServerLocalLogo(Map<String, String> paramMap);
	public void insertServerLocalSeq(int seq);
	public void updateServerLocalSeq(int seq);
	public int getServerLocalSeq();
	
	public Map<String,Object> loadDataSourceRoute();
	public Map<String,BizFunction> getBusCode(String code);
	public List<ContractVersion>  getContractVer(String version);
	public Map<String,Org> getOrg(String orgCode);
	//public Map<String,Component> getComponent(Org org);
	public Map<String,Component> getComponent(String code);
	public Map<String, List<MainData>> getMainData();
	public List<Api> getApi(Integer apiid);
	
	public Map<String ,ProofEffect> getProofValues(Integer servInvokeId);
	public List<Service> getService(Integer serviceId);
	public List<SerInvokeIns> getSerInvokeIns(Integer servInvokeId);
	public List<AppAccToken> getAppAccToken(int begin,int end);
	public List<CtlCounterms2Comp> getFlow();
	public List<App> getApp(Integer appId);
	public List<AppApiList> getAppList(int appId);	
	
	public List<AppUserInfo> getUserInfo();
	
	public List<TechImpl> getTechImpl();
	
	public List<Auth> getAuth(Integer SerInvokeInsId);
	
	public List<TaskBase> getTaskBase();
	
	public List<BizFunction> getBusCodeList(String code);

	public List<Component> getComponentList(String code);

	public List<Org> getOrgList(String orgCode);
	public int getAppAccTokenCount();
	
	public List<Map<String, Object>> getWsdlFileList();
	
	public List<Map<String, Object>> getApiOperationList();
	
	public List<Map<String, Object>> getHeadNodeDescList();
	
	public List<Map<String, Object>> getContractOperationList(Map<String, String> paramMap);
	
	public List<Map<String, Object>> getRestConfigList(Map<String, String> param);
	/**
	 * 得到所有模糊注册表信息
	 * @return
	 */
	public List<RegisterObject> getAllRegTable();
	/**
	 * 得到对应用表的模糊配置
	 * @param register_object_id
	 * @return
	 */
	public Map<String, HideMethod> getAllHideProcessById(
			String register_object_id);
	/**
	 * 获得所有自定义端点的配置信息
	 * @return
	 */
	public List<ContextParamList> getAllAutoEndPoints();
	
	public List<String> getWhiteList(WhiteList whiteList);
	
	
	public Map<String, String> getVersions();
	
	public List<QuartzTaskContentBean> getQuartzTaskRun();
	
	//文件交换部分
	public List<Map<String, Object>> getContConverter();
	public List<Map<String,String>> getFileExFtpUserHome();
	public List<String> getIsExistsSerivce();
	public List<DirBean> getFileExDir();
	public List<Host> getFileExHost();
	public List<RemoteAuth> getFileExRemoteAuth();
	public List<DirBean> getFileExUserUploadDir();
	public List<FileMoveRule> getFileExFileMvRule();
	public List<FileMoveSerInst> getFileExFileMoveSerInst();
	public List<SerInvokeIns> getFileExSerInvokeInst();
	public List<Service> getFileExServiceById();
	public List<ContractVersion> getFileExContractVersionById();
	public List<Contract> getFileExContractById();
	public List<FileExchangeContractAdapter> getFileExContractAdapterById();
	public List<FileExchangeContract2AttrSpec> getFileExContract2AttrSpecByTcpCtrFId();
	public List<FileExchangeNodeValAdapterReq> getFileExNodeValAdapterReqByContractAdapterId();
	public List<FileExchangeNodeDescMaper> getFileExNodeDescMaperByContractAdapterId();
	public List<FileExchangeConstMap> getFileExConstMap();
	public List<FileExchangeDynParamMap> getFileExDynParamMap();
	public List<FileExchangeContractFormat> getFileExContractFormatByContractVersionId();
	public List<FileExchangeContractFormat> getFileExContractFormatByTcpCtrFId();
	public List<NodeDesc> getFileExNodeDescByContractFormatId();
	public List<NodeDesc> getFileExNodeDescByNodeDescId();
	public List<FileExchangeEndpointSpecAttr> getFileExEndpointSpecAttrByEndpointSpecId();
	public List<AttrSpec> getFileExAttrSpecByAttrSpecId();
	public List<AttrSpec> getFileExEndpointAttrValueByEndpointId();
	public List<AttrSpec> getFileExAttrSpecByEndpointAttrValueId();
	public List<AttrSpec> getFileExAttrSpec();
	
	public static final String MAPPER_NAMESPACE = "fileExchange";
//	public List<Map<String,String>> getFTPUserHome(HashMap<String,Object> param);
	public List<DirBean> getDir();
	public List<Host> getHost();
	public List<RemoteAuth> getRemoteAuth();
	public List<DirBean> getUserRequestDir();
//	public FileMoveRule getFileMvRuleBySerInst(Integer serInvokeInstId);
	public List<FileMoveSerInst> getFileMoveSerInst();
//	public void insertFileExFailDispatch(FileExFailDispatch fileExFailDispatch);
//	public void updateFileExFailDispatch(FileExFailDispatch fileExFailDispatch);
//	public void delFileExFailDispatch(Long id);
//	public void insertFileExFailDispatchHis(FileExFailDispatch fileExFailDispatch);
//	public List<FileExFailDispatch> selectFileExFailDispatch(FileExFailDispatch fileExFailDispatch);
	public List<SerInvokeIns> getSerInvokeInst();
	public List<Service> selectService();
	public void updateModuleVersion(String moduleName);
	public void insertModuleVersion(String moduleName);
	public List<Map<String, Object>> getException();
	public List<RemoteCallInfo> getRemoteCallInfoList();
	public List<FuzzyEncryption> getFuzzyEncryption();
	public List<ContractNodeFuzzy> getContractNodeFuzzy();
	
	public Endpoint getEndpoint(int endpointid);
	public List<AttrValue> getAttrValue();
	public List<ConfProperties> getConfProperties();
	public List<LogLevel> getLogLevel(Object object);
	public List<Api> getNeedUserAuthApi();
	public List<CacheStrategy> getCacheStragety(String tenantId);
	List<Template> getTemplate();

}
