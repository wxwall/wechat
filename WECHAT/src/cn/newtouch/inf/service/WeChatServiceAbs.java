package cn.newtouch.inf.service;

import cn.newtouch.inf.model.inmsg.MsgModel;
import cn.newtouch.inf.model.outmsg.ReturnMsgModel;
import cn.newtouch.inf.util.Exception.WeChatException;
import cn.newtouch.inf.util.msgTool.SAXMsgTool;

/**
 * 抽象出功能模块
 * 信息的处理流程分三步依次进行：
 * 		1. 接收信息
 * 		2. 处理信息
 * 		3. 返回信息
 * 并且这个顺序不能变,强制使用处理信息的流程
 * 我们只需要关心业务处理的方式
 * @author wuxw
 *
 */
public abstract class WeChatServiceAbs<T extends ReturnMsgModel,K extends MsgModel> implements WeChatService<T,K> {
	
	
	

	/**
	 * 处理消息，用模版的形式对各模块独立
	 * @param xmlMsg	传入消息
	 * @return			返回处理后的消息
	 * @throws WeChatException
	 */
	public String process(String xmlMsg) throws WeChatException{
		K msg = reviceMsg(xmlMsg);
		return returnMsg(processMsg(msg));
	}
	
	
	public String returnMsg(T obj) throws WeChatException {
		return SAXMsgTool.toXmlString(obj);
	}
}
