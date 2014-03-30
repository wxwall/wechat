package cn.newtouch.inf.model.outmsg;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * 返回消息基础类，处理场景不一样，故不用MsgModel类
 * @author wuxw
 *
 */
@XStreamAlias("xml")
public class ReturnMsgModel {
	
	/** 被请求用户名 */
	@XStreamAlias("ToUserName")
	private String toUserName;
	/** 请求用户名 */
	@XStreamAlias("FromUserName") 
	private String fromUserName;
	/** 创建时间  */
	@XStreamAlias("CreateTime")
	private String createTime;
	/** 消息类型 */
	@XStreamAlias("MsgType")
	private String msgType;
	/** 消息编号 */
	@XStreamAlias("MsgId")
	private String msgId;
	
	
	
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result
				+ ((fromUserName == null) ? 0 : fromUserName.hashCode());
		result = prime * result + ((msgId == null) ? 0 : msgId.hashCode());
		result = prime * result + ((msgType == null) ? 0 : msgType.hashCode());
		result = prime * result
				+ ((toUserName == null) ? 0 : toUserName.hashCode());
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReturnMsgModel other = (ReturnMsgModel) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (fromUserName == null) {
			if (other.fromUserName != null)
				return false;
		} else if (!fromUserName.equals(other.fromUserName))
			return false;
		if (msgId == null) {
			if (other.msgId != null)
				return false;
		} else if (!msgId.equals(other.msgId))
			return false;
		if (msgType == null) {
			if (other.msgType != null)
				return false;
		} else if (!msgType.equals(other.msgType))
			return false;
		if (toUserName == null) {
			if (other.toUserName != null)
				return false;
		} else if (!toUserName.equals(other.toUserName))
			return false;
		return true;
	}
	
	
}
