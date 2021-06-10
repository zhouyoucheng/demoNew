package cipher;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

import org.apache.commons.codec.binary.Base64;

import com.google.common.base.Objects;

/**
 * 对称加密如果安全的传递密钥是个问题
 * @author wb-zyc501131
 *
 */
public class DHCipher {
	static String src = "imooc hello";
	public static void main(String[] args) {
		
		try {
			// 1.初始化发送方密钥
			KeyPairGenerator sendKeyGenerator = KeyPairGenerator.getInstance("DH");
			sendKeyGenerator.initialize(512);
			KeyPair senderKeyPair = sendKeyGenerator.generateKeyPair();
			// 发送方公钥，发功给接收方(网络，文件...)
			byte[] senderPublicKeyEnc = senderKeyPair.getPublic().getEncoded();
			
			// 2.初始化接收方密钥
			KeyFactory receiverKeyFactory = KeyFactory.getInstance("DH");
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(senderPublicKeyEnc);
			PublicKey receiverPublicKey = receiverKeyFactory.generatePublic(x509EncodedKeySpec);
			DHParameterSpec dhParameterSpec = ((DHPublicKey)receiverPublicKey).getParams();
			KeyPairGenerator receiverKeyPariCenerator = KeyPairGenerator.getInstance("DH");
			receiverKeyPariCenerator.initialize(dhParameterSpec);
			KeyPair receiverKeyPari = receiverKeyPariCenerator.generateKeyPair();
			PrivateKey receiverPrivateKey = receiverKeyPari.getPrivate();
			byte[] receiverPublicKeyEnc = receiverKeyPari.getPublic().getEncoded();
			
			// 3密钥构建
			KeyAgreement reveiverKeyAgreement = KeyAgreement.getInstance("DH");
			reveiverKeyAgreement.init(receiverPrivateKey);
			reveiverKeyAgreement.doPhase(receiverPublicKey, true);
			SecretKey receiverDesKey = reveiverKeyAgreement.generateSecret("DES");
			
			KeyFactory senderKeyFactory = KeyFactory.getInstance("DH");
			x509EncodedKeySpec = new X509EncodedKeySpec(receiverPublicKeyEnc);
			PublicKey senderPublicKey = senderKeyFactory.generatePublic(x509EncodedKeySpec);
			KeyAgreement senderKeyAgreement = KeyAgreement.getInstance("DH");
			senderKeyAgreement.init(senderKeyPair.getPrivate());
			senderKeyAgreement.doPhase(senderPublicKey, true);
			SecretKey senderDesKey = senderKeyAgreement.generateSecret("DES");
			if (Objects.equal(receiverDesKey, senderDesKey)) {
				System.out.println("双方密钥相同");
			}
			
			// 4.加密
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, senderDesKey);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("jdk dh encrypt:" + Base64.encodeBase64String(result));
			
			// 5.解密
			cipher.init(Cipher.DECRYPT_MODE, receiverDesKey);
			result = cipher.doFinal(result);
			System.out.println("jdk dh decrypt:" + new String(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
