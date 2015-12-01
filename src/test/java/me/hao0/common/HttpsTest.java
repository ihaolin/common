package me.hao0.common;

import me.hao0.common.http.Https;
import org.junit.Test;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;

/**
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 1/12/15
 */
public class HttpsTest {

    @Test
    public void testHttps() throws Exception {

        InputStream keyInput = Object.class.getResourceAsStream("/cert.p12");
        String pKeyPassword = "1253089901";
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");

        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(keyInput, pKeyPassword.toCharArray());
        keyInput.close();

        keyManagerFactory.init(keyStore, pKeyPassword.toCharArray());
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());
        SSLSocketFactory sockFact = context.getSocketFactory();

        String resp = Https.post("https://api.mch.weixin.qq.com/secapi/pay/refund")
             .body("<xml><appid><![CDATA[wxf66ea2204a7a1c58]]></appid><mch_id><![CDATA[1253089901]]></mch_id><nonce_str><![CDATA[rvcmjyhg9205v4wo]]></nonce_str><op_user_id><![CDATA[1253089901]]></op_user_id><out_refund_no><![CDATA[TEST3344556677]]></out_refund_no><refund_fee><![CDATA[1]]></refund_fee><refund_fee_type><![CDATA[CNY]]></refund_fee_type><sign><![CDATA[3D07071971352BCFF5E007B2B7FA9495]]></sign><total_fee><![CDATA[1]]></total_fee><transaction_id><![CDATA[1003110578201511281803217943]]></transaction_id></xml>")
             .ssLSocketFactory(sockFact)
             .request();

        System.out.println(resp);
    }
}
