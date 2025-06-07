package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private Connection conn;

    public DatabaseManager() throws SQLException {
        conn = DriverManager.getConnection("jdbc:h2:mem:test", "sa","");

        String createApplicants = "CREATE TABLE IF NOT EXISTS Applicants (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), email VARCHAR(255))";
        String createEducation = "CREATE TABLE IF NOT EXISTS Education (id INT PRIMARY KEY AUTO_INCREMENT, applicant_id INT, degree VARCHAR(255))";
        String createExperience = "CREATE TABLE IF NOT EXISTS Experience (id INT PRIMARY KEY AUTO_INCREMENT, applicant_id INT, company VARCHAR(255), position VARCHAR(255))";
        String createCompanies = "CREATE TABLE IF NOT EXISTS Companies (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255))";
        String createResults = "CREATE TABLE IF NOT EXISTS Results (id INT PRIMARY KEY AUTO_INCREMENT, applicant_id INT, result VARCHAR(255))";

        Statement stmt = conn.createStatement();
        stmt.execute(createApplicants);
        stmt.execute(createEducation);
        stmt.execute(createExperience);
        stmt.execute(createCompanies);
        stmt.execute(createResults);
    }

    public Connection getConnection() {
        return conn;
    }
}

