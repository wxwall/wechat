package cn.newtouch.inf.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.newtouch.inf.service.WeChatMng;
import cn.newtouch.inf.util.SpringContextUtils;
import cn.newtouch.inf.util.Exception.WeChatException;

/**
 * ΢��ƽ̨���
 * @author wuxiaowei
 * Create on 2014-3-29 ����08:43:03
 */
public class WeChatAction extends HttpServlet {
	
	static Logger logger = Logger.getLogger(WeChatAction.class);

	private static final long serialVersionUID = 1L;
	
	private WeChatMng weChatMng;
	
	/** ΢��ƽ̨Token */
	public static final String Token = "wxwall";
	 

	/**
	 * post�������������û�����������Ϣ
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String msgType = request.getAttribute("msgType") == null ? "" : request.getAttribute("msgType").toString();
		String wxMsgXml = request.getAttribute("msgXml") == null ? "" : request.getAttribute("msgXml").toString();
		PrintWriter out = response.getWriter();
		try {
			String returnXml = weChatMng.process(msgType,wxMsgXml);
			out.print(returnXml);
		} catch (WeChatException e) {
			//���������쳣����ʱֻ���������־��Ϣ
			logger.info(e.getMessage());
		}finally{
			out.flush();
			out.close();
		}
	}

	/**
	 * ÿ����һ������ʱ�������´���һ��servlet��
	 * ������Ҫÿ�����µõ�һ��bean������ע���bean��Ч
	 */
	public void init() throws ServletException {
		if(weChatMng == null)
			weChatMng = (WeChatMng) SpringContextUtils.getBean("wechatMng");
	}
	
	


	/**
	 * get������΢��ƽ̨������֤���ǿ����ߵ�������Ժ���ҵ�񿪷���������
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{    
            //΢�ż���ǩ��
             String signature = request.getParameter("signature");
             //ʱ���
             String timestamp = request.getParameter("timestamp");
             //�����
             String nonce = request.getParameter("nonce");
             //����ַ���
             String echostr = request.getParameter("echostr");
           if (StringUtils.isNotEmpty(echostr)) {
               echostr = checkAuthentication(signature,timestamp,nonce,echostr); 
               //��֤ͨ�������漴�ִ�
               response.getWriter().write(echostr);
               response.getWriter().flush();  
           }
       }catch(Exception e){
       }
	}

	/**
     *  Function:΢����֤����
     *  @author JLC
     *  @param signature ΢�ż���ǩ��
     *  @param timestamp ʱ���
     *  @param nonce     �����
     *  @param echostr   ����ַ���
     *  @return
     */
    private String  checkAuthentication(String signature,String timestamp,String nonce,String echostr) {
        String result ="";
        // ����ȡ���Ĳ�����������
        String[] ArrTmp = { Token, timestamp, nonce };
        // ��΢���ṩ�ķ��������������ݽ�������
        Arrays.sort(ArrTmp);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ArrTmp.length; i++) {
            sb.append(ArrTmp[i]);
        }
        // ���������ַ�������SHA-1����
        String pwd = Encrypt(sb.toString());
        if (pwd.equals(signature)) { 
            try {
                System.out.println("΢��ƽ̨ǩ����Ϣ��֤�ɹ���"); 
                result = echostr;
            } catch (Exception e) {
                e.printStackTrace();
            } 
        } else {
            System.out.println("΢��ƽ̨ǩ����Ϣ��֤ʧ�ܣ�");
        }
        return result;
    }

    /**
     * ��SHA-1�㷨�����ַ���������16���ƴ�
     * 
     * @param strSrc
     * @return
     */
    private String Encrypt(String strSrc) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-1");
            md.update(bt);
            strDes = bytes2Hex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("����");
            return null;
        }
        return strDes;
    }

    private String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

}
