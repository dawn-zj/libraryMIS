package cn.com.gs.common.util.pdf;

import cn.com.gs.common.define.Constants;
import cn.com.gs.common.util.FileUtil;
import cn.com.gs.common.util.StringUtil;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.security.BouncyCastleDigest;
import com.itextpdf.text.pdf.security.DigestAlgorithms;
import com.itextpdf.text.pdf.security.ExternalDigest;
import com.itextpdf.text.pdf.security.ExternalSignature;
import com.itextpdf.text.pdf.security.MakeSignature;
import com.itextpdf.text.pdf.security.PrivateKeySignature;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;

/**
 * 参考文章：https://blog.csdn.net/tomatocc/article/details/80762507
 * @author Administator
 */
public class PdfStampUtil {
    /**
     * keystore密码
     */
    public static String password = "11111111";

    public static String pfxPath = Constants.FILE_PATH + "/key/rsa/rsapfx3des-sha1.pfx";

    /**
     * 添加图片并签名
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

    public static void main(String[] args) {
        try {
            PdfStampUtil app = new PdfStampUtil();
            // 读取keystore ，获得私钥
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(new FileInputStream(pfxPath), password.toCharArray());
            String alias = ks.aliases().nextElement();
            PrivateKey pk = (PrivateKey) ks.getKey(alias, password.toCharArray());
            // 得到证书链
            Certificate[] chain = ks.getCertificateChain(alias);

            //签章
            byte[] pdfData = FileUtil.getFile(Constants.FILE_PATH + "2页.pdf");
            byte[] photoData = FileUtil.getFile(Constants.FILE_PATH + "999.png");
            byte[] signedData = app.sign(pdfData, photoData,1,100, 200, chain, pk, DigestAlgorithms.SHA1);
            FileUtil.storeFile(Constants.FILE_OUT_PATH + "stamp.pdf", signedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

