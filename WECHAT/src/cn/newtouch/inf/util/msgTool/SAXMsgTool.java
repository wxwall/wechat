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
	public static MsgModel xmlToObj(String xml,Class<MsgModel> msg) throws WeChatException {
		SAXParserFactory spfactory = SAXParserFactory.newInstance();
		// ����SAX��������
		SAXParser parser;
		MsgModel msgModel = msg.getClass();
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
	 * ����ʵ����������������
	 * 
	 * @return
	 */
	public static XStream getXStreamInstance() {
		if (xstream == null)
			return new XStream();
		return xstream;
	}
}
