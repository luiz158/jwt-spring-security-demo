package org.techytax;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class TechyTaxApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechyTaxApplication.class, args);
    }

}
