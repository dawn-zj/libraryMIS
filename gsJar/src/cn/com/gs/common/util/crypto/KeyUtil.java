package cn.com.gs.common.util.crypto;

import cn.com.gs.common.define.Constants;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;

public class KeyUtil {
    /**
     * 公钥加密
     * @param pubKey
     * @param value
     * @param hsmId
     * @param encAlg
     * @return
     * @throws Exception
     */
    public static byte[] encryptWithPubKey(PublicKey pubKey, byte[] value, int hsmId, String encAlg) throws Exception {
        if (pubKey == null)
            throw new Exception("pubKey is null");

        switch (encAlg) {
            case Constants.RSA:
                Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                c.init(Cipher.ENCRYPT_MODE, pubKey);
                return c.doFinal(value);
            case Constants.SM2:
                // TODO return new SM2Impl().encrypt(value, new SM2PublicKey(pubKey.getEncoded()).getEncoded4ex());
                throw new Exception("encrypt alg don't support, alg is: " + encAlg);
            default:
                throw new Exception("encrypt alg unknown, alg is: " + encAlg);
        }
    }

    /**
     * 私钥解密
     * @param priKey
     * @param value
     * @param hsmId
     * @param encAlg
     * @return
     * @throws Exception
     */
    public static byte[] decryptWithPriKey(PrivateKey priKey, byte[] value, int hsmId, String encAlg) throws Exception {
        if (priKey == null)
            throw new Exception("priKey is null");

        switch (encAlg) {
            case Constants.RSA:
                Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                c.init(Cipher.DECRYPT_MODE, priKey);
                return c.doFinal(value);
            case Constants.SM2:
                // TODO return new SM2Impl().decrypt(value, new SM2PrivateKey(priKey.getEncoded()).getD());
                throw new Exception("encrypt alg don't support, alg is: " + encAlg);
            default:
                throw new Exception("decrypt alg unknown, alg is: " + encAlg);
        }
    }

    /**
     * 对明文进行签名
     * @param pubKey
     * @param priKey
     * @param plain
     * @param hsmId
     * @param signAlg
     * @param id
     * @return
     * @throws Exception
     */
    public static byte[] signWithPriKey(PublicKey pubKey, PrivateKey priKey, byte[] plain, int hsmId, String signAlg, byte[] id) throws Exception {
        if (priKey == null)
            throw new Exception("priKey is null");

        switch (signAlg) {
            case Constants.SHA1_RSA:
                Signature sig = Signature.getInstance(Constants.SHA1_RSA, "INFOSEC");
                sig.initSign(priKey);
                sig.update(plain);
                return sig.sign();
            case Constants.SHA256_RSA:
                Signature sig256 = Signature.getInstance(Constants.SHA256_RSA, "INFOSEC");
                sig256.initSign(priKey);
                sig256.update(plain);
                return sig256.sign();
            case Constants.SM3_SM2:
                // byte[] X = ((JCESM2PublicKey) pubKey).getX();
                // byte[] Y = ((JCESM2PublicKey) pubKey).getY();
                //
                // SM2PublicKey pub = new SM2PublicKey();
                // pub.setX(X);
                // pub.setY(Y);
                //
                // return NetSignUtil.sign(plain, pub, (SM2PrivateKey) priKey, id);
                throw new Exception("sign alg don't support, alg is: " + signAlg);
            default:
                throw new Exception("sign alg unknown, alg is: " + signAlg);
        }
    }

    /**
     * 对明文摘要进行签名
     * @param pubKey
     * @param priKey
     * @param hashData
     * @param hsmId
     * @param signAlg
     * @param id
     * @return
     * @throws Exception
     */
    public byte[] signHashWithPriKey(PublicKey pubKey, PrivateKey priKey, byte[] hashData, int hsmId, String signAlg, byte[] id) throws Exception {
        return null;
    }

    /**
     * 对签名进行验证
     * @param pubKey 验证签名的公钥(PublicKey bytearray )
     * @param plain 明文
     * @param signed 签名密文
     * @param hsmId 加密卡id
     * @param signAlg 签名算法
     * @param id
     * @return 验证是否通过
     * @throws Exception
     */
    public boolean signVerifyWithPubKey(PublicKey pubKey, byte[] plain, byte[] signed, int hsmId, String signAlg, byte[] id) throws Exception {
        return false;
    }

    /**
     * 生成随机数
     * @param len 字节长度
     * @return
     * @throws Exception
     */
    public static byte[] genRandom(int len) {
        byte[] bs = new byte[len];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bs);
        return bs;
    }

}
