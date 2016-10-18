package test.fangcang.titanjr.rs.invoker.decrypt;

import java.io.*;
import java.util.Map;

public class RSATester {

    static String publicKey;
    static String privateKey;

    static {
        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair();
//            publicKey = RSAUtils.getPublicKey(keyMap);
//            privateKey = RSAUtils.getPrivateKey(keyMap);
            //�ϴ���Կ��(�涨��Կ�����ϴ�)
            publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWYa7Oj4e0we9Tg9kJLjyNS3rLzlo/8nHp7Sb+oEfgnK/1gzH0OMlp8Hf09WyNk3T64UZtYPd5KwCVWMoZyK3jQwttHr+3cJ8335B4NzSwWjBfRJAf3bQPw0iZT+uoLF5+L7tbIK0Ql4bdDX+io1TkCagRU6XASrG7reXa3yc45wIDAQAB";
            //����˽Կ�����涨���غ�˽Կ���ܣ�
            privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJZhrs6Ph7TB71OD2QkuPI1LesvOWj/ycentJv6gR+Ccr/WDMfQ4yWnwd/T1bI2TdPrhRm1g93krAJVYyhnIreNDC20ev7dwnzffkHg3NLBaMF9EkB/dtA/DSJlP66gsXn4vu1sgrRCXht0Nf6KjVOQJqBFTpcBKsbut5drfJzjnAgMBAAECgYBtzcOjJeBUFutWUsZt0qn6DawLFpCFctTElxpK/+Ob2S0OGo6mIbTHuwMMBQwPUCUxbr5K7WuluMWDR0LCkuqMEQHHhJNVTknfVpwYcy5kGBbCGEywhgnfLlzIEfdeAiJ0I+Kfrzxc+QwvU/ecNPGhNhagbd8cHoI61/5EEf8JEQJBAMZ0uysxlMiEnc5QCsFfNVNkwsGhzwSvT3+xoKiLhM389Bxz14TEF9OKD241Lz9xoqGP9tHpDFeX/eJm+brksD8CQQDB/GrMWnETJ/kHEv37PZsiCtE6LQluMtqNIIwzSiliaYfF6xmcSMX6gBKS7DATu9lBJipJKTYWv8tyOWx6nU1ZAkAuxdhPJ9JfKBJhS7AdPyk8TGUyacZ23jKob97jmm5kdhe6lPrYibbr3oAgg1xtYYTo+xs7AegsxN/LemWlTLzVAkEAl1bPFzjkCLbhwJQfk7ffZLKdws5KEjAYc4vV9VnBaaa9Jzqgk13vHtx2ISuk4nBmMT6ONN+y9BKTrTyBljNMmQJAHmS2M2ZfvrZRYNfKyi/de2tAk/kTNX4rB62Uqwz6Z3+VzcDNdJpey/RNwd0QvC1O4OKJY3gDGkyO/YI4HJkr+w==";
            System.err.println("��Կ:\n" + publicKey);
            System.err.println("˽Կ:\n" + privateKey);
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
    	File inputFile = new File("d:/M10000001.zip");
    	File tmpFile = new File("d:/encryptFile.zip");
    	File outputFile = new File("d:/decrypt001.zip");
        //˽Կ���ܹ�Կ����
//        byte[] data = readFromFile(inputFile);
//        byte[] encodedData = RSAUtils.encryptByPrivateKey(data,privateKey );
//        writeToFile(encodedData, tmpFile);
//        byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
//        writeToFile(decodedData, outputFile);

        //��Կ���ܣ�˽Կ����
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
		//���������
        FileOutputStream outStream;
		try {
			outStream = new FileOutputStream(fileName);
	        //д������
	        outStream.write(content);
	        //�ر������
	        outStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void test() throws Exception {
        System.err.println("��Կ���ܡ���˽Կ����");
        String source = "����һ��û���κ���������֣��㿴���˵���û����������";
        System.out.println("\r����ǰ���֣�\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);
        System.out.println("���ܺ����֣�\r\n" + new String(encodedData));
        byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData, privateKey);
        String target = new String(decodedData);
        System.out.println("���ܺ�����: \r\n" + target);
    }

    static void testSign() throws Exception {
        System.err.println("˽Կ���ܡ�����Կ����");
        String source = "����һ�в���RSA����ǩ��������������";
        System.out.println("ԭ���֣�\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);
        System.out.println("���ܺ�\r\n" + new String(encodedData));
        byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
        String target = new String(decodedData);
        System.out.println("���ܺ�: \r\n" + target);

        System.err.println("˽Կǩ��������Կ��֤ǩ��");
        String sign = RSAUtils.sign(encodedData, privateKey);
        System.err.println("ǩ��:\r" + sign);
        boolean status = RSAUtils.verify(encodedData, publicKey, sign);
        System.err.println("��֤���:\r" + status);
    }
}
