package io.fortumo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Dinesh Thangaraj
 *
 * Created on 06-Jan-2018
 */
@Configuration
@EnableJpaRepositories(basePackages = "io.fortumo.repositories")
public class JpaConfiguration {
}
