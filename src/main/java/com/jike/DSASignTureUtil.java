package com.jike;

import sun.security.provider.DSAPublicKey;

import java.security.*;
import java.security.interfaces.DSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class DSASignTureUtil {
    public static final String KEY_ALGORITHM = "DSA";
    public static final String DSA_PUBLIC_KEY = "DSA_PUBLIC_KEY";
    public static final String DSA_PRIVATE_KEY = "DSA_PRIVATE_KEY";
    public static final String SIGNATURE_ALGORITHM = "SHA1withDSA";
    public static final int KEY_SIZE = 1024;

    /**
     * 生成 公私钥 密钥对
     */

    public static Map<String, Object> initKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(KEY_SIZE);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        DSAPublicKey publicKey = (DSAPublicKey) keyPair.getPublic();
        DSAPrivateKey privateKey = (DSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put(DSA_PUBLIC_KEY, publicKey);
        keyMap.put(DSA_PRIVATE_KEY, privateKey);
        return keyMap;
    }


    public static byte[] getPublicKey(Map<String, Object> keyMap) {
        DSAPublicKey key = (DSAPublicKey) keyMap.get(DSA_PUBLIC_KEY);
        return key.getEncoded();
    }

    public static byte[] getPrivateKey(Map<String, Object> keyMap) {
        DSAPrivateKey key = (DSAPrivateKey) keyMap.get(DSA_PRIVATE_KEY);
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
        Map<String, Object> keyMap = DSASignTureUtil.initKey();
        byte[] rsaPublicKey = DSASignTureUtil.getPublicKey(keyMap);
        byte[] rsaPrivateKey = DSASignTureUtil.getPrivateKey(keyMap);
        System.out.println("privateKey:" + DSASignTureUtil.bytesToHexString(rsaPrivateKey));
        System.out.println("publicKey:" + DSASignTureUtil.bytesToHexString(rsaPublicKey));

        //sign
        byte[] sign = DSASignTureUtil.sign(DATA.getBytes(), rsaPrivateKey);
        System.out.println("RSA Sign:"+ DSASignTureUtil.bytesToHexString(sign));

        //Verify
        boolean isValidate = DSASignTureUtil.verify(DATA.getBytes(), rsaPublicKey, sign);
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
