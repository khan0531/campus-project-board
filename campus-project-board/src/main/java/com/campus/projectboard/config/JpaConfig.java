package com.campus.projectboard.config;

import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@Configuration
public class JpaConfig {
  @Bean
  public AuditorAware<String> auditorAware() {
    return () -> Optional.ofNullable("admin"); // TODO: Spring Security Context 에서 현재 사용자를 받아와야 함
  }
}
