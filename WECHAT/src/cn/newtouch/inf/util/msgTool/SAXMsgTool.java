package cn.newtouch.inf.util.msgTool;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;

import cn.newtouch.inf.model.inmsg.MsgModel;
import cn.newtouch.inf.model.inmsg.TextMsgModel;
import cn.newtouch.inf.util.Exception.WeChatException;

import com.thoughtworks.xstream.XStream;

/**
 * 消息处理工具类
 * 
 * @author wuxw
 * 
 */
public class SAXMsgTool<K extends MsgModel> {

	static Logger logger = Logger.getLogger(SAXMsgTool.class);

	public static XStream xstream = null;


	/**
	 * 将xml字符串转成对应的对象类型
	 * 
	 * @param xml
	 * @return
	 * @throws WeChatException
	 */
	public static MsgModel xmlToObj(String xml,Class<MsgModel> msg) throws WeChatException {
		SAXParserFactory spfactory = SAXParserFactory.newInstance();
		// 生成SAX解析对象
		SAXParser parser;
		MsgModel msgModel = msg.getClass();
		try {
			parser = spfactory.newSAXParser();
			// 指定XML文件，进行XML解析
			parser.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")), msgModel);
		} catch (Exception e) {
			logger.error("解析xml错误:" + e);
			throw new WeChatException("解析传入xml消息错误!");
		}
		return msgModel;
	}

	/**
	 * 将对象转成xml字符串 用XStream做转换器
	 * 
	 * @param obj
	 * @return
	 */
	public static String toXmlString(Object obj) {
		xstream = getXStreamInstance();
		// 处理注解，让xml字符串按照所特定的格式输出
		xstream.processAnnotations(obj.getClass());
		return xstream.toXML(obj);
	}

	/**
	 * 减少实例化对象，提升性能
	 * 
	 * @return
	 */
	public static XStream getXStreamInstance() {
		if (xstream == null)
			return new XStream();
		return xstream;
	}
}
