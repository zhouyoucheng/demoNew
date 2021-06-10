package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by supaur on 2021/5/14 9:24
 *
 * @author supaur
 */
@Slf4j
public class HttpInvokeUtils {

    private static PoolingHttpClientConnectionManager cm = null;
    private volatile static CloseableHttpClient httpClient = null;

    public static CloseableHttpClient getHttpClient() {
        if (null == httpClient) {
            synchronized (HttpInvokeUtils.class) {
                if (null == httpClient) {
                    RequestConfig config = RequestConfig.custom().setConnectTimeout(60 * 1000)
                            .setSocketTimeout(60 * 1000)
                            .setConnectionRequestTimeout(2000)
                            .build();
                    httpClient = HttpClients.custom().setMaxConnTotal(200)
                            .setMaxConnPerRoute(100)
                            .setConnectionManager(cm).setSSLHostnameVerifier(new NoopHostnameVerifier())
                            .setDefaultRequestConfig(config).setRetryHandler(new
                                    DefaultHttpRequestRetryHandler()).build();
                }
            }
        }
        return httpClient;
    }

    public static Object get(String requestUrl, Map<String, Object> entityMap) throws Exception {
        StringBuilder url = new StringBuilder();
        if (!MapUtils.isEmpty(entityMap)) {
            List<String> keySets = Lists.newArrayList(entityMap.keySet());
            for (int i = 0; i < keySets.size(); i++) {
                String k = keySets.get(i);
                Object v = entityMap.get(k);
                if (i == 0) {
                    url.append("?");
                } else {
                    url.append("&");
                }
                url.append(k)
                        .append("=")
                        .append(v);
            }
            requestUrl = url.toString();
        }

        HttpGet httpget = new HttpGet(requestUrl);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String response = getHttpClient().execute(httpget, responseHandler);
        JSONObject jsonObject = JSON.parseObject(response);
        return jsonObject;
    }

    public static Object post(String accessUrl, Map<String, String> headers, Map<String, String> entityMap) throws Exception {
        HttpPost post = new HttpPost(accessUrl);
        //添加header信息, 没有则不添加
        if (!CollectionUtils.isEmpty(headers)) {
            for (Map.Entry<String, String> var : headers.entrySet()) {
                post.setHeader(var.getKey(), var.getValue());
            }
        }
        if (!CollectionUtils.isEmpty(entityMap)) {
            StringEntity entity = new StringEntity(JSON.toJSONString(entityMap));
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/x-www-form-urlencoded");
            post.setEntity(entity);
        }
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String execute = getHttpClient().execute(post, responseHandler);
        JSONObject jsonObject = JSON.parseObject(execute);
        return jsonObject;
    }
}
