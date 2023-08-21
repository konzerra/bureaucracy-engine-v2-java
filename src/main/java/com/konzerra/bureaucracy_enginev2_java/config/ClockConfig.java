package com.konzerra.bureaucracy_enginev2_java.config;

import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ClockConfig {

    public Clock clock(){
        return Clock.systemUTC();
    }
}
