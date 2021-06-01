package cn.com.gs.demo;

import cn.com.gs.common.define.Constants;
import cn.com.gs.common.util.*;
import cn.com.gs.common.util.base64.Base64Util;
import cn.com.gs.common.util.cert.CertUtil;
import cn.com.gs.common.util.crypto.KeyUtil;
import cn.com.gs.common.util.crypto.RSAUtil;
import cn.com.gs.common.util.date.DateUtil;
import org.junit.Test;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class UtilTest {

	/**
	 * 制作图片验证码
	 *
	 * @throws Exception
	 */
	@Test
	public void genAuthCodeTest() throws Exception {
		ImageUtil.genAuthCodeImage(Constants.FILE_OUT_PATH + "authCodeImage.jpg");
		System.out.println("制作完成");
	}

	/**
	 * 图片去底色
	 */
	@Test
	public void transferAlphaTest() {
		byte[] photoData = ImageUtil.transferAlpha(Constants.FILE_PATH + "gs.png");
		FileUtil.storeFile(Constants.FILE_OUT_PATH + "gs_去底色.png", photoData);
		System.out.println("图片处理完毕！");
	}

	/**
	 * 制作二维码
	 */
	@Test
	public void genBarcodeImageTest() {
		byte[] bytes = ImageUtil.genBarcodeImage("https://www.baidu.com");
		FileUtil.storeFile(Constants.FILE_OUT_PATH + "barcodeImage.jpg", bytes);
		System.out.println("制作完成");
	}

	/**
	 * base64编解码
	 */
	@Test
	public void base64Test() {
		//编码
		String str1 = "son";
		byte[] b1 = Base64Util.encode(str1);
		System.out.println(new String(b1));

		//解码
		String str2 = "c29u";
		byte[] b2 = Base64Util.decode(str2);
		System.out.println(new String(b2));
	}

	/**
	 * x509证书解析
	 */
	@Test
	public void certTest() throws Exception {
		byte[] file = FileUtil.getFile(Constants.FILE_PATH + "ca.cer");
		X509Certificate x509Certificate = CertUtil.getX509Certificate(file);
		System.out.println(x509Certificate.getSubjectDN());
	}

	/**
	 * 系统提供者
	 */
	@Test
	public void providerTest() {
		CertUtil.getAllProvider();
	}

	/**
	 * 获取pdf模板文本域
	 *
	 * @throws Exception
	 */
	@Test
	public void getPdfFieldsTest() throws Exception {
		List list = PdfUtil.getTemplateFields(FileUtil.getFile(Constants.FILE_PATH + "req_con.pdf"));
		System.out.println(list);
	}

	/**
	 * 根据pdf模板生成pdf
	 *
	 * @throws Exception
	 */
	@Test
	public void genPdfByTemplateTest() throws Exception {
		byte[] pdfTemplateData = FileUtil.getFile(Constants.FILE_PATH + "req_con.pdf");

		Properties properties = new Properties();
		properties.setProperty("cert", "CN=Test");
		properties.setProperty("businessCodeCn", "新办");
		properties.setProperty("name", "张女士");
		properties.setProperty("phone", "15712345678");
		properties.setProperty("idType", "身份证");
		properties.setProperty("idNum", "411328****");
		properties.setProperty("other", "无");

		properties.setProperty("ag_name", "张女士");
		properties.setProperty("ag_sex", "女");
		properties.setProperty("ag_idNum", "411328****");
		properties.setProperty("ag_idType", "身份证");
		properties.setProperty("ag_phone", "15712345678");
		properties.setProperty("ag_telPhone", "0377-66666666");
		properties.setProperty("ag_email", "867096367@qq.com");
		properties.setProperty("ag_other", "无");

		properties.setProperty("req_time", DateUtil.getDateTime());
		properties.setProperty("remark", "无");

		byte[] pdfData = PdfUtil.genPdfByTemplate(pdfTemplateData, properties);
		FileUtil.storeFile(Constants.FILE_OUT_PATH + "个人信息.pdf", pdfData);
	}

	/**
	 * pdf插入图片
	 *
	 * @throws Exception
	 */
	@Test
	public void pdfAddImageTest() throws Exception {
		byte[] pdfData = FileUtil.getFile(Constants.FILE_PATH + "req_con.pdf");
		byte[] photoData = FileUtil.getFile(Constants.FILE_PATH + "gs.png");
		byte[] addImagePdf = PdfUtil.pdfAddImage(pdfData, photoData, 1, 100, 100, 100, 100);
		FileUtil.storeFile(Constants.FILE_OUT_PATH + "个人信息_addImage.pdf", addImagePdf);
		System.out.println("ok");
	}

	/**
	 * 压缩解压缩
	 *
	 * @throws Exception
	 */
	@Test
	public void zipTest() throws Exception {
		String zipPath = Constants.FILE_OUT_PATH + "file.zip";
		String unzipPath = Constants.FILE_OUT_PATH + "file_unzip";
		String filePath = Constants.FILE_PATH;
		ZipUtil.zipDir(filePath, zipPath, "");
		ZipUtil.unZip(zipPath, unzipPath, "");
		System.out.println("ok");
	}

	@Test
	public void execTest() throws Exception {
		String ipconfig = ExecSh.exec("ipconfig");
		System.out.println(ipconfig);
	}

	@Test
	public void hexTest() throws Exception {
		long time = DateUtil.getCurrentTime();
		System.out.println(time);
		System.out.println(HexUtil.byte2Hex(HexUtil.long2Byte(time)));
		System.out.println(HexUtil.byte2Long(HexUtil.long2Byte(time)));
	}

	@Test
	public void getHostMacTest() throws Exception {
		System.out.println(NetWorkUtil.getHostMac("eth0"));
	}

	@Test
	public void sigarTest() throws Exception {
		Map<String, Object> systemData = SigarUtil.getInstance().getSystemData();
		for(String key : systemData.keySet()){
			Object value = systemData.get(key);
			System.out.println(key + ":" + value);
		}
	}

	@Test
	public void genRsaKeyPairTest() throws Exception {
		KeyPair kayPair = RSAUtil.genRSAKeyPair(Constants.RSA_KEY_SIZE_1024);
		PublicKey publicKey = kayPair.getPublic();
		PrivateKey privateKey = kayPair.getPrivate();
		//得到base64编码的公钥/私钥字符串
		String publicKeyString = new String(Base64Util.encode(publicKey.getEncoded()));
		String privateKeyString = new String(Base64Util.encode(privateKey.getEncoded()));
		FileUtil.storeFile(Constants.FILE_OUT_PATH + "rsa/pubKey.txt", publicKeyString.getBytes());
		FileUtil.storeFile(Constants.FILE_OUT_PATH + "rsa/priKey.txt", privateKeyString.getBytes());
		System.out.println("RSA密钥对存储成功！");
	}

	/**
	 * RSA非对称加解密
	 * @throws Exception
	 */
	@Test
	public void rsaEncryptTest() throws Exception {
		String plain = "plain";
		System.out.println("加密开始，原文数据：" + plain);
		// 加载RSA公钥
		byte[] pubKeyData = FileUtil.getFile(Constants.FILE_OUT_PATH + "rsa/pubKey.txt");
		PublicKey publicKey = RSAUtil.generateP8PublicKey(Base64Util.decode(pubKeyData));
		byte[] encrypt = KeyUtil.encryptWithPubKey(publicKey, plain.getBytes(), -1, Constants.RSA);

		// 加载RSA私钥
		byte[] priKeyData = FileUtil.getFile(Constants.FILE_OUT_PATH + "rsa/priKey.txt");
		PrivateKey privateKey = RSAUtil.generateP8PrivateKey(Base64Util.decode(priKeyData));
		byte[] decrypt = KeyUtil.decryptWithPriKey(privateKey, encrypt, -1, Constants.RSA);
		System.out.println("解密完成，解密数据：" + new String(decrypt));
	}

	@Test
	public void rsaSignTest() throws Exception {
		String plain = "plain";
		System.out.println("签名开始，原文数据：" + plain);
		// 加载RSA私钥
		byte[] priKeyData = FileUtil.getFile(Constants.FILE_OUT_PATH + "rsa/priKey.txt");
		PrivateKey privateKey = RSAUtil.generateP8PrivateKey(Base64Util.decode(priKeyData));
		byte[] signed = KeyUtil.signWithPriKey(null, privateKey, plain.getBytes(), -1, Constants.SHA256_RSA, "1".getBytes());

		// 加载RSA公钥
		byte[] pubKeyData = FileUtil.getFile(Constants.FILE_OUT_PATH + "rsa/pubKey.txt");
		PublicKey publicKey = RSAUtil.generateP8PublicKey(Base64Util.decode(pubKeyData));
		boolean result = KeyUtil.signVerifyWithPubKey(publicKey, plain.getBytes(), signed, -1, Constants.SHA256_RSA, "1".getBytes());
		System.out.println("验签完成，验签结果：" + result);
	}
}
