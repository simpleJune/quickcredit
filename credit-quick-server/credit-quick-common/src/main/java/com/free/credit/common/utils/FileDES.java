/*
 * 文件名: DES3.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * @类描述：文件DES3加解密
 * @创建人：luohao
 * @创建时间：2016年5月6日
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class FileDES {

    // 加密标记
    public static final String ENCRYPT_MODE = "0";

    // 解密标记
    public static final String DECRYPT_MODE = "1";

    /**
     * 方法描述：初始化密文
     * 
     * @param secretkeys
     * @param mode
     * @return
     */
    public static Cipher initCipher(String secretkeys, String mode) {
        Cipher cipher = null;
        try {
            // SecretKey负责保存对称密钥
            SecretKey deskey;
            KeySpec dks = new DESedeKeySpec(secretkeys.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            // 生成密钥
            deskey = keyFactory.generateSecret(dks);
            cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            if (ENCRYPT_MODE.equals(mode)) {
                // 根据密钥，对Cipher对象进行初始化,ENCRYPT_MODE表示加密模式
                cipher.init(Cipher.ENCRYPT_MODE, deskey);
            } else if (DECRYPT_MODE.equals(mode)) {
                // 根据密钥，对Cipher对象进行初始化,DECRYPT_MODE表示解密模式
                cipher.init(Cipher.DECRYPT_MODE, deskey);
            }
            return cipher;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return cipher;
    }

    /**
     * 方法描述：文件加解密
     * @param uri
     *            ：源文件路径
     * @param dist
     *            ：加密或者解密后的文件路径
     * @param secretkeys
     *            ：加解密串
     * @param mode
     *            ：加密、解密标记
     */
    public static void encryptFile(String uri, String dist, String secretkeys, String mode) {
        InputStream in = null;
        OutputStream out = null;
        CipherInputStream cis = null;
        try {
            // 一次读多个字节
            byte[] buffer = new byte[1024];
            int r = 0;
            Cipher clipher = initCipher(secretkeys, mode);
            in = new BufferedInputStream(new FileInputStream(new File(uri)));
            cis = new CipherInputStream(in, clipher);
            File fileout = new File(dist);
            if (!fileout.exists()) {
                fileout.createNewFile();
            }
            out = new FileOutputStream(fileout, false);
            r = cis.read(buffer);
            // 读入多个字节到字节数组中，byteread为一次读入的字节数
            while (r != -1) {
                out.write(buffer, 0, r);
                r = cis.read(buffer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static void main(String[] args) {

        String secretkeys = "asdfghjkl123456712345d3f";
        // encryptFile("E:\\test\\0512\\DRAWINGS_HC_HYL_20160312.txt",
        // "E:\\test\\0512\\0512_1.txt", secretkeys, "0");
        // encryptFile("E:\\test\\0512\\0512_1.txt","E:\\test\\0512\\0512_2.txt",
        // secretkeys, "1");
        encryptFile("E:\\test\\0512\\TRADE_HC_20170123-M.txt", "E:\\test\\0512\\0512_3.txt", secretkeys, "0");
        encryptFile("E:\\test\\0512\\0512_3.txt", "E:\\test\\0512\\0512_33.txt", secretkeys, "1");
    }

}
