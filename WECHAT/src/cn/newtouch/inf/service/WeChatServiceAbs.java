package cn.newtouch.inf.service;

import cn.newtouch.inf.model.inmsg.MsgModel;
import cn.newtouch.inf.model.outmsg.ReturnMsgModel;
import cn.newtouch.inf.util.Exception.WeChatException;

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
	
	
	public String returnMsg(ReturnMsgModel obj) throws WeChatException {
		String xml = "<xml>" +
		"<ToUserName><![CDATA["+obj.getToUserName()+"]]></ToUserName>" +
		"<FromUserName><![CDATA["+obj.getFromUserName()+"]]></FromUserName>" +
		"<CreateTime>"+obj.getCreateTime()+"</CreateTime>" +
		"<MsgType><![CDATA["+obj.getMsgId()+"]]></MsgType>" +
		"<Content><![CDATA[���]]></Content>" +
		"</xml>";
		return xml;
		//return SAXMsgTool.toXmlString(obj);
	}
}
