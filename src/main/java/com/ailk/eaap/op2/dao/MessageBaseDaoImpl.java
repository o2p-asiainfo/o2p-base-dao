package com.ailk.eaap.op2.dao;

import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.bo.message.Message;
import com.ailk.eaap.op2.bo.message.MessageRecipientRel;
import com.linkage.rainbow.dao.SqlMapDAO;

public class MessageBaseDaoImpl implements MessageBaseDao{

	private SqlMapDAO sqlMapDao;
	public SqlMapDAO getSqlMapDao() {
		return sqlMapDao;
	}
	public void setSqlMapDao(SqlMapDAO sqlMapDao) {
		this.sqlMapDao = sqlMapDao;
	}
	
	public String insertMes(Message msg) {
		return sqlMapDao.insert("messageBase.addMessage", msg).toString();
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> queryMessageList(Message msg) {
		return sqlMapDao.queryForList("messageBase.selectMessage", msg);
	}
	
	public Integer countMessageList(Message msg){
		return (Integer) sqlMapDao.queryForObject("messageBase.countMessage", msg);
	}
	
	public Integer updateMsg(Message msg) {
		return sqlMapDao.update("messageBase.updateMessage", msg);
	}

	public Message getMsgById(Message msg){
		return (Message) sqlMapDao.queryForObject("messageBase.selectMessageById", msg);
	}

	public String insertMsgRecRel(MessageRecipientRel msgRecRel){
		return sqlMapDao.insert("messageBase.addMsgRecRel", msgRecRel).toString();
	}
	
	@SuppressWarnings("unchecked")
	public List<MessageRecipientRel> queryMessageRecipientRelList(MessageRecipientRel msgRecRel){
		return sqlMapDao.queryForList("messageBase.selectMsgRecRel", msgRecRel);
	}
	
	public Integer countMessageRecipientRelList(MessageRecipientRel msgRecRel){
		return (Integer) sqlMapDao.queryForObject("messageBase.countMsgRecRel", msgRecRel);
	}
	
	public Integer updateMessageRecipientRel(MessageRecipientRel msgRecRel){
		return sqlMapDao.update("messageBase.updateMsgRecRel", msgRecRel);
	}
	
	public Integer updateMsgRecRelByMsgId(MessageRecipientRel msgRecRel){
		return sqlMapDao.update("messageBase.updateMsgRecRelByMsgId", msgRecRel);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> getRoleList(){
		return sqlMapDao.queryForList("messageBase.selectRole", null);
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getOrgList(Map<String,Object> m){
		return sqlMapDao.queryForList("messageBase.selectORG", m);
	}
	public Integer cntOrgList(Map<String,Object> m){
		return (Integer) sqlMapDao.queryForObject("messageBase.cntORG", m);
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> selectOrgName(Map<String,Object> p){
		return sqlMapDao.queryForList("messageBase.selectOrgName", p);
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> selectMessageByData(Map<String,Object> map){
		return sqlMapDao.queryForList("messageBase.selectMessageByData", map);
	}
	public Integer countMessageByData(Map<String,Object> map){
		return (Integer) sqlMapDao.queryForObject("messageBase.countMessageByData", map);
	}
}
