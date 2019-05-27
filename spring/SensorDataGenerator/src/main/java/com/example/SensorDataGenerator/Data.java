package com.example.SensorDataGenerator;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {
    @JSONField
    private Process process;

    @JSONField
    private State state;
}
