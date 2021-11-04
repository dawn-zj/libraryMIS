package cn.com.gs.common.util.pkcs;

import cn.com.gs.common.define.Constants;
import cn.com.gs.common.util.FileUtil;
import cn.com.gs.common.util.HexUtil;
import cn.com.gs.common.util.crypto.KeyUtil;
import cn.com.gs.common.util.crypto.SM4Util;
import org.bouncycastle.asn1.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

/**
 * 数字信封工具类
 */
public class PKCS7EnvelopeUtil {
	/**
	 * 制作数字信封
	 *
	 * @param plain     原文
	 * @param publicKey 公钥
	 * @param symAlg    对称算法
	 */
	public static void makeP7(byte[] plain, PublicKey publicKey, String symAlg) throws Exception {
		// 使用对方公钥加密，保证只有对方能解
		// 1.生成对称密钥
		boolean isSM4 = false;
		byte[] symData = null;
		switch (symAlg) {
			case Constants.SM4:
				isSM4 = true;
				symData = KeyUtil.genRandom(16);

			default:
				isSM4 = true;
				symAlg = Constants.SM4;
				symData = KeyUtil.genRandom(16);
		}

		// 2.使用对称密钥加密原文plain
		byte[] encryptData = null;
		if (isSM4) {
			encryptData = SM4Util.ecb_encrypt(plain, symData);
			if (encryptData == null)
				throw new Exception("SM4 Encrypt Error");
		} else {
			// todo SM1/AES/DES/RC4
		}

		// 3.使用公钥加密，encryptData


		// 4.构建ASN1格式，PKCS7规范

	}

	public static void makeP7(byte[] plain, Certificate cert) {

	}

	public static void parseP7(byte[] envData, PrivateKey privateKey) {

	}

	@Test
	public void genDerASN1() throws Exception {
		// 创建ASN1EncodableVector, 存放sequence的数据
		ASN1EncodableVector encodable = new ASN1EncodableVector();
		encodable.add(new DERInteger(10)); //10对应的hex为0a
		encodable.add(new DERUTF8String("string"));

		// ASN1EncodableVector封装为DERSequence
		DERSequence derSequence = new DERSequence(encodable);

		// 写入DERSequence数据
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		DEROutputStream derOutputStream = new DEROutputStream(outputStream);
		derOutputStream.writeObject(derSequence);
		derOutputStream.flush();

		// 从字节流中获取最终数据
		byte[] result = outputStream.toByteArray();
		FileUtil.storeFile(Constants.FILE_OUT_PATH  + "sequence.asn1", result);
		System.out.println("DER编码生成完毕：" + HexUtil.byte2Hex(result)); // 300b02010a0c06737472696e67

		/*
		* 30 0b 02 01 0a 0c 06 73 74 72 69 6e 67
		* TLV
		* 30 代表ox30 sequence
		* 0b 代表sequence长度为11，值为02 01 0a 0c 06 73 74 72 69 6e 67
		*
		* 02 代表ox02 integer
		* 01 代表integer长度为1，值为0a，转为十进制为10
		*
		* 0c 代表ox0c utf8 string
		* 06 代表utf8 string长度为6，值为73 74 72 69 6e 67
		* */

	}
}
