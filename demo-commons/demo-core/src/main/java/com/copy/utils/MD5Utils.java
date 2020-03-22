package com.copy.utils;


import com.copy.exception.AppException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具
 *
 * @author zhongyong
 * @date 20191014
 * @since v1.0
 */
public class MD5Utils {

    private static final Logger logger = LoggerFactory.getLogger(MD5Utils.class);

    private MD5Utils() {
    }

    /**
     * MD5加密
     *
     * @param plainText 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String encryption(String plainText) {
        String reMd5 = null;
        try {
            byte[] textBytes = plainText.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(textBytes);
            byte[] b = md.digest();
            reMd5 = Hex.encodeHexString(b);
        } catch (NoSuchAlgorithmException e) {
            logger.error("MD5加密异常", e);
            throw new AppException("MD5加密异常");
        }
        return reMd5;
    }

}
