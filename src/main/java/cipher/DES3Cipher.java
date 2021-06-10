package cipher;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * 用途比较广泛,效率慢
 * @author wb-zyc501131
 *
 */
public class DES3Cipher {
	final static String SRC = "imooc hello";
	public static void main(String[] args) {
		jdk3DESede();
		bc3DESede();
	}

	/**
	 * jdk实现
	 */
	public static void jdk3DESede() {
		try {
			// 1.生成key
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
			keyGenerator.init(112);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();
			
			// 2.key转换
			DESedeKeySpec dESedeKeySpec = new DESedeKeySpec(bytesKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
			SecretKey convertSecretKey = factory.generateSecret(dESedeKeySpec);
			
			// 3.加密
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
			byte[] result = cipher.doFinal(SRC.getBytes());
			System.out.println("jdk DES3ede encrypt:" + Hex.encodeHexString(result));
			
			// 4.解密
			cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
			result = cipher.doFinal(result);
			System.out.println("jdk DES3ede decrypt :" + new String(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * bouncy castle 实现
	 * 添加provider,签名时候指定该provider
	 */
	public static void bc3DESede() {
		try {
			
			Security.addProvider(new BouncyCastleProvider());
			// 1.生成key
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede","BC");
			keyGenerator.init(168);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();
			
			// 2.key转换
			DESedeKeySpec dESedeKeySpec = new DESedeKeySpec(bytesKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede","BC");
			SecretKey convertSecretKey = factory.generateSecret(dESedeKeySpec);
			
			// 3.加密
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
			byte[] result = cipher.doFinal(SRC.getBytes());
			System.out.println("bc dc3 encrypt:" + Hex.encodeHexString(result));
			
			// 4.解密
			cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
			result = cipher.doFinal(result);
			System.out.println("bc dc3 decrypt :" + new String(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
