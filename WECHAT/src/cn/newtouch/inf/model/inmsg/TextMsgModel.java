package cn.newtouch.inf.model.inmsg;

import org.xml.sax.SAXException;


/**
 * �ı���Ϣʵ����
 * 
 * @author wuxw
 * 
 */
public class TextMsgModel extends MsgModel {

	/** �ı���Ϣ */
	private String content;

	/** ��ϢID */
	private String msgId;

	
	
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	/** ��ʱ�ֶ� */
	private String tempStr = null;

	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		if ("ToUserName".equals(name)) {
			this.setToUserName(tempStr);
		} else if ("FromUserName".equals(name)) {
			this.setFromUserName(tempStr);
		} else if ("CreateTime".equals(name)) {
			this.setCreateTime(tempStr);
		} else if ("MsgType".equals(name)) {
			this.setMsgType(tempStr);
		} else if ("Content".equals(name)) {
			this.setContent(tempStr);
		} else if ("MsgId".equals(name)) {
			this.setMsgId(tempStr);
		}

		tempStr = "";
	}

	public void characters(char[] ch, int offset, int length)
			throws SAXException {
		tempStr = new String(ch, offset, length);
	}

	

}
