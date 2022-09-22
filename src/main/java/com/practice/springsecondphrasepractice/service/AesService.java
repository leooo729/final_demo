package com.practice.springsecondphrasepractice.service;

import com.mysql.cj.util.Base64Decoder;
import com.practice.springsecondphrasepractice.controller.dto.request.AesRequest;
import com.practice.springsecondphrasepractice.controller.dto.response.AesResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class AesService {

    public AesResponse ecbEncode(AesRequest request, String key) throws Exception {
        byte[] raw = key.getBytes("utf-8");  //該字串位元組陣列
        SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");//產生金鑰 //AES三種金鑰長度（128,192,256 bit）
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 生成Cipher物件, 產生轉換的模式："演算法/模式/補碼方式"
        cipher.init(Cipher.ENCRYPT_MODE, keySpec); //初始化
        byte[] encrypted = cipher.doFinal(request.getRequest().getBytes("utf-8")); //得到加密後的位元組陣列

        return new AesResponse(new String(new Base64().encode(encrypted)));//ASE64做轉碼功能，同時能起到2次加密的作用。
    }

    public AesResponse ecbDecode(AesRequest request, String key) throws Exception {
        byte[] raw = key.getBytes("utf-8");
        SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decrypted = new Base64().decode(request.getRequest());//先用base64解密
        byte[] original = cipher.doFinal(decrypted);

        return new AesResponse(new String(original, "Utf-8"));
    }
    public AesResponse cbcEncode(AesRequest request,String key) throws Exception{
        byte[] raw = key.getBytes("utf-8");
        SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec("2021121018304400".getBytes());//使用CBC模式，需要一個向量iv，可增加加密演算法的強度
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);  // 初始化:解密模式，向量
        byte[] encrypted = cipher.doFinal(request.getRequest().getBytes());
        return new AesResponse(new String(new Base64().encode(encrypted)));

    }
    public AesResponse cbcDecode(AesRequest request,String key) throws Exception {
        byte[] raw = key.getBytes("utf-8");
        SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec("2021121018304400".getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
        byte[] encrypted1 = new Base64().decode(request.getRequest());//先用base64解密
        byte[] original = cipher.doFinal(encrypted1);
        return new AesResponse(new String(original,"Utf-8"));
    }
}

