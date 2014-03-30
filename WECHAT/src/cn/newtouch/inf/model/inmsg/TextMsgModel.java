package cn.newtouch.inf.model.inmsg;

import org.xml.sax.SAXException;


/**
 * 文本消息实体类
 * 
 * @author wuxw
 * 
 */
public class TextMsgModel extends MsgModel {

	/** 文本消息 */
	private String content;

	/** 消息ID */
	private String msgId;

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
	/** 临时字段 */
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((msgId == null) ? 0 : msgId.hashCode());
		result = prime * result + ((tempStr == null) ? 0 : tempStr.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TextMsgModel other = (TextMsgModel) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (msgId == null) {
			if (other.msgId != null)
				return false;
		} else if (!msgId.equals(other.msgId))
			return false;
		if (tempStr == null) {
			if (other.tempStr != null)
				return false;
		} else if (!tempStr.equals(other.tempStr))
			return false;
		return true;
	}
	
	

}
