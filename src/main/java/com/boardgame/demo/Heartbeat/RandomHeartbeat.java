package com.boardgame.demo.heartbeat;

import org.springframework.stereotype.Service;

@Service
class RandomHeartbeat implements HeartbeatSensor {

    @Override
    public int get() {
        return 40 + (int) (Math.random() * (230 - 40 + 1));
    }
}
