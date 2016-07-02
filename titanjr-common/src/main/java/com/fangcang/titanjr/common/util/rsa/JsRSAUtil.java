package com.fangcang.titanjr.common.util.rsa;

/**
 * 
 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

/**
 * 
 * @description:   js与后端交互:RSA 工具类。提供加密，解密，生成密钥对等方法。
 * @fileName:JsRSAUtil.java 
 * @createTime:2015年6月26日 下午5:41:26  
 * @author:		黄伟 
 * @version 1.0.0  
 *
 */
public class JsRSAUtil {
	
	private static String RSAKeyStore =JsRSAUtil.class.getClassLoader().getResource("").getPath()+"RSAKey.txt";
	
	static{
		File file=new File(RSAKeyStore);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
//	private  static Map<String, KeyPair> map  = new HashMap<String, KeyPair>();
	
	
	/**
	 * 
	 * @function:  生成密钥对
	 * @return
	 * @throws Exception KeyPair   
	 * @exception 
	 * @author:	黄伟
	 * @since  1.0.0
	 */
	public static KeyPair generateKeyPair() throws Exception {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA",
					new org.bouncycastle.jce.provider.BouncyCastleProvider());
			final int KEY_SIZE = 1024;// 这个值关系到块加密的大小，可以更改，但是不要太大，否则效率会低
			keyPairGen.initialize(KEY_SIZE, new SecureRandom());
			KeyPair keyPair = keyPairGen.generateKeyPair();
			
//			System.out.println(keyPair.getPrivate());
//			System.out.println(keyPair.getPublic());
			
			saveKeyPair(keyPair);
			return keyPair;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static KeyPair getKeyPair() throws Exception {
		FileInputStream fis = new FileInputStream(RSAKeyStore);
		ObjectInputStream oos = new ObjectInputStream(fis);
		KeyPair kp = (KeyPair) oos.readObject();
		oos.close();
		fis.close();
		return kp;
	}
	
//	public static KeyPair getKeyPair() throws Exception {
//		KeyPair keyPair =map.get("KeyPair");
//		return keyPair;
//	}
	

	public static void saveKeyPair(KeyPair kp) throws Exception {

		FileOutputStream fos = new FileOutputStream(RSAKeyStore);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		// 生成密钥
		oos.writeObject(kp);
		oos.close();
		fos.close();
	}
	
//	public static void saveKeyPair(KeyPair kp) throws Exception {
//		map.put("KeyPair", kp);
//	}

	private static void closeStreams(InputStream is,OutputStream os){
		if(null!=is){
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(null!=os){
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * @function:	生成公钥  
	 * @param modulus
	 * @param publicExponent
	 * @return
	 * @throws Exception RSAPublicKey   
	 * @exception 
	 * @author:		黄伟   
	 * @since  1.0.0
	 */
	public static RSAPublicKey generateRSAPublicKey(byte[] modulus,
			byte[] publicExponent) throws Exception {
		KeyFactory keyFac = null;
		try {
			keyFac = KeyFactory.getInstance("RSA",
					new org.bouncycastle.jce.provider.BouncyCastleProvider());
		} catch (NoSuchAlgorithmException ex) {
			throw new Exception(ex.getMessage());
		}

		RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(
				modulus), new BigInteger(publicExponent));
		try {
			return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
		} catch (InvalidKeySpecException ex) {
			throw new Exception(ex.getMessage());
		}
	}



	/**
	 * 
	 * @function: 生成私钥 
	 * @param modulus
	 * @param privateExponent
	 * @return
	 * @throws Exception RSAPrivateKey   
	 * @exception 
	 * @author:		黄伟   
	 * @since  1.0.0
	 */
	public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus,
			byte[] privateExponent) throws Exception {
		KeyFactory keyFac = null;
		try {
			keyFac = KeyFactory.getInstance("RSA",
					new org.bouncycastle.jce.provider.BouncyCastleProvider());
		} catch (NoSuchAlgorithmException ex) {
			throw new Exception(ex.getMessage());
		}

		RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(
				modulus), new BigInteger(privateExponent));
		try {
			return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
		} catch (InvalidKeySpecException ex) {
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * 
	 * @function:  
	 * @param pk 加密的密钥
	 * @param data  待加密的明文数据 
	 * @return 加密后的数据
	 * @throws Exception byte[]   
	 * @exception 
	 * @author:		黄伟
	 * @since  1.0.0
	 */
	
	public static byte[] encrypt(PublicKey pk, byte[] data) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("RSA",
					new org.bouncycastle.jce.provider.BouncyCastleProvider());
			cipher.init(Cipher.ENCRYPT_MODE, pk);
			int blockSize = cipher.getBlockSize();// 获得加密块大小，如：加密前数据为128个byte，而key_size=1024
			// 加密块大小为127
			// byte,加密后为128个byte;因此共有2个加密块，第一个127
			// byte第二个为1个byte
			int outputSize = cipher.getOutputSize(data.length);// 获得加密块加密后块大小
			int leavedSize = data.length % blockSize;
			int blocksSize = leavedSize != 0 ? data.length / blockSize + 1
					: data.length / blockSize;
			byte[] raw = new byte[outputSize * blocksSize];
			int i = 0;
			while (data.length - i * blockSize > 0) {
				if (data.length - i * blockSize > blockSize)
					cipher.doFinal(data, i * blockSize, blockSize, raw, i
							* outputSize);
				else
					cipher.doFinal(data, i * blockSize, data.length - i
							* blockSize, raw, i * outputSize);
				// 这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把byte[]放到
				// ByteArrayOutputStream中，而最后doFinal的时候才将所有的byte[]进行加密，可是到了此时加密块大小很可能已经超出了
				// OutputSize所以只好用dofinal方法。

				i++;
			}
			return raw;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 
	 * @function:  解密
	 * @param pk 解密的密钥
	 * @param raw 已经加密的数据
	 * @return
	 * @throws Exception byte[]   
	 * @exception  解密后的明文
	 * @author:		黄伟   
	 * @since  1.0.0
	 */
	public static byte[] decrypt(PrivateKey pk, byte[] raw) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("RSA",
					new org.bouncycastle.jce.provider.BouncyCastleProvider());
			cipher.init(cipher.DECRYPT_MODE, pk);
			int blockSize = cipher.getBlockSize();
			ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
			int j = 0;

			while (raw.length - j * blockSize > 0) {
				bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
				j++;
			}
			return bout.toByteArray();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	
	/**
	 * 
	 * @function:   对数据进行解密的公用方法(包过解决乱码等一些问题)
	 * @param privateKey 私钥
	 * @param result	要解密的内容
	 * @return	解密成功后的数据
	 * @throws Exception String   
	 * @exception 
	 * @author:		黄伟   
	 * @since  1.0.0
	 */
	public static String decryptString(PrivateKey privateKey,String result) throws Exception{
		
		String pwd=null;
		try
		{
			byte[] en_result = JsRSAUtil.hexStringToBytes(result);
			byte[] de_result = JsRSAUtil.decrypt(privateKey,en_result);
//			System.out.println(new String(de_result));
			StringBuffer sb = new StringBuffer();
			sb.append(new String(de_result));
			pwd = sb.reverse().toString();
			
			pwd = URLDecoder.decode(pwd,"UTF-8");//
		}
		catch (Exception e)
		{
			throw e;
		}
		
		return pwd;
	}
	
	/**
	 *  秘文传过来一共有256个字符，通过转成字节数组，同样的明文，有时传过来转成的字节数组长度为128，有的时候却又是129，
     *说是这个转字节数组的方式问题嘛，我就换了一个方式，将其稳定在128位
	 * @function: 解决byte[] en_result = new BigInteger(result, 16).toByteArray(); 算法不稳定问题  
	 * @param hexString
	 * @return byte[]   
	 * @exception 
	 * @author:wujun
	 * @since  1.0.0
	 */
	public static byte[] hexStringToBytes(String hexString) {  
	    if (hexString == null || hexString.equals("")) {  
	        return null;  
	    }  
	    hexString = hexString.toUpperCase();  
	    int length = hexString.length() / 2;  
	    char[] hexChars = hexString.toCharArray();  
	    byte[] d = new byte[length];  
	    for (int i = 0; i < length; i++) {  
	        int pos = i * 2;  
	        d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));  
	    }  
	    return d;  
	}  
	
	/**  
	 * Convert char to byte  
	 * @param c char  
	 * @return byte  
	 */  
	 private static byte charToByte(char c) {  
	    return (byte) "0123456789ABCDEF".indexOf(c);  
	}  

	public static void main(String[] args) throws Exception {
		RSAPublicKey rsap = (RSAPublicKey) JsRSAUtil.generateKeyPair().getPublic();
		RSAPrivateKey rsapri = (RSAPrivateKey)JsRSAUtil.generateKeyPair().getPrivate();
		
		//RSAPublicKey rsap = (RSAPublicKey)getKeyPair().getPublic();
//		String test = "huangweiweiweiweiweiwei";
//		byte[] en_test = encrypt(getKeyPair().getPublic(), test.getBytes());
//		byte[] de_test = decrypt(getKeyPair().getPrivate(), en_test);
		String module = rsap.getModulus().toString(16);  
		String empoent = rsap.getPublicExponent().toString(16);  
		System.out.println("module=================:"+module);
		System.out.println("empoent============:"+empoent);
		System.out.println("module=================:"+rsapri.getModulus().toString(16));
		System.out.println("empoent============:"+rsapri.getPrivateExponent().toString(16));
		
	//	System.out.println("解密后为==========:"+new String(de_test));
	}
}
