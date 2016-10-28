package test.fangcang.titanjr.rs.invoker.decrypt;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class RSATester {

    static String publicKey;
    static String privateKey;

    static {
        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair();
//            publicKey = RSAUtils.getPublicKey(keyMap);
//            privateKey = RSAUtils.getPrivateKey(keyMap);
            publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWYa7Oj4e0we9Tg9kJLjyNS3rLzlo/8nHp7Sb+oEfgnK/1gzH0OMlp8Hf09WyNk3T64UZtYPd5KwCVWMoZyK3jQwttHr+3cJ8335B4NzSwWjBfRJAf3bQPw0iZT+uoLF5+L7tbIK0Ql4bdDX+io1TkCagRU6XASrG7reXa3yc45wIDAQAB";
            privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJZhrs6Ph7TB71OD2QkuPI1LesvOWj/ycentJv6gR+Ccr/WDMfQ4yWnwd/T1bI2TdPrhRm1g93krAJVYyhnIreNDC20ev7dwnzffkHg3NLBaMF9EkB/dtA/DSJlP66gsXn4vu1sgrRCXht0Nf6KjVOQJqBFTpcBKsbut5drfJzjnAgMBAAECgYBtzcOjJeBUFutWUsZt0qn6DawLFpCFctTElxpK/+Ob2S0OGo6mIbTHuwMMBQwPUCUxbr5K7WuluMWDR0LCkuqMEQHHhJNVTknfVpwYcy5kGBbCGEywhgnfLlzIEfdeAiJ0I+Kfrzxc+QwvU/ecNPGhNhagbd8cHoI61/5EEf8JEQJBAMZ0uysxlMiEnc5QCsFfNVNkwsGhzwSvT3+xoKiLhM389Bxz14TEF9OKD241Lz9xoqGP9tHpDFeX/eJm+brksD8CQQDB/GrMWnETJ/kHEv37PZsiCtE6LQluMtqNIIwzSiliaYfF6xmcSMX6gBKS7DATu9lBJipJKTYWv8tyOWx6nU1ZAkAuxdhPJ9JfKBJhS7AdPyk8TGUyacZ23jKob97jmm5kdhe6lPrYibbr3oAgg1xtYYTo+xs7AegsxN/LemWlTLzVAkEAl1bPFzjkCLbhwJQfk7ffZLKdws5KEjAYc4vV9VnBaaa9Jzqgk13vHtx2ISuk4nBmMT6ONN+y9BKTrTyBljNMmQJAHmS2M2ZfvrZRYNfKyi/de2tAk/kTNX4rB62Uqwz6Z3+VzcDNdJpey/RNwd0QvC1O4OKJY3gDGkyO/YI4HJkr+w==";
            System.err.println("publicKey:\n" + publicKey);
            System.err.println("privateKey:\n" + privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        testFile();
//        test();
//        testSign();
    }

    private static void testFile() throws Exception {
    	File inputFile = new File("f:/dk-123456.zip");
    	File tmpFile = new File("f:/dk-123456-encryptFile.zip");
    	File outputFile = new File("f:/dk-123456-decrypt001.zip");
        //˽Կ���ܹ�Կ����
//        byte[] data = readFromFile(inputFile);
//        byte[] encodedData = RSAUtils.encryptByPrivateKey(data,privateKey );
//        writeToFile(encodedData, tmpFile);
//        byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
//        writeToFile(decodedData, outputFile);

        //
        byte[] data = readFromFile(inputFile);
        byte[] encodedData = RSAUtils.encryptByPublicKey(data,publicKey);
        writeToFile(encodedData, tmpFile);
        byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData, privateKey);
        writeToFile(decodedData, outputFile);
    }

	public static byte[] readFromFile(File fileName) throws Exception {

		FileInputStream fis = new FileInputStream(fileName);
		byte[] buf = new byte[(int) fileName.length()];
		fis.read(buf);
		fis.close();

		return buf;
	}

	public static void writeToFile(byte[] content, File fileName) {
		//创建输出流  
        FileOutputStream outStream;
		try {
			outStream = new FileOutputStream(fileName);
	        //写入数据  
	        outStream.write(content);  
	        //关闭输出流  
	        outStream.close();  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}

	static void test() throws Exception {
        System.err.println("公钥加密——私钥解密");
        String source = "这是一行没有任何意义的文字，你看完了等于没看，不是吗？";
        System.out.println("\r加密前文字：\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);
        System.out.println("加密后文字：\r\n" + new String(encodedData));
        byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData, privateKey);
        String target = new String(decodedData);
        System.out.println("解密后文字: \r\n" + target);
    }

    static void testSign() throws Exception {
        System.err.println("私钥加密——公钥解密");
        String source = "这是一行测试RSA数字签名的无意义文字";
        System.out.println("原文字：\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);
        System.out.println("加密后：\r\n" + new String(encodedData));
        byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
        String target = new String(decodedData);
        System.out.println("解密后: \r\n" + target);
        
        System.err.println("私钥签名——公钥验证签名");
        String sign = RSAUtils.sign(encodedData, privateKey);
        System.err.println("签名:\r" + sign);
        boolean status = RSAUtils.verify(encodedData, publicKey, sign);
        System.err.println("验证结果:\r" + status);
    }
}
