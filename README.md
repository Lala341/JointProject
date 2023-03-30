

# InHealth

## Architecture

### Data Lake

- Use App (React Native) to recolect information of sensors, it create a file and send to the server.
- Server API. This server receive the information of the users and sent to Apache Nifi.
- Apache Nifi. Allow interconnect different sources of data, also have some features necessary for security in case of sensitive information. (Batch loading).
- HDFS- Hadoop. Allow to storage the information.
