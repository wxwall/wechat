package cn.newtouch.inf.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class WeChatFilter implements Filter {
	
	public static String ENCODING = "UTF-8";
	
	Logger logger = Logger.getLogger(WeChatFilter.class);
	

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		if("POST".equals(request.getMethod())){
			getXmlContext(request, resp);
		}
		chain.doFilter(req, resp);
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

	
	/**
	 * 得到请求报文
	 * 
	 * @param request
	 * @param response
	 * @return 
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public void getXmlContext(ServletRequest request, ServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding(ENCODING);
		response.setCharacterEncoding(ENCODING);
		String msgXml = IOUtils.toString(request.getInputStream(), ENCODING);
		Document doc = null;
		SAXReader reader = new SAXReader();
		InputStream in = new ByteArrayInputStream(msgXml.getBytes(ENCODING));
		try {
			doc = reader.read(in);
		} catch (DocumentException e) {
			logger.error("解析请求报文格式！");
			e.printStackTrace();
		}
		Element root = doc.getRootElement();
		String msgType = root.element("MsgType").getTextTrim();
		if(!msgType.equals("event")){
			String msgId = root.element("MsgId").getTextTrim();
			request.setAttribute("msgId", msgId);
		}else{
			logger.info("不支持的请求方式：" + msgType);
		}
		request.setAttribute("msgXml", msgXml);
		request.setAttribute("msgType", msgType);
	}
}
