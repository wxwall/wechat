package cn.newtouch.inf.util.msgTool;

import java.io.ByteArrayInputStream;
import java.io.Writer;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;

import cn.newtouch.inf.model.inmsg.MsgModel;
import cn.newtouch.inf.model.inmsg.TextMsgModel;
import cn.newtouch.inf.util.Exception.WeChatException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

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
	public static MsgModel xmlToObj(String xml) throws WeChatException {
		SAXParserFactory spfactory = SAXParserFactory.newInstance();
		// 生成SAX解析对象
		SAXParser parser;
		TextMsgModel msgModel = new TextMsgModel();
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
	 * 添加XStream支持CDATA
	 * 
	 * @return
	 */
	public static XStream getXStreamInstance() {
		return new XStream(new XppDriver() {
	        public HierarchicalStreamWriter createWriter(Writer out) {
	            return new PrettyPrintWriter(out) {
	            	
	            	private String nodeName;
	                @Override
					public void startNode(String name) {
	                	nodeName = name;
						super.startNode(name);
					}

					protected void writeText(QuickWriter writer, String text) {
						if(nodeName.equals("CreateTime")) {//MsgId没有CDATA的写法，所以这里处理了
							writer.write(text);
						}else{
							writer.write("<![CDATA[");
							writer.write(text);
							writer.write("]]>");
						}
	                }
	            };
	        }
	    });
	}
}


