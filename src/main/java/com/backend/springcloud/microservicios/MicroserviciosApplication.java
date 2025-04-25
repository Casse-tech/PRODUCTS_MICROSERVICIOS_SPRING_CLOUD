package com.backend.springcloud.microservicios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.backend.lib.mcsv.commons.entity",
        "com.backend.springcloud.microservicios.entity"})
public class MicroserviciosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviciosApplication.class, args);
    }

}
