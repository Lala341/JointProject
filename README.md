

# InHealth

## Architecture

### Data Lake

- Use App (React Native) to recolect information of sensors, it create a file and send to the server.
- Server API. This server receive the information of the users and sent to Apache Hadoop.
- HDFS- Hadoop. Allow to storage the information.


## Repository Structure

app - Code of the app in React Native to recolect information of sensors, it create a file and send to the server.

hdfs - Files of docker about the configuration and deploy of the architecture.

server - Code of the JAVA Server, have the data collectors of the external data sources and the 


## Use Instructions

To run the application code, you can follow these steps: https://reactnative.dev/docs/environment-setup
( npx install, npx start expo)

For the deployment and execution of the architecture, a version of docker compose was used for each container, it starts from the version of this repository https://github.com/big-data-europe/docker-hbase and the docker compose is edited for specific uses in the InHealth infrastructure. For its execution it is necessary to have docker installed, execute the following commands:

cd hdfs/docker-hbase
docker-compose -f docker-compose-standalone.yml up -d



