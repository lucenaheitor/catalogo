package com.example.demo.Config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public LivroMapper livroMapper() {
        return Mappers.getMapper(LivroMapper.class);
    }
}
