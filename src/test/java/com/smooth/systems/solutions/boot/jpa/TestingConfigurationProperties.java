package com.smooth.systems.solutions.boot.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EntityScan
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
@ComponentScan("com.smooth.systems.solutions.boot.jpa")
public class TestingConfigurationProperties {
}
