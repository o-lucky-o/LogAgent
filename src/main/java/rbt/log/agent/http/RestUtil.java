package rbt.log.agent.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

public class RestUtil {

    private static Logger logger = Logger.getLogger(RestUtil.class);

    /**
     * 发送get请求到rest接口
     */
    public static String sendgetMethod(String url) throws IOException {
        StringBuffer string = new StringBuffer();
        BufferedReader rd = null;
        String line = null;
        try {
            URL restServiceURL = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) restServiceURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoInput(true);
            conn.setUseCaches(false);
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP POST Request Failed with Error code :"
                        + conn.getResponseCode());
            }
            InputStream inStrm = conn.getInputStream();
            rd = new BufferedReader(new InputStreamReader(inStrm, "UTF-8"));
            while ((line = rd.readLine()) != null) {
                string.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string.toString();
    }

    /**
     * get 请求调用方法
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String getMethod(String url) throws IOException {
        StringBuffer string = new StringBuffer();
        try {
            URL restServiceURL = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            if (httpURLConnection.getResponseCode() != 200) {
                throw new RuntimeException("HTTP POST Request Failed with Error code :"
                        + httpURLConnection.getResponseCode());
            }
            InputStream inStrm = httpURLConnection.getInputStream();
            byte[] b = new byte[1024];
            int length = -1;
            while ((length = inStrm.read(b)) != -1) {
                string.append(new String(b, 0, length));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string.toString();
    }

    /**
     * Post调用方法
     *
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    public static String postMethod(String url, JSONObject json) throws IOException {
        StringBuffer string = new StringBuffer();
        try {
            URL restServiceURL = new URL(url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            if (httpURLConnection.getResponseCode() != 200) {
                throw new RuntimeException("HTTP POST Request Failed with Error code :"
                        + httpURLConnection.getResponseCode());
            }
            InputStream inStrm = httpURLConnection.getInputStream();
            byte[] b = new byte[1024];
            int length = -1;
            while ((length = inStrm.read(b)) != -1) {
                string.append(new String(b, 0, length));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string.toString();
    }

    public static String postJsonMethod(String url, JSONObject data) {
        StringBuffer result = null;
        String json = "";
        if (data == null) {
            json = "{}";
        } else {
            json = data.toString();
        }
        HttpURLConnection conn = null;
        OutputStreamWriter out = null;
        BufferedReader reader = null;
        try {
            URL realUrl = new URL(url);
            result = new StringBuffer();
            conn = (HttpURLConnection) realUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Accept", "application/json");
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            //POST请求
            out = new OutputStreamWriter(conn.getOutputStream());
            out.write(json);
            out.flush();

            //读取响应
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String lines = null;
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes());
                result.append(lines);
            }
            reader.close();
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常!" + e);
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result.toString();
    }


    /**
     * 发送HttpPost请求
     *
     * @param url   请求URL
     * @param param url 请求参数
     * @return 请求响应结果
     */
    public static String sendPost(String url, String param) {
        StringBuffer result = null;
        HttpURLConnection conn = null;
        PrintWriter out = null;
        BufferedReader reader = null;
        try {
            URL realUrl = new URL(url);
            result = new StringBuffer();
            conn = (HttpURLConnection) realUrl.openConnection();
            conn.setRequestProperty("accept", "//*"); // 第一个 / 是 *
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Accept", "application/json");
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);

            //POST请求
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();


            //读取响应
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            logger.info("【4：post请求：】 请求发送完毕！");
            String lines = null;
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes());
                result.append(lines);
            }
            //reader.close();
            //conn.disconnect();
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常!" + e);
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result.toString();
    }


}