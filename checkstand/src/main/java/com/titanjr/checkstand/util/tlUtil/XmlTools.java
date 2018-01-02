package com.titanjr.checkstand.util.tlUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.Provider;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.titanjr.checkstand.dto.TLAgentInfoRequestDTO;
import com.titanjr.checkstand.dto.TLAgentPayTransDTO;
import com.titanjr.checkstand.dto.TLAgentQueryTransDTO;
import com.titanjr.checkstand.request.TLAgentTradeRequest;
import com.titanjr.checkstand.respnse.TLAgentTradeResponse;
import com.titanjr.checkstand.util.tlUtil.bean.DownRsp;
import com.titanjr.checkstand.util.tlUtil.bean.PaymentSign;
import com.titanjr.checkstand.util.tlUtil.bean.QTDetail;
import com.titanjr.checkstand.util.tlUtil.bean.QTransRsp;
import com.titanjr.checkstand.util.tlUtil.bean.TransRet;
import com.titanjr.checkstand.util.tlUtil.security.CryptInf;
import com.titanjr.checkstand.util.tlUtil.security.CryptNoRestrict;
import com.titanjr.checkstand.util.tlUtil.security.CryptUnderRestrict;




public class XmlTools {
	
	private static final Logger logger = LoggerFactory.getLogger(XmlTools.class);
	private static Provider prvd=null;
	private static final SSLHandler simpleVerifier = new SSLHandler();
	private static SSLSocketFactory sslFactory;
	
