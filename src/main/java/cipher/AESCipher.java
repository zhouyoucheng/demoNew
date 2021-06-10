package cipher;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * 目前使用最多的对称加密算法，目前官方没有报道被破解，des算法没公开，des3慢
 * 比des高级
 * @author wb-zyc501131
 *
 */
public class AESCipher {
	final static String SRC = "imooc hello";
	public static void main(String[] args) {
		jdkAESede();
		bcAESede();
	}

	/**
	 * jdk实现
	 */
	public static void jdkAESede() {
		try {
			// 1.生成key
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();
			
			// 2.key转换
			Key key = new SecretKeySpec(bytesKey, "AES");
			
			// 3.加密
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(SRC.getBytes());
			System.out.println("jdk AES encrypt:" + Hex.encodeHexString(result));
			
			// 4.解密
			cipher.init(Cipher.DECRYPT_MODE,key);
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
	public static void bcAESede() {
		
			try {
				Security.addProvider(new BouncyCastleProvider());
				// 1.生成key
				KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "BC");
				keyGenerator.init(128);
				SecretKey secretKey = keyGenerator.generateKey();
				byte[] bytesKey = secretKey.getEncoded();
				
				// 2.key转换
				Key key = new SecretKeySpec(bytesKey, "AES");
				
				// 3.加密
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
				cipher.init(Cipher.ENCRYPT_MODE, key);
				byte[] result = cipher.doFinal(SRC.getBytes());
				System.out.println("bc AES encrypt:" + Hex.encodeHexString(result));
				
				// 4.解密
				cipher.init(Cipher.DECRYPT_MODE,key);
				result = cipher.doFinal(result);
				System.out.println("bc AES decrypt :" + new String(result));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}	
}
