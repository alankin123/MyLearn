package com.alankin.hihttp;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by alankin on 2017/1/18.
 * 转换工具类
 */
public class Utils {
    /**
     * Param转字节数组
     *
     * @param params
     */
    public static byte[] Param2ByteArr(Params params) {
        return HashMap2ByteArr(params);
    }

    /**
     * Param转字节数组
     *
     * @param params
     */
    public static String Param2String(Params params) {
        return HashMap2String(params);
    }

    /**
     * Headers转字节数组
     *
     * @param headers
     * @return
     */
    public static byte[] Headers2ByteArr(Headers headers) {
        return HashMap2ByteArr(headers);
    }

    /**
     * Headers转字节数组
     *
     * @param headers
     * @return
     */
    public static String Headers2String(Headers headers) {
        return HashMap2String(headers);
    }


    public static String HashMap2String(HashMap<String, String> map) {
        String str = "";
        if (map != null && !map.isEmpty()) {
            Set<Map.Entry<String, String>> entries = map.entrySet();
            StringBuffer stringBuffer = new StringBuffer();
            int count = 0;
            try {
                for (Map.Entry<String, String> entry : entries) {

                    if (count != 0) {
                        stringBuffer.append("&"
                                + URLEncoder.encode(entry.getKey(), "UTF-8")
                                + "="
                                + URLEncoder.encode(entry.getValue(), "UTF-8")
                        );
                    } else {
                        stringBuffer.append(URLEncoder.encode(entry.getKey(), "UTF-8")
                                + "="
                                + URLEncoder.encode(entry.getValue(), "UTF-8")
                        );
                    }
                    count++;
                }
                str = stringBuffer.toString();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public static byte[] HashMap2ByteArr(HashMap<String, String> map) {
        byte[] bytes = null;
        if (map != null && !map.isEmpty()) {
            try {
                bytes = HashMap2String(map).getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    /*------------------Log工具类--------------------*/
    public static void log(String s) {
        if (Config.isLog) {
            Log.e(Config.TAG, s);
        }
    }
}
