package com.backend.springcloud.msvc.products;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.backend.lib.mcsv.commons.entity",
        "com.backend.springcloud.microservicios.entity"})
public class ProductsMicroserviceApplication {

    private static final Logger logger = LoggerFactory.getLogger(ProductsMicroserviceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProductsMicroserviceApplication.class, args);
        logger.info("PRODUCT MICROSERVICE successfully INIT...port 8001...!!!");
    }

}