	public static void initProvider(Provider p) {
		prvd = p;
	}
	private static class SSLHandler implements X509TrustManager, HostnameVerifier {	
		private SSLHandler() {
			
		} 
		public void checkClientTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
		}
		public void checkServerTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
		} 
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		} 
		public boolean verify(String arg0, SSLSession arg1) {
			return true;
		}
	}
	
	
	/**
	 * 构建报文
	 * @author Jerry
	 * @date 2017年12月29日 下午3:39:22
	 */
	public static String buildXml(Object o,boolean isreq) {
		XStream xs = initXStream(new XStreamIg(), isreq);
		String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>" + xs.toXML(o);
		xml=xml.replaceAll("__", "_");
		return xml;
	}
	
	/**
	 * 发送报文
	 * @author Jerry
	 * @date 2017年12月29日 下午3:40:46
	 */
	public static String send(String url,String xml) throws Exception {
		OutputStream reqStream=null;
		InputStream resStream =null;
		URLConnection request = null;
		String respText=null;
		byte[] postData;
		try {
			postData = xml.getBytes("GBK");
			request = createRequest(url, "POST");
	
			request.setRequestProperty("Content-type", "application/tlt-notify");
			request.setRequestProperty("Content-length", String.valueOf(postData.length));
			request.setRequestProperty("Keep-alive", "false");
	
			reqStream = request.getOutputStream();
			reqStream.write(postData);
			reqStream.close();
	
			ByteArrayOutputStream ms = null;	
			resStream = request.getInputStream();
			ms = new ByteArrayOutputStream();
			byte[] buf = new byte[4096];
			int count;
			while ((count = resStream.read(buf, 0, buf.length)) > 0) {
				ms.write(buf, 0, count);
			}
			resStream.close();
			respText = new String(ms.toByteArray(), "GBK");
		} catch(Exception ex) {
			throw ex;
		} finally {
			close(reqStream);
			close(resStream);
		}
		return respText;
	}
	/**
	 * 请求连接
	 * @author Jerry
	 * @date 2017年12月29日 下午3:49:49
	 */
	private static URLConnection createRequest(String strUrl, String strMethod) throws Exception {
		URL url = new URL(strUrl );
		//weblogic
		/*URL url = new URL(null,strUrl ,new Handler());*/
		URLConnection conn = url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		if (conn instanceof HttpsURLConnection)
		{
			HttpsURLConnection httpsConn = (HttpsURLConnection) conn;
			httpsConn.setRequestMethod(strMethod);
			httpsConn.setSSLSocketFactory(XmlTools.getSSLSF());
			httpsConn.setHostnameVerifier(simpleVerifier);
		}
		else if (conn instanceof HttpURLConnection)
		{
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setRequestMethod(strMethod);
		}
		return conn;
	}
	public static synchronized SSLSocketFactory getSSLSF() throws Exception {
		if(sslFactory!=null) {
			return sslFactory; 
		}
		SSLContext sc = prvd==null?SSLContext.getInstance("TLS"):SSLContext.getInstance("TLS");
		sc.init(null, new TrustManager[]{simpleVerifier}, null);
		sslFactory = sc.getSocketFactory();
		return sslFactory;
	}
	private static void close(InputStream c) {
		try {
			if(c!=null) c.close();
		} catch(Exception ex) {
			
		}
	}
	private static void close(OutputStream c) {
		try {
			if(c!=null) c.close();
		} catch(Exception ex) {
			
		}
	}
	
	
	/**
	 * 请求报文签名
	 * @author Jerry
	 * @date 2017年12月29日 下午3:47:50
	 */
	public static String signMsg(String strData, String pathPfx, String pass,boolean restrict) throws Exception {
		final String IDD_STR="<SIGNED_MSG></SIGNED_MSG>";
		String strMsg = strData.replaceAll(IDD_STR, "");
		String signedMsg = signPlain(strMsg, pathPfx, pass,prvd,restrict);
		String strRnt = strData.replaceAll(IDD_STR, "<SIGNED_MSG>" + signedMsg + "</SIGNED_MSG>");
		return strRnt;
	}
	private static String signPlain(String strData, String pathPfx, String pass,Provider prv,boolean restrict) throws Exception {
		PaymentSign.initProvider();
		CryptInf crypt;
		if(restrict) {
			crypt=new CryptUnderRestrict("GBK");
		} else {
			crypt=new CryptNoRestrict("GBK",prv);
		}
		String strRnt = "";
		if (crypt.SignMsg(strData, pathPfx, pass)) {
			String signedMsg = crypt.getLastSignMsg();
			strRnt = signedMsg;
		} else {
			throw new Exception("签名失败");
		}
		return strRnt;		
	}
	

	/**
	 * 返回报文验签
	 * @author Jerry
	 * @date 2017年12月29日 下午3:44:51
	 */
	public static boolean verifySign(String strXML, String cerFile,boolean restrict,boolean isfront) throws Exception {
		return verifySign(strXML, cerFile, prvd, restrict,isfront);
	}
	private static boolean verifySign(String strXML, String cerFile,Provider prv,boolean restrict,boolean isfront) throws Exception {
		if(!isfront){
			PaymentSign.initProvider();
			// 签名
			CryptInf crypt;
			if(restrict) {
				crypt=new CryptUnderRestrict("GBK");
			} else {
				crypt=new CryptNoRestrict("GBK",prv);
			}
			//
			File file = new File(cerFile);
			if (!file.exists()) {
				throw new Exception("文件"+cerFile+"不存在");
			}
			int iStart = strXML.indexOf("<SIGNED_MSG>");
			if(iStart==-1) {
				throw new Exception("XML报文中不存在<SIGNED_MSG>");
			}
			int end = strXML.indexOf("</SIGNED_MSG>");
			if(end==-1) {
				throw new Exception("XML报文中不存在</SIGNED_MSG>");
			}
			String signedMsg = strXML.substring(iStart + 12, end);
			String strMsg = strXML.substring(0, iStart) + strXML.substring(end + 13);
			logger.info(strMsg);
			return crypt.VerifyMsg(signedMsg.toLowerCase(), strMsg,cerFile);
		}else{
			return true;
		}
		
	}
	
	/**
	 * XML转请求对象
	 * @author Jerry
	 * @date 2017年12月29日 下午4:08:26
	 */
	public static TLAgentTradeRequest parseReq(String xml) {
		return (TLAgentTradeRequest) initXStream(new XStreamEx(), true).fromXML(xml);
	}
	/**
	 * XML转返回对象
	 * @author Jerry
	 * @date 2017年12月29日 下午4:09:05
	 */
	public static TLAgentTradeResponse parseRsp(String xml) {
		return (TLAgentTradeResponse) initXStream(new XStreamEx(), false).fromXML(xml);
	}
	
	
	public static XStream initXStream(XStream xs,boolean isreq) {
		if(isreq) {
			xs.alias("AIPG", TLAgentTradeRequest.class);
		}else {
			xs.alias("AIPG", TLAgentTradeResponse.class);
		}
		xs.addImplicitCollection(TLAgentTradeRequest.class, "trxData");//删除集合节点名称（不删子集）
		xs.addImplicitCollection(TLAgentTradeResponse.class, "trxData");
		xs.alias("INFO", TLAgentInfoRequestDTO.class);
		xs.alias("TRANS", TLAgentPayTransDTO.class);
		xs.alias("TRANSRET", TransRet.class);
		xs.alias("QTRANSREQ", TLAgentQueryTransDTO.class);
		xs.alias("QTRANSRSP", QTransRsp.class);
		xs.addImplicitCollection(QTransRsp.class, "details");
		xs.alias("QTDETAIL", QTDetail.class);
		xs.alias("DOWNRSP", DownRsp.class);
		return xs;
	}
	
}
