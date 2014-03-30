package cn.newtouch.inf.service;

import java.util.Map;

import org.apache.log4j.Logger;

import cn.newtouch.inf.util.Exception.WeChatException;

public class WeChatMng {
	
	static Logger logger = Logger.getLogger(WeChatMng.class);
	
	public WeChatService weChatService;
	
	private Map<String,WeChatService> map;
	

	public void setMap(Map<String, WeChatService> map) {
		this.map = map;
	}


	/**
	 * 
	 * @param msgType
	 * @param wxMsgXml
	 * @return
	 */
	public String process(String msgType,String wxMsgXml) throws WeChatException{
		if(msgType == null || msgType == ""){
			logger.error("������Ϣ��" + msgType);
			throw new WeChatException("������ϢΪ�գ�");
		}
		weChatService = map.get(msgType);
		if(null == weChatService){
			throw new WeChatException("��֧�ֵĽӿ����ͣ�" + msgType);
		}
		return weChatService.process(wxMsgXml);
	}
}
