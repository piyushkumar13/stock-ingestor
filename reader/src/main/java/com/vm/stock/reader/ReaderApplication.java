package com.vm.stock.reader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ReaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReaderApplication.class, args);
    }

}