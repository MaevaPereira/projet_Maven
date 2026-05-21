package com.spring_boot.project_maven.sensor;

import org.springframework.stereotype.Service;

import java.util.Random;


@Service
    public class RandomHeartbeat implements HeartbeatSensor {
       public int get(){
           int i = new Random().nextInt(40, 231);
            return i;
        }
    }

