FROM bde2020/spark-base:2.4.4-hadoop2.7

# Install Hadoop and HBase client libraries
RUN apk update && apk add hadoop-client hbase

# Add HDFS and HBase configuration files
ADD core-site.xml /spark/conf/core-site.xml
ADD hdfs-site.xml /spark/conf/hdfs-site.xml
ADD hbase-site.xml /etc/hbase/conf/hbase-site.xml

# Start Spark Shell by default
CMD ["/spark/bin/spark-shell"]
