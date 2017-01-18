package com.alankin.hihttp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

/**
 * Created by alankin on 2017/1/18.
 * 连接类
 */
public class Connect {
    HttpClient httpClient;
    Request request;

    public Connect(HttpClient httpClient) {
        this.httpClient = httpClient;
        request = this.httpClient.request;
    }

    synchronized byte[] connect() {
        byte[] bytes = null;
        if (request == null) {
            Utils.log("request is null");
            throw new IllegalArgumentException("request is null");
        }

        URL url = null;
        String method = request.method.getMethod();
        int connectTimeOut = httpClient.connectTimeOut;
        int readTimeOut = httpClient.readTimeOut;
        Map<String, String> headers = request.headers;
        Params params = request.params;
        String path = request.url;
        byte[] dataBytes = null;//写入数据

        //判断请求方式
        try {
            if (method.equalsIgnoreCase(Method.GET.getMethod())) {//get请求
                url = new URL(path + "/" + Utils.Param2String(params));
            } else if (request.method.getMethod().equalsIgnoreCase(Method.POST.getMethod())) {//post请求
                url = new URL(path);
                dataBytes = Utils.Param2ByteArr(params);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (url == null) {
            Utils.log("url is null");
            throw new IllegalArgumentException("url is null");
        }
        //URLConnection参数设置
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(method);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setConnectTimeout(connectTimeOut);
            httpURLConnection.setReadTimeout(readTimeOut);
            httpURLConnection.setUseCaches(false);
            if (headers != null && !headers.isEmpty()) {
                Set<Map.Entry<String, String>> entries = headers.entrySet();
                for (Map.Entry<String, String> entry : entries) {
                    httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            //建立socket连接
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(dataBytes);
                outputStream.flush();
                InputStream inputStream = httpURLConnection.getInputStream();
                byte[] buff = new byte[1024];
                int length = inputStream.read(buff);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (length != -1) {
                    byteArrayOutputStream.write(buff);
                }
                //最终获得的字节数组
                bytes = byteArrayOutputStream.toByteArray();
            } else {
                Utils.log("connect error!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

}
