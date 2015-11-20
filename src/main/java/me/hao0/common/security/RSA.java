package me.hao0.common.security;

import me.hao0.common.exception.SecurityException;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA
 */
public class RSA {

    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    /**
     * 签名(先转为PKCS8)
     * @param signing 待签名字符串
     * @param privateKey 私钥
     * @param charset 编码
     * @return 签名后的字符串
     */
    public static String sign(String signing, String privateKey, String charset) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(priKey);
            signature.update(signing.getBytes(charset));
            return Base64.encode(signature.sign());
        } catch (Exception e) {
            throw new SecurityException(e);
        }
    }

    /**
     * RSA验签名检查
     * @param signing 待签名数据
     * @param signed 签名值
     * @param publicKey 公钥
     * @param charset  编码格式
     * @return 布尔值
     */
    public static Boolean verify(String signing, String signed, String publicKey, String charset) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initVerify(pubKey);
            signature.update(signing.getBytes(charset));
            return signature.verify(Base64.decode(signed));
        } catch (Exception e) {
            throw new SecurityException(e);
        }
    }

    /**
     * 解密
     * @param decrypting 待解密文
     * @param priKey 私钥
     * @param inpuCharset 编码格式
     * @return 解密后的字符串
     */
    public static String decrypt(String decrypting, String priKey, String inpuCharset) throws Exception {
        PrivateKey prikey = getPrivateKey(priKey);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prikey);

        InputStream ins = new ByteArrayInputStream(Base64.decode(decrypting));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        //rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
        byte[] buf = new byte[128];
        int bufl;

        while ((bufl = ins.read(buf)) != -1) {
            byte[] block;

            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                System.arraycopy(buf, 0, block, 0, bufl);
            }
            writer.write(cipher.doFinal(block));
        }
        return new String(writer.toByteArray(), inpuCharset);
    }

    /**
     * 得到私钥
     * @param key 密钥字符串（经过base64编码）
     * @return 私钥
     */
    public static PrivateKey getPrivateKey(String key) {
        try {
            byte[] keyBytes;
            keyBytes = Base64.decode(key);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            return privateKey;
        } catch (Exception e) {
            throw new SecurityException(e);
        }
    }
}