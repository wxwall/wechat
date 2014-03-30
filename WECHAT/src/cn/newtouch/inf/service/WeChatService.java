package cn.newtouch.inf.service;

import cn.newtouch.inf.model.inmsg.MsgModel;
import cn.newtouch.inf.model.outmsg.ReturnMsgModel;
import cn.newtouch.inf.util.Exception.WeChatException;

public interface WeChatService<T extends ReturnMsgModel,K extends MsgModel> {
	
	/**
	 * 接受客户端发送的消息
	 * @param xml	消息格式为xml
	 * @return		返回处理完成的xml对应成的实体对象
	 * @throws WeChatException
	 */
	public K reviceMsg(String xml) throws WeChatException;
	
	/**
	 * 处理消息
	 */
	public  T processMsg(K msg) throws WeChatException;
	
	/**
	 * 返回处理后的消息
	 * @return	消息实体类
	 */
	public  String returnMsg(T obj) throws WeChatException;
	

	public String process(String xmlMsg)  throws WeChatException;
	
}
