package com.furesky.base.encryption;

import java.security.Key;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;

/**
 * AES 对称加密算法
 * 
 * @author jiandax
 * @date 2019年10月24日
 */
public class AesUtils{
	private static final int KEY_SIZE = 16;
	private static final String CHARSET = "UTF-8";
	private static final String KEY_ALGORITHM = "AES";
	private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
	public static String IvParameter = "ed16b1f8a9e648d4";

	/**
	 * 生成 AES 秘钥
	 * 
	 * @return
	 */
	public static String generateKey() {
		try {
			Key key = KeyGenerator.getInstance(KEY_ALGORITHM).generateKey();
			return Base64Utils.encodeToString(key.getEncoded());
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	/**
	 * AES 加密
	 * 
	 * @param data 数据
	 * @param key 秘钥
	 * @return
	 */
	public static String encrypt(String data, String key) {
		if(StringUtils.isAnyBlank(data,key)) {
			return "";
		}
		byte[] keyArray = formatKey(Base64Utils.decodeFromString(key));
		try {
			SecretKeySpec keySpec = new SecretKeySpec(keyArray, KEY_ALGORITHM);
			IvParameterSpec iv = new IvParameterSpec(IvParameter.getBytes(CHARSET));
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

			byte[] cipherDataArray = cipher.doFinal(data.getBytes(CHARSET));
			return Base64Utils.encodeToString(cipherDataArray);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * AES 解密
	 * 
	 * @param cipherData 密文
	 * @param key 秘钥
	 * @return
	 */
	public static String decrypt(String cipherData, String key) {
		if(StringUtils.isAnyBlank(cipherData,key)) {
			return "";
		}
		byte[] keyArray = formatKey(Base64Utils.decodeFromString(key));
		byte[] cipherDataArray = Base64Utils.decodeFromString(cipherData);
		try {
			SecretKeySpec keySpec = new SecretKeySpec(keyArray, KEY_ALGORITHM);
			IvParameterSpec iv = new IvParameterSpec(IvParameter.getBytes(CHARSET));
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);

			byte[] dataArray = cipher.doFinal(cipherDataArray);
			return new String(dataArray, CHARSET);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	/**
	 * 获取秘钥字节数组，并限定长度
	 * 
	 * @param key 秘钥
	 * @return
	 */
	private static byte[] formatKey(byte[] keyArray) {
		if(keyArray.length==KEY_SIZE) {
			return keyArray;
		}
		if(keyArray.length>KEY_SIZE) {
			return Arrays.copyOfRange(keyArray, 0, KEY_SIZE);
		}
		return Arrays.copyOf(keyArray, KEY_SIZE);
	}

}
