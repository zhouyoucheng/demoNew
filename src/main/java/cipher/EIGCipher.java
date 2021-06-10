package cipher;

import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.spec.DHParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * 只提供公钥加密算法,JDK不提供，BC提供实现
 * 注意是由接收者生成密钥
 * @author wb-zyc501131
 *
 */
public class EIGCipher {
	static String src = "imooc hello";
	public static void main(String[] args) {

		try {
			// 添加提供者
			Security.addProvider(new BouncyCastleProvider());

			// 1.初始化密钥
			AlgorithmParameterGenerator aGenerator = AlgorithmParameterGenerator.getInstance("ELGamal", "BC");
			aGenerator.init(256);
			AlgorithmParameters aParameters = aGenerator.generateParameters();
			DHParameterSpec dhParameterSpec = aParameters.getParameterSpec(DHParameterSpec.class);
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ELGamal", "BC");
			keyPairGenerator.initialize(dhParameterSpec, new SecureRandom());
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			PrivateKey elGamalPrivateKey = keyPair.getPrivate();
			PublicKey elGamalPublicKey = keyPair.getPublic();
			System.out.println("elGamalPublicKey:" + Base64.encodeBase64String(elGamalPublicKey.getEncoded()));
			System.out.println("elGamalPrivateKey:" + Base64.encodeBase64String(elGamalPrivateKey.getEncoded()));


			// 2.公钥加密
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(elGamalPublicKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("ELGamal");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			Cipher cipher = Cipher.getInstance("ELGamal");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("公钥加密：" + Base64.encodeBase64String(result));

			// 3.私钥解密
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(elGamalPrivateKey.getEncoded());
			keyFactory = KeyFactory.getInstance("ELGamal");
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			cipher = Cipher.getInstance("ELGamal");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			result = cipher.doFinal(result);
			System.out.println("私钥解密：" + new String(result));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
