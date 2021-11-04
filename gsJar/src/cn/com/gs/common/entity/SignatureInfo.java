package cn.com.gs.common.entity;

import java.security.PrivateKey;
import java.security.cert.Certificate;

/**
 * @author Administator
 */
public class SignatureInfo {
    private String hashAlgorithm;//摘要算法名称，例如SHA-1
    private String imagePath;//图章路径
    private Certificate[] chain;//证书链
    private PrivateKey privateKey;//签名私钥
    private float x;//图章左下角x
    private float y;//图章左下角y

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String getHashAlgorithm() {
        return hashAlgorithm;
    }

    public void setHashAlgorithm(String hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Certificate[] getChain() {
        return chain;
    }

    public void setChain(Certificate[] chain) {
        this.chain = chain;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

}

