package com.common.utils;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HttpConnection网络请求工具类
 * @author arvin
 * @date 2016年8月18日下午4:01:48
 */
public class HttpConnection {
	
	private static String TAG = "HttpUtils";
    private static StringBuilder sb2;
    private static final int readTimeOut = 20 * 1000; // 读取超时
    private static final int connectTimeout = 20 * 1000; // 超时时间

    /**
     * 网络请求超时
     */
    public static final String TIME_OUT = "TIME_OUT";

    /**
     * url地址错误
     */
    public static final String FILE_NOT_FOUND = "FILE_NOT_FOUND";

    /**
     * 服务器找不到
     */
    public static final String SERVER_NOT_FOUND = "SERVER_NOT_FOUND";

    /**
     * 向指定URL发送GET方法的请求
     *
     * param url    发送请求的URL
     * param params 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, Map<String, String> params) {
        return sendGet(url, params, 0);
    }

    /**
     * 指定URL发送GET方法的请求
     *
     * param url
     * param params
     * param timeout
     * return
     */
    public static String sendGet(String url, Map<String, String> params, int timeout) {
        String param = paramsConvertString(params);
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            if (!"".equals(param)) urlNameString += "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            if (timeout > 0) {
                connection.setReadTimeout(timeout * 1000);
                connection.setConnectTimeout(timeout * 1000);
            } else {
                connection.setReadTimeout(readTimeOut);
                connection.setConnectTimeout(connectTimeout);
            }
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        }
        catch (UnknownHostException e){
            System.out.println("未知主机" + e);
            e.printStackTrace();
        }
        catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * param url    发送请求的 URL
     * param params 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, Map<String, String> params) {
        return sendPost(url, params, 0);
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * param url
     * param params
     * param timeout
     * return
     */
    public static String sendPost(String url, Map<String, String> params, int timeout) {
        String param = paramsConvertString(params);
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            if (timeout > 0) {
                conn.setReadTimeout(timeout * 1000);//设置连接主机超时（单位：毫秒）
                conn.setConnectTimeout(timeout * 1000);//设置从主机读取数据超时（单位：毫秒）
            } else {
                conn.setReadTimeout(readTimeOut);
                conn.setConnectTimeout(connectTimeout);
            }
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            if(conn.getOutputStream()==null){
                result = FILE_NOT_FOUND;
                return result;
            }
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            if (!"".equals(param)) out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (FileNotFoundException e) {
            result = FILE_NOT_FOUND;
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            result = TIME_OUT;
            e.printStackTrace();
        }catch (SocketException e){
            result = SERVER_NOT_FOUND;
            e.printStackTrace();
        }
        catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 通过拼接的方式构造请求内容，实现参数传输以及文件传输
     *
     * param actionUrl
     * param params
     * param files
     * return
     * @throws IOException
     */
    public static String postImg(String actionUrl, Map<String, String> params, Map<String, File> files) {
        HttpURLConnection conn = null;
        DataOutputStream outStream = null;
        InputStream is = null;
        InputStream in = null;
        try {
            String BOUNDARY = java.util.UUID.randomUUID().toString();
            String PREFIX = "--", LINEND = "\r\n";
            String MULTIPART_FROM_DATA = "multipart/form-data";
            String CHARSET = "UTF-8";

            URL uri = new URL(actionUrl);
            conn = (HttpURLConnection) uri.openConnection();
            conn.setReadTimeout(120 * 1000); // 缓存的最长时间
            conn.setDoInput(true);// 允许输入
            conn.setDoOutput(true);// 允许输出
            conn.setUseCaches(false); // 不允许使用缓存
            conn.setRequestMethod("POST");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Charsert", CHARSET);
            conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA
                    + ";boundary=" + BOUNDARY);

            // 首先组拼文本类型的参数
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINEND);
                sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
                sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
                sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
                sb.append(LINEND);
                sb.append(entry.getValue());
                sb.append(LINEND);
            }

            outStream = new DataOutputStream(conn
                    .getOutputStream());
            outStream.write(sb.toString().getBytes());

            // 发送文件数据
            if (files != null) {
                for (Map.Entry<String, File> file : files.entrySet()) {
                    StringBuilder sb1 = new StringBuilder();
                    sb1.append(PREFIX);
                    sb1.append(BOUNDARY);
                    sb1.append(LINEND);
                    sb1.append("Content-Disposition: form-data; name=\"" + file.getKey() + "\"; filename=\"" + file.getKey() + "\"" + LINEND);
                    sb1.append("Content-Type: application/octet-stream; charset=" + CHARSET + LINEND);
                    sb1.append(LINEND);
                    outStream.write(sb1.toString().getBytes());

                    is = new FileInputStream(file.getValue());
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = is.read(buffer)) != -1) {
                        outStream.write(buffer, 0, len);
                    }

                    is.close();
                    outStream.write(LINEND.getBytes());
                }

                // 请求结束标志
                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
                outStream.write(end_data);
                outStream.flush();
                // 得到响应码
                sb2 = new StringBuilder();
                int res = conn.getResponseCode();
                if (res == 200) {
                    in = conn.getInputStream();
                    int ch;
                    while ((ch = in.read()) != -1) {
                        sb2.append((char) ch);
                    }
                }else{
                    System.out.println("服务器返回错误码："+res);
                }
                outStream.close();
                conn.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
                if (is != null) {
                    is.close();
                }
                if (in != null) {
                    in.close();
                }
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        String result = null;
        try {
            if(sb2!=null && !sb2.equals(""))
                result = StringUtils.toUTF8(sb2.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 将Map中的参数转成字符串形式
     *
     * param params
     * return
     */
    public static String paramsConvertString(Map<String, String> params) {
        if (params == null) return "";
        String paramStr = "";
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String val = entry.getValue();
            if(val==null){
                paramStr = paramStr + "&";
                paramStr = paramStr + (String)entry.getKey() + "=";
            }
            else if(val==""){
                paramStr = paramStr + "&";
                paramStr = paramStr + (String)entry.getKey() + "=\"\"";
            }
            else if (val.contains("###")) {//判断是否为参数数组
                String[] arr = val.split("###");
                val = "";
                for (String str : arr) {
                    val = str;
                    try {
                        val = URLEncoder.encode(val, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    paramStr += "&";
                    paramStr += entry.getKey() + "=" + val;
                }
            } else {
                try {
                    val = URLEncoder.encode(val, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                paramStr += "&";
                paramStr += entry.getKey() + "=" + val;
            }

        }
        return paramStr.replaceFirst("&", "");
    }
    
    /**
     * post提交xml串
     * @param urlPath
     * @param xml
     * @return
     */
    public static String sendPostXml(String urlPath,String xml){
    	
    	try {  

            byte[] bb=xml.getBytes();  
            //请求地址  
            URL url = new URL(urlPath);  
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
            conn.setRequestMethod("POST");  
            conn.setConnectTimeout(5 * 1000);//设置超时的时间  
            conn.setDoInput(true);  
            conn.setDoOutput(true);//如果通过post提交数据，必须设置允许对外输出数据  
            conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");  
            conn.setRequestProperty("Content-Length", String.valueOf(bb.length));  
            conn.connect();  
             DataOutputStream out = new DataOutputStream(conn  
                        .getOutputStream());  
                out.writeBytes(xml); //写入请求的字符串  
                out.flush();  
                out.close();  
        //请求返回的状态   
        if(conn.getResponseCode() ==200) {  
            //请求返回的数据  
            InputStream in=conn.getInputStream();  
            String a=null;  
             try {  
                byte[] data1 = new byte[in.available()];  
                in.read(data1);  
                //转成字符串  
                a = new String(data1);  
                return a;
            } catch (Exception e1) {  
                // TODO Auto-generated catch block  
                e1.printStackTrace();  
            }  
        } 
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    	return null;
    }


    public static void main(String[] args) {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("name", "张三");
//        String str = HttpConnection.paramsConvertString(params);
//        System.out.println(str);
    	
    	
    	Map<String,String> params = new HashMap<String,String>();
		params.put("userName", AES.encrypt("csfz"));
		params.put("type", "0");
		params.put("tc_orderId","1");
		params.put("tc_detailInfo", "您的1010000516473132(特锐界3496CC越野车2FMDK4KC)订单已被一马当先科技有限公司拖车公司接收");
		params.put("tc_pushType", "1");
		try {
			String result = HttpConnection.sendPost("http://192.168.5.61:8018/api/jPush/trailerJPush",params);
			System.out.println("finish\t"+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
