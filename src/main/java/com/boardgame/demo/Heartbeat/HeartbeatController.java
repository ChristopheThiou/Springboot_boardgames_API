package com.boardgame.demo.Heartbeat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartbeatController {

    @Autowired
    private HeartbeatSensor sensor;

    @GetMapping("/heartbeat")
    public int getHeartbeat() {
        return sensor.get();

    }
}
