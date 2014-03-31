package cn.newtouch.inf.model.outmsg;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class TextReturnMsgModel extends ReturnMsgModel {
	
	
	@XStreamAlias("Content")
	private String content;

	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
