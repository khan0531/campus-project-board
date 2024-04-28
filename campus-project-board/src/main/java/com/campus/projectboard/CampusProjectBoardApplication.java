package com.campus.projectboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class CampusProjectBoardApplication {

  public static void main(String[] args) {
    SpringApplication.run(CampusProjectBoardApplication.class, args);
  }

}
