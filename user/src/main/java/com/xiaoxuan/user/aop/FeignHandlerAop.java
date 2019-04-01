package com.xiaoxuan.user.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import feign.Request;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Aspect
@Configuration
public class FeignHandlerAop {


    private static Logger logger = LoggerFactory.getLogger(FeignHandlerAop.class);

    @Around(value = "execution(* feign.Client.execute(..)) ")
    public Object afterRequestCreate(ProceedingJoinPoint joinPoint) throws Throwable {
        Object args[] = joinPoint.getArgs();
        Object newArgs[] = new Object[args.length];
        int i = 0;
        for (Object arg : args) {
            if (arg instanceof Request) {
                Request request = (Request) arg;
                if (request.httpMethod().name().equals("GET")) {
                    Request.Body body = request.requestBody();
                    String queryParams = body.asString();
                    if ("Binary data".equals(queryParams)) {
                        newArgs[i++] = arg;
                    } else {
                        //只需解析GET请求使用dto接收参数的情况，不考虑集合或者dto数组的情况，因为该情况都应该使用post发送请求，且使用requestBody接收参数
                        JSONObject jsonObject = JSON.parseObject(queryParams);
                        StringBuilder params = transeJson2KeyValuePairs(jsonObject, null);
                        String url = request.url() + "?" + params.deleteCharAt(params.length() - 1);
                        Map<String, Collection<String>> originHeaders = request.headers();
                        Map<String, Collection<String>> newHeaders = new HashMap<>();
                        originHeaders.forEach((key, value) -> newHeaders.put(key, value.stream().filter(item -> "Content-Type".equals(key) && item.indexOf("application/json") < 0).collect(Collectors.toList())));
                        logger.info("处理feignclient发送get请求使用dto接收参数问题，拼接的url:{}", url);
                        newArgs[i++] = Request.create(request.httpMethod(), url, newHeaders, Request.Body.empty());
                    }
                } else {
                    newArgs[i++] = arg;
                }
            } else {
                newArgs[i++] = arg;
            }
        }
        Object result = joinPoint.proceed(newArgs);
        return result;
    }

    /**
     * JSONObject转化为键值对：
     * {
     * id:1,
     * name:'faker',
     * inner:
     * {
     * age:1,
     * sex:'男'
     * }
     * }
     * 转为
     * id=1 & name=faker & inner.age=1 & inner.sex=男
     *
     * @Author : 萧玄
     * @Date: 17:01 2019/3/28
     */
    private StringBuilder transeJson2KeyValuePairs(JSONObject jsonObject, String prefix) {
        Set<String> keysets = jsonObject.keySet();
        StringBuilder params = new StringBuilder();
        keysets.forEach(key -> {
            Object value = jsonObject.get(key);
            if (value instanceof JSONObject) {
                if (value != null) {
                    appendJsonbjectParams(prefix, params, key, (JSONObject) value);
                } else {
                    logger.warn("参数【{}】为空", (builPrefix(prefix) + key));
                }
            } else if (value instanceof JSONArray) {
                if (value != null) {
                    JSONArray jsonArray = (JSONArray) value;
                    for (int index = 0, size = jsonArray.size(); index < size; ++index) {
                        Object jo = jsonArray.get(index);
                        if (jo instanceof JSONObject) {
                            appendJsonbjectParams(prefix, params, key + encoderIndex("[" + index + "]"), (JSONObject) jo);
                        } else {
                            appendNotJsonObjectParams(prefix, params, key, jo);
                        }
                    }
                } else {
                    logger.warn("参数【{}】为空", (builPrefix(prefix) + key));
                }
            } else {
                if (value != null) {
                    appendNotJsonObjectParams(prefix, params, key, value);
                } else {
                    logger.warn("参数【{}】为空", (builPrefix(prefix) + key));
                }
            }
        });
        return params;
    }

    /**
     * 追加jsonobject对象参数值
     *
     * @Author : 萧玄
     * @Date: 11:43 2019/4/1
     */
    private void appendJsonbjectParams(String prefix, StringBuilder params, String key, JSONObject value) {
        params.append(transeJson2KeyValuePairs(value, builPrefix(prefix) + key));
    }

    /**
     * 追加非jsonobject对象参数值
     *
     * @Author : 萧玄
     * @Date: 11:41 2019/4/1
     */
    private void appendNotJsonObjectParams(String prefix, StringBuilder params, String key, Object value) {
        params.append(builPrefix(prefix) + key).append("=").append(buildParamValue(value)).append("&");
    }

    /**
     * json对象列表的数据：加下标标记到元素上[i]
     * 例如：
     * [
     * dto:
     * {
     * id:1,
     * name:faker
     * },
     * {
     * id:2,
     * name:saber
     * }
     * ]
     * <p>
     * 转为：
     * dto[0].id=1 & dto[0].name=faker & dto[1].id=2 & dto[1].name=saber
     *
     * @Author : 萧玄
     * @Date: 11:34 2019/4/1
     */
    private String encoderIndex(String value) {
        try {
            return URLEncoder.encode(value.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.warn("解析对象列表下标异常{}：", value);
        }
        return "";
    }

    /**
     * 解析并使用UTF-8 编码参数值
     *
     * @Author : 萧玄
     * @Date: 19:34 2019/3/29
     */
    private Object buildParamValue(Object value) {
        try {
            if (value instanceof Collection) {
                Collection collection = (Collection) value;
                StringBuilder sb = new StringBuilder();
                collection.forEach(item -> sb.append(item).append(","));
                return URLEncoder.encode(sb.deleteCharAt(sb.length() - 1).toString(), "UTF-8");
            }
            return URLEncoder.encode(value.toString(), "UTF-8");
        } catch (UnsupportedEncodingException exception) {
            logger.warn("解析参数异常：{}", value);
        }
        return "";
    }

    /**
     * 增加前缀，格式：【父属性的名称.】，其中“.”是必须
     *
     * @Author : 萧玄
     * @Date: 11:38 2019/4/1
     */
    private String builPrefix(String prefix) {
        return StringUtils.isEmpty(prefix) ? "" : prefix + ".";
    }


}
