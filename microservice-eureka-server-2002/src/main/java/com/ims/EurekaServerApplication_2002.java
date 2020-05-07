package com.ims;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
 
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication_2002 {
 
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication_2002.class, args);
    }
}