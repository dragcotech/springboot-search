package com.mpfleet;

import com.mpfleet.config.SqlFileRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class MpfleetApplication {

    public static void main(String[] args) throws SQLException, IOException {
        SpringApplication.run(MpfleetApplication.class, args);
        SqlFileRunner sqlFileRunner = new SqlFileRunner();
        sqlFileRunner.executeSqlFile("src/main/resources/seed.sql");
    }

}
