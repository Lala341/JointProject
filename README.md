

# InHealth

InHealth is a health and wellness app that utilizes activity tracking and sensor data from users' phones to predict diseases and identify health risks. This approach provides personalized insights into users' health without relying on costly wearable devices. The app offers tailored recommendations to enhance overall well-being, addressing lifestyle-related diseases like obesity, diabetes, and sleep disorders. InHealth empowers users to make informed lifestyle choices and prioritize their health and wellness.

## Instructions of Execution

### Code Repository

Code Repository: [https://github.com/Lala341/JointProject](https://github.com/Lala341/JointProject)

### Folder Structure

```
app/                  # Contains the code of the application in react native
dashboards/           # Contains the tableau project for the deployed dashboards
hdfs/docker-hbase/    # Contains the docker compose for the execution of the architecture
server/               # Contains the server in Java, in charge of all processes such as real-time analysis, prediction of diseases in graphs, among others, it also contains the producers and consumers of kafka, as well as the connection to mongodb and neo4j
testing/              # Folder that contains some collections to test on the server endpoints
```

### Execution of Architecture (Services)

1. Install Docker Compose according to your operating system. Refer to [Docker documentation](https://docs.docker.com/get-docker/) for installation instructions.
2. Open the console and navigate to the `hdfs/docker-hbase` folder.
3. Run the following command:
   ```
   docker-compose -f docker-compose-standalone.yml up -d
   ```
4. Verify that all Docker Compose services are active.

### Execution of Main Server (Java)

To execute and test the Java server:

1. Make sure you have Java JDK 11 installed. If you are using IntelliJ IDEA, you can download it directly into the application.
2. Open the project located in the `server` folder.
3. Install the dependencies specified in the `pom.xml` file.
4. Compile and run the server normally. You can follow the configuration in the `ServerApplication` class, which is a Java project that uses Spring Boot.
5. Test the service. (In the next module, although optional, there is a Postman collection for some methods and a notebook that generates files to simulate the load in real time)

### Execution of Tests (Optional)

The tests are located in the `testing` folder. There is a Postman collection that can be used to test some methods, or you can also use the `generateDataDB` notebook, which is used to create new users, polls, and upload files to the server.

### Execution of Dashboards (Optional)

To open the dashboards, follow these steps:

1. Ensure you have Tableau Desktop installed on your computer.
2. Install the driver for MongoDB Atlas. Refer to the [MongoDB documentation](https://www.mongodb.com/docs/atlas/data-federation/query/sql/tableau/connect/) for instructions. (You only need to follow the steps in the "Download the JDBC Driver and Tableau Connector component" section)
3. Import the project from the Tableau file located in the `dashboards` folder.
4. Enter the password for the data source:
   Password: `pvZ6I6vLHORulZi5`


# SDM

https://www.ncbi.nlm.nih.gov/pmc/articles/PMC1656964/
https://apps.who.int/iris/bitstream/handle/10665/42792/9241580348_eng_Volume1.pdf
https://www.sciencedirect.com/science/article/abs/pii/S1532046423000898
https://ieeexplore.ieee.org/abstract/document/9565727
https://www.sciencedirect.com/science/article/abs/pii/S1386505622001484
file:///Users/lauraforerocamacho/Downloads/healthcare-11-00710-v2.pdf
https://paperswithcode.com/paper/unsupervised-pre-training-on-patient
https://arxiv.org/pdf/2207.10603.pdf



