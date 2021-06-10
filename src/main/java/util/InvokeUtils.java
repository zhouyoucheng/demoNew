package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author supaur
 * @date 调用应用工具类
 */
public class InvokeUtils {

    final static String URL = "http://activity-ops.chery-sit.supaur.tech/api/v1/activity/handler/operate";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        // 1.入参列表
        List<Object> objects = new ArrayList<>();
        List<Long> arrayList = new ArrayList<>();
        arrayList.add(2297L);
        arrayList.add(2299L);
        objects.add(arrayList);

        String s = JSON.toJSONString(arrayList);
        System.out.println(s);
        List<Integer> integers = JSONArray.parseArray(s, Integer.class);
        System.out.println(integers);
        // 2.入参列表类型（list类型把元素类型带上）
        List<String> invokeParamTypes = Lists.newArrayList("java.util.List_java.lang.Long");
        Req reqParams = getReqParams(
                "com.supaur.activity.biz.service.ActivityGeneralBizService",
                "findActivityItemInfoByItemIds",
                "com.supaur.activity.biz.service.ActivityGeneralBizService",
                objects,
                invokeParamTypes
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> stringHttpEntity = new HttpEntity<>(JSONObject.toJSONString(reqParams), headers);
        ResponseEntity<JSONObject> response = restTemplate.exchange(URL, HttpMethod.POST, stringHttpEntity, JSONObject.class);
        System.out.println(Optional.ofNullable(response.getBody()).map(v -> v.get("data")).map(v -> JSON.toJSONString(v)).orElse(null));
    }

    public static Req getReqParams(String beanName, String methodName, String interfaceName, List<Object> invokeParams,
                                   List<String> invokeParamTypes) {
        Req req = new Req();
        req.setKey1("key1");
        req.setKey2("b27b985ac317c6b281fb671c87c0e8b6");
        req.setName1(beanName);
        req.setName2(methodName);
        // 是否是远程调用
        req.setRemote("remote");
        req.setInterfaceName(interfaceName);
        req.setObjects(invokeParams);
        req.setObjectTypes(invokeParamTypes);
        return req;
    }

    @Data
    static class Req {
        private String key1;
        private String key2;
        private String name1;
        private String name2;
        private String remote;
        private String interfaceName;
        private List<Object> objects;
        private List<String> objectTypes;
    }

}
