package me.hao0.common;

import me.hao0.common.xml.XmlReaders;
import org.junit.Test;

/**
 * Author: haolin
 * Date:   8/2/16
 * Email:  haolin.h0@gmail.com
 */
public class XmlReadersTest {

    @Test
    public void testRead(){
        String xml ="<xml><return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<return_msg><![CDATA[OK]]></return_msg>\n" +
                "<appid><![CDATA[wxe37]]></appid>\n" +
                "<mch_id><![CDATA[137]]></mch_id>\n" +
                "<nonce_str><![CDATA[AYySchVKar2W0Ynb]]></nonce_str>\n" +
                "<sign><![CDATA[C2DD53C14B94761843CEA701254B8E4]]></sign>\n" +
                "<result_code><![CDATA[FAIL]]></result_code>\n" +
                "<err_code><![CDATA[OUT_TRADE_NO_USED]]></err_code>\n" +
                "<err_code_des><![CDATA[商户订单号重复]]></err_code_des>\n" +
                "</xml>";
        XmlReaders.create(xml, "UTF-8");

        System.out.println(System.getProperty("file.encoding"));

        System.out.println(System.getProperties());
    }
}
