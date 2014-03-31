package cn.newtouch.inf.service;

import cn.newtouch.inf.model.inmsg.MsgModel;
import cn.newtouch.inf.model.outmsg.ReturnMsgModel;
import cn.newtouch.inf.util.Exception.WeChatException;
import cn.newtouch.inf.util.msgTool.SAXMsgTool;

/**
 * ���������ģ��
 * ��Ϣ�Ĵ������̷��������ν��У�
 * 		1. ������Ϣ
 * 		2. ������Ϣ
 * 		3. ������Ϣ
 * �������˳���ܱ�,ǿ��ʹ�ô�����Ϣ������
 * ����ֻ��Ҫ����ҵ����ķ�ʽ
 * @author wuxw
 *
 */
public abstract class WeChatServiceAbs<T extends ReturnMsgModel,K extends MsgModel> implements WeChatService<T,K> {
	
	
	

	/**
	 * ������Ϣ����ģ�����ʽ�Ը�ģ�����
	 * @param xmlMsg	������Ϣ
	 * @return			���ش�������Ϣ
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
