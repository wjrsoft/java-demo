package com.jike;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSASignTureUtil {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String RSA_PUBLIC_KEY = "RSA_PUBLIC_KEY";
    public static final String RSA_PRIVATE_KEY = "RSA_PRIVATE_KEY";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * 生成 公私钥 密钥对
     */

    public static Map<String, Object> initKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put(RSA_PUBLIC_KEY, publicKey);
        keyMap.put(RSA_PRIVATE_KEY, privateKey);
        return keyMap;
    }


    public static byte[] getPublicKey(Map<String, Object> keyMap) {
        RSAPublicKey key = (RSAPublicKey) keyMap.get(RSA_PUBLIC_KEY);
        return key.getEncoded();
    }

    public static byte[] getPrivateKey(Map<String, Object> keyMap) {
        RSAPrivateKey key = (RSAPrivateKey) keyMap.get(RSA_PRIVATE_KEY);
        return key.getEncoded();
    }

    /**
     * 对原始数据进行签名
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] sign(byte[] data, byte[] privateKey) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey1 = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateKey1);
        signature.update(data);
        return signature.sign();
    }

    /**
     * 签名验证
     * @param data
     * @param publicKey
     * @param sign
     * @return
     */
    public static boolean verify(byte[] data, byte[] publicKey, byte[] sign) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey1 = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicKey1);
        signature.update(data);
        boolean isValidate = signature.verify(sign);
        return isValidate;
    }

    public static void main(String[] args) throws Exception {
        String DATA = "wangjinrongXueXiJike";
        Map<String, Object> keyMap = RSASignTureUtil.initKey();
        byte[] rsaPublicKey = RSASignTureUtil.getPublicKey(keyMap);
        byte[] rsaPrivateKey = RSASignTureUtil.getPrivateKey(keyMap);
        System.out.println("privateKey:" + RSASignTureUtil.bytesToHexString(rsaPrivateKey));
        System.out.println("publicKey:" + RSASignTureUtil.bytesToHexString(rsaPublicKey));

        //sign
        byte[] sign = RSASignTureUtil.sign(DATA.getBytes(), rsaPrivateKey);
        System.out.println("RSA Sign:"+RSASignTureUtil.bytesToHexString(sign));

        //Verify
        boolean isValidate = RSASignTureUtil.verify(DATA.getBytes(), rsaPublicKey, sign);
        System.out.println("RSA Verify:"+isValidate);
    }

    public static String bytesToHexString(byte... src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
