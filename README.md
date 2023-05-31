

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


# SDM

https://www.ncbi.nlm.nih.gov/pmc/articles/PMC1656964/
https://apps.who.int/iris/bitstream/handle/10665/42792/9241580348_eng_Volume1.pdf
https://www.sciencedirect.com/science/article/abs/pii/S1532046423000898
https://ieeexplore.ieee.org/abstract/document/9565727
https://www.sciencedirect.com/science/article/abs/pii/S1386505622001484
file:///Users/lauraforerocamacho/Downloads/healthcare-11-00710-v2.pdf
https://paperswithcode.com/paper/unsupervised-pre-training-on-patient
https://arxiv.org/pdf/2207.10603.pdf


Important

https://www.medrxiv.org/content/10.1101/2021.11.25.21266465v1.full.pdf
https://sci-hub.se/10.1016/j.eswa.2019.05.048


Data
https://www.dhsprogram.com/publications/publication-DHSQ8-DHS-Questionnaires-and-Manuals.cfm
https://ec.europa.eu/eurostat/web/microdata/european-health-interview-survey


https://www.ohdsi.org/data-standardization/



