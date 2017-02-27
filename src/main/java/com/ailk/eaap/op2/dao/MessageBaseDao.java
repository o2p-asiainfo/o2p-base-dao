package com.ailk.eaap.op2.dao;

import java.util.List;
import java.util.Map;

import com.ailk.eaap.op2.bo.message.Message;
import com.ailk.eaap.op2.bo.message.MessageRecipientRel;

public interface MessageBaseDao {

	/**
	 * 添加消息
	 * @param msg 消息实体
	 * @return 添加的消息实体ID
	 */
	public String insertMes(Message msg);
	/**
	 * 查询消息
	 * @param msg  查询消息的信息
	 * @return 消息列表 List
	 */
	public List<Message> queryMessageList(Message msg);
	/**
	 * 计数查询总数
	 * @param msg 查询消息的信息
	 * @return 总数
	 */
	public Integer countMessageList(Message msg);
	/**
	 * 更新消息
	 * @param msg 更新的消息信息
	 * @return 数据库变更数
	 */
	public Integer updateMsg(Message msg); 
	/**
	 * 根据Id查找消息
	 * @param msg
	 * @return
	 */
	public Message getMsgById(Message msg);
	/**
	 * 增加消息接收人
	 * @param msgRecRel 
	 * @return
	 */
	public String insertMsgRecRel(MessageRecipientRel msgRecRel);
	/**
	 * 查询消息接收人
	 * @param msgRecRel
	 * @return
	 */
	public List<MessageRecipientRel> queryMessageRecipientRelList(MessageRecipientRel msgRecRel);
	/**
	 * 计数消息接收人
	 * @param msgRecRel
	 * @return
	 */
	public Integer countMessageRecipientRelList(MessageRecipientRel msgRecRel);
	/**
	 * 更新消息接收人
	 * @param msgRecRel
	 * @return
	 */
	public Integer updateMessageRecipientRel(MessageRecipientRel msgRecRel);
	public Integer updateMsgRecRelByMsgId(MessageRecipientRel msgRecRel);
	/**
	 * 查找角色信息
	 * @return
	 */
	public List<Map<String,String>> getRoleList();
	public List<Map<String,Object>> getOrgList(Map<String,Object> m);
	public Integer cntOrgList(Map<String,Object> m);
	public List<Map<String,String>> selectOrgName(Map<String,Object> p);
	public List<Message> selectMessageByData(Map<String,Object> map);
	public Integer countMessageByData(Map<String,Object> map);
}
