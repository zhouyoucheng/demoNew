package cipher;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;


/**
 * 基于口令的加密，加了一个盐
 * @author wb-zyc501131
 *
 */
public class PBECipher {
	final static String SRC = "imooc security pbe";
	public static void main(String[] args) {
		jdkPBE();
		bcPBE();
	}

	/**
	 * jdk实现
	 */
	public static void jdkPBE() {
		try {
			// 1.初始化盐
			SecureRandom random = new SecureRandom();
			byte[] salt = random.generateSeed(8);
			
			// 2.口令与密钥
			String password = "imooc";
			PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
			SecretKey key = factory.generateSecret(pbeKeySpec);
			
			// 3.加密
			PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, 100);
			Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
			cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
			byte[] result = cipher.doFinal(SRC.getBytes());
			System.out.println("jdk pbe encrypt:" + Base64.encodeBase64String(result));
			
			// 4.解密
			cipher.init(Cipher.DECRYPT_MODE,key,parameterSpec);
			result = cipher.doFinal(result);
			System.out.println("jdk AES decrypt :" + new String(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * bouncy castle 实现
	 * 添加provider,签名时候指定该provider
	 */
	public static void bcPBE() {
		
	}	
}
