package com.mpfleet.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlFileRunner {
    public void executeSqlFile(String fileName) throws IOException, SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mpfleet", "root", "root12345");

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder sqlBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sqlBuilder.append(line);
            sqlBuilder.append("\n");
        }
        reader.close();

        try{
            String[] sqlStatements = sqlBuilder.toString().split(";");
            Statement statement = connection.createStatement();
            for (String sql : sqlStatements) {
                if (!sql.trim().isEmpty()) {
                    statement.executeUpdate(sql.replace("INSERT", "INSERT INTO ... ON DUPLICATE KEY UPDATE"));
                }
            }
            statement.close();
        }catch (Exception e){
            System.out.println("Database filled!");
        }

        connection.close();
    }
}
