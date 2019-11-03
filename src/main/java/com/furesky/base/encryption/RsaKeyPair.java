package com.furesky.base.encryption;

public class RsaKeyPair implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4154729548296522966L;

	private String publicKey;
	private String privateKey;
	
	public RsaKeyPair(String publicKey,String privateKey) {
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}
}
