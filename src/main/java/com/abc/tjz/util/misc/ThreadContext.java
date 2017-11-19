package com.abc.tjz.util.misc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.SneakyThrows;

/**
 * 线程上下文服务接口，其中的数据只在本线程中可见
 */
public abstract class ThreadContext {

    private static final ThreadLocal<Map<String, Object>> mapThreadLocal = new InheritableThreadLocal<Map<String, Object>>();
    
    private static final ThreadLocal<HttpServletRequest> requestThreadLocal = new InheritableThreadLocal<HttpServletRequest>();
    
    private static final ThreadLocal<HttpServletResponse> responseThreadLocal = new InheritableThreadLocal<HttpServletResponse>();

    private static Map<String, Object> getContextMap() {
        if (mapThreadLocal.get() == null) {
            mapThreadLocal.set(new HashMap<String, Object>());
        }
        return mapThreadLocal.get();
    }

    public static void put(String key, Object value) {
        getContextMap().put(key, value);
    }

    public static Object get(String key) {
        return getContextMap().get(key);
    }

    public static HttpServletRequest getRequest() {
        return requestThreadLocal.get();
    }

    public static void setRequest(HttpServletRequest httpServletRequest) {
        requestThreadLocal.set(httpServletRequest);
    }

    public static HttpServletResponse getResponse() {
        return responseThreadLocal.get();
    }

    public static void setResponse(HttpServletResponse response) {
        responseThreadLocal.set(response);
    }
    
    public static void clear() {
    	mapThreadLocal.set(null);
    	requestThreadLocal.set(null);
        responseThreadLocal.set(null);
    }
}
