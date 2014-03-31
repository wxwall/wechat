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
 * ��Ϣ��������
 * 
 * @author wuxw
 * 
 */
public class SAXMsgTool<K extends MsgModel> {

	static Logger logger = Logger.getLogger(SAXMsgTool.class);

	public static XStream xstream = null;


	/**
	 * ��xml�ַ���ת�ɶ�Ӧ�Ķ�������
	 * 
	 * @param xml
	 * @return
	 * @throws WeChatException
	 */
	public static MsgModel xmlToObj(String xml) throws WeChatException {
		SAXParserFactory spfactory = SAXParserFactory.newInstance();
		// ����SAX��������
		SAXParser parser;
		TextMsgModel msgModel = new TextMsgModel();
		try {
			parser = spfactory.newSAXParser(); 
			// ָ��XML�ļ�������XML����
			parser.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")), msgModel);
		} catch (Exception e) {
			logger.error("����xml����:" + e);
			throw new WeChatException("��������xml��Ϣ����!");
		}
		return msgModel;
	}

	/**
	 * ������ת��xml�ַ��� ��XStream��ת����
	 * 
	 * @param obj
	 * @return
	 */
	public static String toXmlString(Object obj) {
		xstream = getXStreamInstance();
		// ����ע�⣬��xml�ַ����������ض��ĸ�ʽ���
		xstream.processAnnotations(obj.getClass());
		return xstream.toXML(obj);
	}

	/**
	 * ���XStream֧��CDATA
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
						if(nodeName.equals("CreateTime")) {//MsgIdû��CDATA��д�����������ﴦ����
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


