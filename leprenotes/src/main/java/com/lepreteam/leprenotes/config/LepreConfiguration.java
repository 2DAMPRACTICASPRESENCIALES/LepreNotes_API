package com.lepreteam.leprenotes.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LepreConfiguration {
    @Bean
    public ModelMapper modelMapper() { return new ModelMapper(); }
}
