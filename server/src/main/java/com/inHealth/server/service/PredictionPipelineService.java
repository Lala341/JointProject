package com.inHealth.server.service;

import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Service
public class PredictionPipelineService {
    public static void main(String[] args) {
        // To train model
        executeQueries("queries/query2.cypher");
        executeQueries("queries/query1.cypher");
        // To drop everything
        getPrediciton("'1e9c1862-ec25-4cde-a689-38ab696ccbc0'");
        //  executeQueries("queries/query2.cypher");
    }

    public List getPredicitonEnd(String id) {
        // To train model
        executeQueries("queries/query2.cypher");
        executeQueries("queries/query1.cypher");
        // To drop everything
        return getPrediciton("'"+id+"'");
    }

    public static List getPrediciton(String id) {
        // Set up the Neo4j Java Driver
        String uri = "bolt://localhost:7687";
        String username = "neo4j";
        String password = "12345678";

        Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));

        // Read the query from the file
        String statement = readQueryFromFile("queries/query3.cypher");
        statement=statement.replace("ID", id);
        System.out.println(statement);
        // Split the queries into separate statements

        List results=new ArrayList<>();
        // Execute each statement
        try (Session session = driver.session()) {
               if (!statement.trim().isEmpty()) {
                    Result result = session.run(statement);


                    // Process and print the query results
                    while (result.hasNext()) {
                        Record record = result.next();
                        String cond= String.valueOf(record.get("condition"));
                        Double prob= Double.valueOf(record.get("probability").asDouble());
                        List temp=new ArrayList<>();
                        temp.add(cond);
                        temp.add(prob);
                        results.add(temp);

                        System.out.println(record.asMap());
                    }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the driver when done
            driver.close();
        }
        return results;
    }

    public static void executeQueries(String filePath) {
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

    public static String readQueryFromFile(String filePath) {
        try (InputStream inputStream = PredictionPipelineService.class.getClassLoader().getResourceAsStream(filePath);
             Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            return scanner.useDelimiter("\\A").next();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
