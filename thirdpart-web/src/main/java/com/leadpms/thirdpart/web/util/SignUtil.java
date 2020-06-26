package com.leadpms.thirdpart.web.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class SignUtil {
	
	public static String getSign(String param, String secretKey) {
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), mac.getAlgorithm());
            mac.init(secretKeySpec);
            byte[] hash = mac.doFinal(param.getBytes());
            return DatatypeConverter.printBase64Binary(hash);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return "";
    }

}
