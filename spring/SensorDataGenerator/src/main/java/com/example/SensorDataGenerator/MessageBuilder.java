package com.example.SensorDataGenerator;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class MessageBuilder {
    public String build() {
        JSONObject jsonObject = buildJSONObject();
        return jsonObject.toJSONString();
    }

    private JSONObject buildJSONObject() {
        //Map
        return null;
    }
}
