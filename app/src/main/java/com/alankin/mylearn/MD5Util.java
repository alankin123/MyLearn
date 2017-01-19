package com.alankin.mylearn;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by alankin on 2016/8/4.
 */
public class MD5Util {
    /**
     * 将字符串md5化
     *
     * @param str
     *            要转化的字符串
     * @return 转化以后的字符串
     */
    public static String MD5(String str) {

        StringBuffer buf = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();

            int i;

            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            // System.out.println("result: " + buf.toString());// 32位的加密

            // System.out.println("result: " + buf.toString().substring(8,
            // 24));// 16位的加密

        } catch (NoSuchAlgorithmException e) {
        }
        return buf.toString();
    }


    /**
     * true：合法请求  false ：非法请求
     * @return
     */
    public static String getSignature(String dev_server_secret,Object ... params){
        if(params.length == 0 ){
            return null;
        }
        List<String> list = new ArrayList<String>();
        for (Object param : params) {
            if(param != null){
                list.add(param.toString());
            }
        }
        return getSignature(list,dev_server_secret);
    }

    public static String getSignature(Map<String, String> params, String dev_server_secret){
        // 先将参数以其参数名的字典序升序进行排序
        Map<String, String> sortedParams = new TreeMap<String, String>(params);
        Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();
        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder basestring = new StringBuilder();
        for (Map.Entry<String, String> param : entrys) {
            basestring.append(param.getValue());
        }
        basestring.append(dev_server_secret);
        System.out.println(basestring.toString());
        //对待签名串求签
        return MD5(basestring.toString());
    }

    public  static String getSignature(List<String> params, String dev_server_secret){
        // 先将参数以其参数名的字典序升序进行排序
        Collections.sort(params);
        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder basestring = new StringBuilder();
        for (Object param : params) {
            basestring.append(param);
        }
        basestring.append(dev_server_secret);
        System.out.println(basestring.toString());
        //对待签名串求签
        return MD5(basestring.toString());
    }
}
