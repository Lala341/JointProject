package com.inHealth.server.service;

import org.neo4j.driver.*;
import org.neo4j.driver.Record;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class PredictionPipelineService {
    public static void main(String[] args) {
        // To train model
        executeQueries("queries/query1.cypher");
        // To drop everything
        executeQueries("queries/query2.cypher");
    }

    private static void executeQueries(String filePath) {
        // Set up the Neo4j Java Driver
        String uri = "bolt://localhost:7687";
        String username = "neo4j";
        String password = "12345678";

        Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));

        // Read the query from the file
        String queries = readQueryFromFile(filePath);

        // Split the queries into separate statements
        String[] statements = queries.split(";");

        // Execute each statement
        try (Session session = driver.session()) {
            for (String statement : statements) {
                if (!statement.trim().isEmpty()) {
                    Result result = session.run(statement);

                    // Process and print the query results
                    while (result.hasNext()) {
                        Record record = result.next();
                        System.out.println(record.asMap());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the driver when done
            driver.close();
        }
    }

    private static String readQueryFromFile(String filePath) {
        try (InputStream inputStream = PredictionPipelineService.class.getClassLoader().getResourceAsStream(filePath);
             Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            return scanner.useDelimiter("\\A").next();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
