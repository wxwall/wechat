package cn.newtouch.inf.service.impl;

import org.apache.log4j.Logger;

import cn.newtouch.inf.model.inmsg.TextMsgModel;
import cn.newtouch.inf.model.outmsg.TextReturnMsgModel;
import cn.newtouch.inf.service.WeChatServiceAbs;
import cn.newtouch.inf.util.Exception.WeChatException;
import cn.newtouch.inf.util.msgTool.SAXMsgTool;

public class TextReceviService extends WeChatServiceAbs<TextReturnMsgModel,TextMsgModel> {
	
	static Logger logger = Logger.getLogger(TextReceviService.class);

	public TextMsgModel reviceMsg(String xml) throws WeChatException {
		//������sax�������ߣ������ʹ��������������xml����д��������������
		//��������û�ýӿڵ���ʽ���Ժ���������󣬸ĳɽӿ���ʽ
		return (TextMsgModel) SAXMsgTool.xmlToObj(xml);
	}
	

	public TextReturnMsgModel processMsg(TextMsgModel msg) throws WeChatException {
		TextReturnMsgModel text = new TextReturnMsgModel();
		text.setCreateTime(msg.getCreateTime());
		text.setFromUserName(msg.getToUserName());//���ص���ϢToUserName�����FromUserName
		text.setToUserName(msg.getFromUserName());//���ص���ϢFromUserName�����ToUserName
		text.setMsgType(msg.getMsgType());
		text.setContent(msg.getContent());
		return text;
	}


}
