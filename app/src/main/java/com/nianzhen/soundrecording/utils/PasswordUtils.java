package com.nianzhen.soundrecording.utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Created by Administrator on 2016/1/6.
 */
public class PasswordUtils {

    private final static String KEY = "nianzhen";


    public final static int CAPITAL = 1;//大写字母

    public final static int LOWERCASE = 2;//小写字母

    public final static int LATTER = 3;//字母

    public final static int NUMBER = 4;//数字

    public final static int LETTERSANDNUMBERS = 5;//字母和数字

    public final static int SPECIALCHARACTERS = 6;//特殊字符

    public final static int ALL = 7;//全部


    /**
     * 加密
     *
     * @param datasource String
     * @return String
     */
    public static String encrypt(String datasource) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(KEY.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            return new String(cipher.doFinal(datasource.getBytes()));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param string
     * @return string
     * @throws Exception
     */
    public static String decrypt(String string) throws Exception {
        SecureRandom random = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(KEY.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        return new String(cipher.doFinal(string.getBytes()));
    }

    private static List<String> capitalList = new ArrayList<>();
    private static List<String> lowercaseList = new ArrayList<>();
    private static List<String> numberList = new ArrayList<>();
    private static List<String> specialList = new ArrayList<>();


    static {
        for (int i = 65; i < 91; i++) {
            char b = (char) i;
            char c = (char) (i + 32);
            System.out.print(String.valueOf(b) + String.valueOf(c));
            capitalList.add(i - 65, String.valueOf(b));
            lowercaseList.add(i - 65, String.valueOf(c));
        }
        for (int i = 0; i < 10; i++) {
            char b = (char) (i + 48);
            System.out.print(String.valueOf(b));
            numberList.add(i, String.valueOf(b));
        }
    }

    // 测试主函数
    public static void main(String args[]) {
        for (int i = 0; i < 21; i++) {
            System.out.println(capitalList.get(i));
        }
    }

}
