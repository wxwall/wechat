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
 * 微信平台入口
 * @author wuxiaowei
 * Create on 2014-3-29 下午08:43:03
 */
public class WeChatAction extends HttpServlet {
	
	static Logger logger = Logger.getLogger(WeChatAction.class);

	private static final long serialVersionUID = 1L;
	
	private WeChatMng weChatMng;
	
	/** 微信平台Token */
	public static final String Token = "wxwall";
	 

	/**
	 * post请求用来接受用户发过来的消息
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
			//后续处理异常，暂时只输出错误日志信息
			logger.info(e.getMessage());
		}finally{
			out.flush();
			out.close();
		}
	}

	/**
	 * 每创建一次请求时，会重新创建一个servlet，
	 * 所以需要每次重新得到一次bean，这里注入的bean无效
	 */
	public void init() throws ServletException {
		if(weChatMng == null)
			weChatMng = (WeChatMng) SpringContextUtils.getBean("wechatMng");
	}
	
	


	/**
	 * get请求是微信平台用来验证否是开发者的情况，对后续业务开发不起作用
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{    
            //微信加密签名
             String signature = request.getParameter("signature");
             //时间戳
             String timestamp = request.getParameter("timestamp");
             //随机数
             String nonce = request.getParameter("nonce");
             //随机字符串
             String echostr = request.getParameter("echostr");
           if (StringUtils.isNotEmpty(echostr)) {
               echostr = checkAuthentication(signature,timestamp,nonce,echostr); 
               //验证通过返回随即字串
               response.getWriter().write(echostr);
               response.getWriter().flush();  
           }
       }catch(Exception e){
       }
	}

	/**
     *  Function:微信验证方法
     *  @author JLC
     *  @param signature 微信加密签名
     *  @param timestamp 时间戳
     *  @param nonce     随机数
     *  @param echostr   随机字符串
     *  @return
     */
    private String  checkAuthentication(String signature,String timestamp,String nonce,String echostr) {
        String result ="";
        // 将获取到的参数放入数组
        String[] ArrTmp = { Token, timestamp, nonce };
        // 按微信提供的方法，对数据内容进行排序
        Arrays.sort(ArrTmp);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ArrTmp.length; i++) {
            sb.append(ArrTmp[i]);
        }
        // 对排序后的字符串进行SHA-1加密
        String pwd = Encrypt(sb.toString());
        if (pwd.equals(signature)) { 
            try {
                System.out.println("微信平台签名消息验证成功！"); 
                result = echostr;
            } catch (Exception e) {
                e.printStackTrace();
            } 
        } else {
            System.out.println("微信平台签名消息验证失败！");
        }
        return result;
    }

    /**
     * 用SHA-1算法加密字符串并返回16进制串
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
            System.out.println("错误");
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
