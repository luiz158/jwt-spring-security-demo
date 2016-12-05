package org.techytax;

import lombok.extern.java.Log;
import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import java.sql.SQLException;

@SpringBootApplication
@Log
public class JwtDemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) throws SQLException {
//        startH2Server();
        SpringApplication.run(JwtDemoApplication.class, args);
}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        startH2Server();
        return application.sources(JwtDemoApplication.class);
    }

    private static void startH2Server() {
        try {
            Server h2Server = Server.createTcpServer("-tcpAllowOthers").start();
//            Class.forName("org.h2.Driver");
//            DriverManager.getConnection("jdbc:h2:tcp://localhost/~/tt_db;MODE=H2;AUTO_SERVER=TRUE", "sa", "");

            Server.createWebServer().start();
            if (h2Server.isRunning(true)) {
                log.info("H2 server was started and is running.");
            } else {
                throw new RuntimeException("Could not start H2 server.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to start H2 server: ", e);
        }
    }
}
