package cn.com.gs.common.util.crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import cn.com.gs.common.define.Constants;
import cn.com.gs.common.util.FileUtil;
import cn.com.gs.common.util.base64.Base64Util;

public class RSAUtil {

	static {
		// Security.addProvider();
	}

	public static void main(String[] args) throws Exception {
		KeyPair kayPair = genRSAKeyPair(Constants.RSA_KEY_SIZE_1024);
		RSAPublicKey publicKey = (RSAPublicKey)kayPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey)kayPair.getPrivate();
		//得到base64编码的公钥/私钥字符串
		String publicKeyString = new String(Base64Util.encode(publicKey.getEncoded()));
		String privateKeyString = new String(Base64Util.encode(privateKey.getEncoded()));
		FileUtil.storeFile("E:/rsa/pubKey.txt", publicKeyString.getBytes());
		FileUtil.storeFile("E:/rsa/priKey.txt", privateKeyString.getBytes());
		System.out.println("RSA密钥对存储成功！");
	}

	/**
	 * 生成RSA密钥对
	 * @param keySize 密钥长度
	 * @return KeyPair 密钥对
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 */
	public static KeyPair genRSAKeyPair(int keySize) throws NoSuchAlgorithmException, NoSuchProviderException {
		// 1.获取实例:provider是啥
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		// 2.初始化长度
		kpg.initialize(keySize);
		// 3.生成密钥对
		KeyPair keyPair = kpg.generateKeyPair();
		return keyPair;
	}
	
	//TODO RSA 加密、解密
}