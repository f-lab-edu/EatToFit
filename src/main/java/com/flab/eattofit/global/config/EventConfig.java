package com.flab.eattofit.global.config;

import com.flab.eattofit.global.config.event.Events;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class EventConfig {

    private final ApplicationContext applicationContext;

    @Bean
    public InitializingBean eventInitializer() {
        return () -> Events.setPublisher(applicationContext);
    }
}
