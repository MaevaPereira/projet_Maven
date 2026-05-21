package com.spring_boot.project_maven.controller;

import com.spring_boot.project_maven.sensor.HeartbeatSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartbeatController {
    @Autowired
    private HeartbeatSensor heartbeatSensor;
    @GetMapping("/heartbeat")
    int  heartbeat (){
        return heartbeatSensor.get();
    }
}


