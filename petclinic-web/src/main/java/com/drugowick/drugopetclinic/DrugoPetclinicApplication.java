package com.drugowick.drugopetclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DrugoPetclinicApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrugoPetclinicApplication.class, args);
    }

}
