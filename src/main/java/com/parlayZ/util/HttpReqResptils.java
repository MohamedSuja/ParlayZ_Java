package com.parlayZ.util;

import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

public class HttpReqResptils {

    public static Map<String, String> getSearchParameters(WebRequest webRequest) {
        Map<String, String[]> params = webRequest.getParameterMap();
        Map<String, String> searchParams = new HashMap<>();
        StringBuilder stringBuilder;
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            stringBuilder = new StringBuilder();
            String key = entry.getKey();
            for (String val : entry.getValue()) {
                stringBuilder.append(val);
            }
            searchParams.put(key, stringBuilder.toString());
        }
        return searchParams;
    }
}
