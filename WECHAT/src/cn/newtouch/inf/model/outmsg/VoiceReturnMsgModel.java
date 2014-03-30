package cn.newtouch.inf.model.outmsg;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class VoiceReturnMsgModel extends ReturnMsgModel {
	
	@XStreamAlias("content")
	private String content;

	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
