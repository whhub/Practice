package com.example.SensorDataGenerator;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MessageBuilder {
    public String build() {

        State state = new State();
        state.setStartup(false);
        state.setPlanTotalTime(480);
        state.setPlanProductRate(0.6);
        state.setRealGoodNum(430);
        state.setRealTotalNum(450);

        Process process = new Process();
        process.setWaterPressure(100);
        process.setFlow(50);

        Data data = new Data();
        data.setState(state);
        data.setProcess(process);

        Message message = new Message();
        message.setData(data);
        message.setDate(new Date());
        message.setDeviceNum("2019030100672660877D");

        return JSON.toJSONString(message);
    }

}
