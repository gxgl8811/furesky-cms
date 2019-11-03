package com.furesky.base.encryption;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;

/**
 * RSA 非对称加密算法
 * 
 * @author jiandax
 * @date 2019年10月24日
 */
public class RsaUtils {
	private static final int KEY_SIZE = 1024;
	private static final String CHARSET = "UTF-8";
	private static final String KEY_ALGORITHM = "RSA";

	/**
	 * 生成 RSA 秘钥对
	 * 
	 * @return
	 */
	public static RsaKeyPair generateKeyPair() {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
			keyPairGen.initialize(KEY_SIZE);
			KeyPair keyPair=keyPairGen.generateKeyPair();
			
			String publicKey=Base64Utils.encodeToString(keyPair.getPublic().getEncoded());
			String privateKey=Base64Utils.encodeToString(keyPair.getPrivate().getEncoded());
			return new RsaKeyPair(publicKey, privateKey);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * RSA 加密
	 * 
	 * @param data         数据
	 * @param key          秘钥
	 * @param isPrivateKey 是否是私钥加密
	 * @return
	 */
	public static String encrypt(String data, String key, boolean isPrivateKey) {
		if(StringUtils.isAnyBlank(data,key)) {
			return "";
		}
		byte[] cipherDataArray = null;
		try {
			Key keyKey = getKey(key, isPrivateKey);
			byte[] dataArray = data.getBytes(CHARSET);

			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, keyKey);
			cipherDataArray = cipher.doFinal(dataArray);
			return Base64Utils.encodeToString(cipherDataArray);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * RSA 解密
	 * 
	 * @param cipherData   密文
	 * @param key          秘钥
	 * @param isPrivateKey 是否是私钥加密
	 * @return
	 */
	public static String decrypt(String cipherData, String key, boolean isPrivateKey) {
		if(StringUtils.isAnyBlank(cipherData,key)) {
			return "";
		}
		byte[] dataArray = null;
		try {
			Key keyKey = getKey(key, isPrivateKey);
			byte[] cipherDataArray = Base64Utils.decodeFromString(cipherData);

			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, keyKey);
			dataArray = cipher.doFinal(cipherDataArray);
			return new String(dataArray, CHARSET);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 获取key
	 * 
	 * @param key          秘钥（Base64）
	 * @param isPrivateKey 是否是私钥
	 * @return
	 * @throws Exception
	 */
	private static Key getKey(String key, boolean isPrivateKey) throws Exception {
		byte[] keyArray = Base64Utils.decodeFromString(key);
		KeySpec keySpec = null;
		Key keyKey = null;
		if (isPrivateKey) {
			keySpec = new PKCS8EncodedKeySpec(keyArray);
			keyKey = KeyFactory.getInstance(KEY_ALGORITHM).generatePrivate(keySpec);
		} else {
			keySpec = new X509EncodedKeySpec(keyArray);
			keyKey = KeyFactory.getInstance(KEY_ALGORITHM).generatePublic(keySpec);
		}
		return keyKey;
	}
}
