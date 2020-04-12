package utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * RSA加密
 * @author wen
 * @date 2019-3-23
 *
 */
public class RSAUtil {

	
	public static String ENCRYPT_TYPE = "RSA";
	
	/**
	 * 获取密钥对
	 * @return
	 */
	public static KeyPair getKeyPair() {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ENCRYPT_TYPE);
			keyPairGenerator.initialize(1024);
			return keyPairGenerator.generateKeyPair();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
