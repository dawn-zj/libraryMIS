package cn.com.gs.common.util.pdf;

import cn.com.gs.common.define.Constants;
import cn.com.gs.common.exception.NetGSRuntimeException;
import cn.com.gs.common.util.StringUtil;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.security.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.ByteArrayOutputStream;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.util.ArrayList;

/**
 * 参考文章：https://blog.csdn.net/tomatocc/article/details/80762507
 * @author Administator
 */
public class PdfStampUtil {

    static {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    /**
     * PDF添加图片并签名
     * @param pdfData pdf数据
     * @param photoData 图片数据
     * @param pageNumber 页码
     * @param x x坐标
     * @param y y坐标
     * @param chain 证书链
     * @param privateKey 私钥
     * @param hashAlg 摘要算法
     * @return
     * @throws Exception
     */
    public byte[] sign(byte[] pdfData, byte[] photoData, int pageNumber, float x, float y,
                       Certificate[] chain, PrivateKey privateKey, String hashAlg) throws Exception {
        PdfReader reader = new PdfReader(pdfData);

        /*
         * 创建签章工具PdfStamper，
         * 第二个参数是输出流，签完的文件放在这个输出流，我们获取
         * 最后一个boolean参数是否允许被追加签名
         * false的话，pdf文件只允许被签名一次，多次签名，最后一次有效
         * true的话，pdf可以被追加签名，验签工具可以识别出每次签名之后文档是否被修改
         */
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfStamper stamper = PdfStamper.createSignature(reader, outputStream, '\0', null, true);

        // 1.设置PdfSignatureAppearance
        PdfSignatureAppearance sap = stamper.getSignatureAppearance();
        // 1.1设置图章图片
        Image image = Image.getInstance(photoData);
        sap.setSignatureGraphic(image);
        // 1.2设置图章的显示方式，这里是GRAPHIC只显示图章（还有其他的模式，可以图章和签名描述一同显示），不设置默认是展示描述
        sap.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);
        // 1.3设置图章位置，页码，签名域名称，多次追加签名的时候，签名预名称不能一样 图片大小受表单域大小影响（过小导致压缩）
        // 签名的坐标，是图章相对于pdf页面的位置坐标，原点为pdf页面左下角
        // 四个参数的分别是，图章左下角x，图章左下角y，图章右上角x，图章右上角y
        float imageWidth = image.getWidth() * 72f / Constants.DPI;
        float imageHeight = image.getHeight() * 72f / Constants.DPI;
        float ux = x + imageWidth;
        float uy = y + imageHeight;
        sap.setVisibleSignature(new Rectangle(x, y, ux, uy), pageNumber, StringUtil.genDigitRandom(10));

        // 2.摘要算法
        ExternalDigest digest = new BouncyCastleDigest();
        // 3.签名算法
        ExternalSignature signature = new PrivateKeySignature(privateKey, hashAlg, null);
        // 签章
        MakeSignature.signDetached(sap, digest, signature, chain,
                null, null, null, 0, MakeSignature.CryptoStandard.CADES);

        stamper.close();
        reader.close();
        return outputStream.toByteArray();

    }

    /**
     * PDF验签名
     * @param pdfData
     * @return
     * @throws Exception
     */
    public boolean verifySign(byte[] pdfData) throws Exception {
        PdfReader reader = new PdfReader(pdfData);
        AcroFields fields = reader.getAcroFields();
        ArrayList<String> names = fields.getSignatureNames();
        for (int i = 0, size = names.size(); i < size; i++) {
            String signName = (String) names.get(i);
            PdfDictionary dictionary = fields.getSignatureDictionary(signName);

            PdfName sub = dictionary.getAsName(PdfName.SUBFILTER);
            if (PdfName.ETSI_CADES_DETACHED.equals(sub)) {
                PdfPKCS7 pkcs7 = fields.verifySignature(signName);
                return pkcs7.verify();

            } else {
                throw new NetGSRuntimeException("暂不支持的SubFilter类型：" + sub);
            }
        }
        return false;
    }

}

