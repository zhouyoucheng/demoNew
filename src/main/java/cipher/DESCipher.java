package cipher;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * DEC加密算法，算法不公开,违反了柯克霍夫原则
 * @author wb-zyc501131
 *
 */
public class DESCipher {
	final static String SRC = "imooc hello";
	public static void main(String[] args) {
		jdkDES();
		bcDES();
	}

	/**
	 * jdk实现
	 */
	public static void jdkDES() {
		try {
			// 1.生成key
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
			keyGenerator.init(56);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();
			
			// 2.key转换
			DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
			SecretKey convertSecretKey = factory.generateSecret(desKeySpec);
			
			// 3.加密
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
			byte[] result = cipher.doFinal(SRC.getBytes());
			System.out.println("jdk des encrypt:" + Hex.encodeHexString(result));
			
			// 4.解密
			cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
			result = cipher.doFinal(result);
			System.out.println("jdk des decrypt :" + new String(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * bouncy castle 实现
	 * 添加provider,签名时候指定该provider
	 */
	public static void bcDES() {
		try {
			
			Security.addProvider(new BouncyCastleProvider());
			// 1.生成key
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES","BC");
			keyGenerator.init(56);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();
			
			// 2.key转换
			DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DES","BC");
			SecretKey convertSecretKey = factory.generateSecret(desKeySpec);
			
			// 3.加密
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
			byte[] result = cipher.doFinal(SRC.getBytes());
			System.out.println("jdk dc encrypt:" + Hex.encodeHexString(result));
			
			// 4.解密
			cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
			result = cipher.doFinal(result);
			System.out.println("jdk dc decrypt :" + new String(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
