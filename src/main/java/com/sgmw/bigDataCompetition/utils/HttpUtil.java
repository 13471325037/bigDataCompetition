package com.sgmw.bigDataCompetition.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

/**
 * @author weitongming
 * @Classname HttpUtil
 * @Description http
 * @Date 2019/12/28 13:58
 * @Version V1.0
 */
public class HttpUtil {

    private static CloseableHttpClient httpclient = HttpClients.createDefault();

    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 60000;
    private static final String CHARSET_UTF8 = "UTF-8";

    static {
        /*// 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());*/

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        // 在提交请求之前 测试连接是否可用
        configBuilder.setStaleConnectionCheckEnabled(true);
        requestConfig = configBuilder.build();
    }

    public static String get(String url) throws Exception{
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        String resp = null;
        try {
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                resp = EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            response.close();
        }
        return resp ;
    }

    /**
     * 发送 GET 请求（HTTP），不带输入数据
     *
     * @param url 请求url
     * @return String
     */
    public static String doGet(String url) {
        return doGet(url, new HashMap<String, String>());
    }

    /**
     * 发送 GET 请求（HTTPS），K-V形式
     *
     * @param url        请求url
     * @param headParams 请求参数
     * @return String
     */
    public static String doGet(String url, Map<String, String> headParams) {
        if (isHttpsRequest(url)) {
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setDefaultRequestConfig(requestConfig).build();
            return doGet(url, headParams, httpClient);
        } else {
            return doGet(url, headParams, HttpClients.createDefault());
        }
    }

    private static String doGet(String url, Map<String, String> headParams, CloseableHttpClient httpclient) {
        String apiUrl = url;
        HttpResponse response = null;
        StringBuffer param = new StringBuffer();
        apiUrl += param;
        String result = null;
        try {
            HttpGet httpPost = new HttpGet(apiUrl);
            for (String key : headParams.keySet()) {
                httpPost.addHeader(key, headParams.get(key));
            }
            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            result = EntityUtils.toString(entity, CHARSET_UTF8);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 发送 POST 请求（HTTP），不带输入数据
     *
     * @param url 请求url
     * @return String
     */
    public static String doPost(String url) {
        return doPost(url, null, new HashMap<String, String>());
    }


    /**
     * 发送 SSL POST 请求（HTTPS），K-V形式
     *
     * @param apiUrl     API接口URL
     * @param headParams 头部参数
     * @param params     参数map
     * @return
     */
    public static String doPost(String apiUrl, Map<String, String> headParams, Map<String, String> params) {
        if (isHttpsRequest(apiUrl)) {
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setDefaultRequestConfig(requestConfig).build();
            return doPost(apiUrl, headParams, params, httpClient);
        } else {
            return doPost(apiUrl, headParams, params, HttpClients.createDefault());
        }
    }

    /**
     * 发送 SSL POST 请求（HTTPS），JSON形式
     *
     * @param apiUrl     API接口URL
     * @param headParams 头部参数
     * @param body       JSON对象
     * @return
     */
    public static String doPost(String apiUrl, Map<String, String> headParams, String body) {
        if (isHttpsRequest(apiUrl)) {
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setDefaultRequestConfig(requestConfig).build();
            return doPost(apiUrl, headParams, body, httpClient);
        } else {
            return doPost(apiUrl, headParams, body, HttpClients.createDefault());
        }
    }

    private static String doPost(String apiUrl, Map<String, String> headParams, String body, CloseableHttpClient httpClient) {
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        String httpStr = null;

        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(body, CHARSET_UTF8);//解决中文乱码问题
            stringEntity.setContentEncoding(CHARSET_UTF8);
            stringEntity.setContentType("application/json");
            if (headParams != null) {
                for (Map.Entry<String, String> entry : headParams.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, CHARSET_UTF8);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    private static String doPost(String apiUrl, Map<String, String> headParams, Map<String, String> params, CloseableHttpClient httpClient) {
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        String httpStr = null;

        try {
            httpPost.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
            if (headParams != null) {
                for (Map.Entry<String, String> entry : headParams.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            for (Map.Entry<String, String> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
                pairList.add(pair);
            }

            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(CHARSET_UTF8)));
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, CHARSET_UTF8);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 SSL PUT 请求（HTTPS），K-V形式
     *
     * @param apiUrl     API接口URL
     * @param headParams 头部参数
     * @return
     */
    public static String doPut(String apiUrl, Map<String, String> headParams) {
        if (isHttpsRequest(apiUrl)) {
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setDefaultRequestConfig(requestConfig).build();
            return doRequest(headParams, httpClient, new HttpPut(apiUrl));
        } else {
            return doRequest(headParams, HttpClients.createDefault(), new HttpPut(apiUrl));
        }
    }

    /**
     * 发送 SSL PUT 请求（HTTPS），K-V形式
     *
     * @param apiUrl     API接口URL
     * @param headParams 头部参数
     * @return
     */
    public static String doPut(String apiUrl, Map<String, String> headParams, byte[] paramBytes) {
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setDefaultRequestConfig(requestConfig).build();
        CloseableHttpResponse response = null;
        String httpStr = null;
        HttpPut httpPut = new HttpPut(apiUrl);
        try {
            httpPut.setConfig(requestConfig);
            if (headParams != null) {
                for (Map.Entry<String, String> entry : headParams.entrySet()) {
                    httpPut.addHeader(entry.getKey(), entry.getValue());
                }
            }
            httpPut.setEntity(new ByteArrayEntity(paramBytes));
            response = httpClient.execute(httpPut);
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, CHARSET_UTF8);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 SSL DELETE 请求（HTTPS），K-V形式
     *
     * @param apiUrl     API接口URL
     * @param headParams 头部参数
     * @return
     */
    public static String doDelete(String apiUrl, Map<String, String> headParams) {
        if (isHttpsRequest(apiUrl)) {
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setDefaultRequestConfig(requestConfig).build();
            return doRequest(headParams, httpClient, new HttpDelete(apiUrl));
        } else {
            return doRequest(headParams, HttpClients.createDefault(), new HttpDelete(apiUrl));
        }
    }

    private static String doRequest(Map<String, String> headParams, CloseableHttpClient httpClient, HttpRequestBase httpRequestBase) {
        CloseableHttpResponse response = null;
        String httpStr = null;

        try {
            httpRequestBase.setConfig(requestConfig);
            if (headParams != null) {
                for (Map.Entry<String, String> entry : headParams.entrySet()) {
                    httpRequestBase.addHeader(entry.getKey(), entry.getValue());
                }
            }
            response = httpClient.execute(httpRequestBase);
            int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, CHARSET_UTF8);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    public static HttpEntity doReq4Get(String url, Map<String, String> headParams) {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setDefaultRequestConfig(requestConfig).build();
        CloseableHttpResponse response = null;
        try {
            httpGet.setConfig(requestConfig);
            httpGet.setHeader("content-type", "application/json");
            if (headParams != null) {
                for (Map.Entry<String, String> entry : headParams.entrySet()) {
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }
            }
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            if (response != null) {
//                try {
//                    EntityUtils.consume(response.getEntity());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        return null;
    }

    /**
     * 判断是否为https请求
     */
    public static boolean isHttpsRequest(String url) {
        return !(url == null || url.trim().equals("")) && url.toLowerCase().startsWith("https");
    }

    /**
     * 创建SSL安全连接
     *
     * @return
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                public void verify(String host, SSLSocket ssl) throws IOException {
                }

                public void verify(String host, X509Certificate cert) throws SSLException {
                }

                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                }
            });
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }
}
