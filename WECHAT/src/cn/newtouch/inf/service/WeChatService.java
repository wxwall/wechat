package cn.newtouch.inf.service;

import cn.newtouch.inf.model.inmsg.MsgModel;
import cn.newtouch.inf.model.outmsg.ReturnMsgModel;
import cn.newtouch.inf.util.Exception.WeChatException;

public interface WeChatService<T extends ReturnMsgModel,K extends MsgModel> {
	
	/**
	 * ���ܿͻ��˷��͵���Ϣ
	 * @param xml	��Ϣ��ʽΪxml
	 * @return		���ش�����ɵ�xml��Ӧ�ɵ�ʵ�����
	 * @throws WeChatException
	 */
	public K reviceMsg(String xml) throws WeChatException;
	
	/**
	 * ������Ϣ
	 */
	public  T processMsg(K msg) throws WeChatException;
	
	/**
	 * ���ش�������Ϣ
	 * @return	��Ϣʵ����
	 */
	public  String returnMsg(T obj) throws WeChatException;
	

	public String process(String xmlMsg)  throws WeChatException;
	
}
