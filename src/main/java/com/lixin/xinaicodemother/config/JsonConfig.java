package com.lixin.xinaicodemother.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;
import tools.jackson.databind.ser.std.ToStringSerializer;

@Configuration
public class JsonConfig {

    @Bean
    @Primary
    public JsonMapper jsonMapper() {
        SimpleModule longModule = new SimpleModule();
        longModule.addSerializer(Long.class, ToStringSerializer.instance);
        longModule.addSerializer(Long.TYPE, ToStringSerializer.instance);

        return JsonMapper.builder()
                .addModule(longModule)
                .build();
    }
}