package com.lixin.xinaicodemother;

import dev.langchain4j.community.store.embedding.redis.spring.RedisEmbeddingStoreAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {RedisEmbeddingStoreAutoConfiguration.class})
@MapperScan("com.lixin.xinaicodemother.mapper")
public class XinAiCodeMotherApplication {

    public static void main(String[] args) {
        SpringApplication.run(XinAiCodeMotherApplication.class, args);
    }

}
