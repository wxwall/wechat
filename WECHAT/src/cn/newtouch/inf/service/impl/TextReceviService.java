package cn.newtouch.inf.service.impl;

import org.apache.log4j.Logger;

import cn.newtouch.inf.model.inmsg.MsgModel;
import cn.newtouch.inf.model.outmsg.TextReturnMsgModel;
import cn.newtouch.inf.service.WeChatServiceAbs;
import cn.newtouch.inf.util.Exception.WeChatException;
import cn.newtouch.inf.util.msgTool.SAXMsgTool;

public class TextReceviService<TextMsgModel extends MsgModel> extends WeChatServiceAbs<TextReturnMsgModel,TextMsgModel> {
	
	static Logger logger = Logger.getLogger(TextReceviService.class);

	public TextMsgModel reviceMsg(String xml) throws WeChatException {
		//这里用sax解析工具，如果想使用其它方法解析xml，重写这个方法便可以了
		//解析方法没用接口的形式，以后如果有需求，改成接口形式
		return (TextMsgModel) SAXMsgTool.xmlToObj(xml,TextMsgModel.class);
	}
	
	


	public TextReturnMsgModel processMsg(TextMsgModel msg) throws WeChatException {
		TextReturnMsgModel text = new TextReturnMsgModel();
		text.setCreateTime(msg.getCreateTime());
		text.setFromUserName(msg.getFromUserName());
		text.setToUserName(msg.getToUserName());
		return text;
	}


}
