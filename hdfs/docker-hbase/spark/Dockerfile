FROM bde2020/spark-base:2.4.4-hadoop2.7

# Install required dependencies
RUN apk update && \
    apk add --no-cache wget bash && \
    apk add --no-cache --virtual .build-deps curl && \
    rm -rf /var/cache/apk/*

# Install Hadoop
ENV HADOOP_VERSION 2.7.7
RUN wget https://archive.apache.org/dist/hadoop/common/hadoop-$HADOOP_VERSION/hadoop-$HADOOP_VERSION.tar.gz && \
    tar -xzf hadoop-$HADOOP_VERSION.tar.gz && \
    mv hadoop-$HADOOP_VERSION /usr/local/hadoop && \
    rm hadoop-$HADOOP_VERSION.tar.gz

# Install HBase
ENV HBASE_VERSION 2.4.7
RUN wget https://archive.apache.org/dist/hbase/$HBASE_VERSION/hbase-$HBASE_VERSION-bin.tar.gz && \
    tar -xzf hbase-$HBASE_VERSION-bin.tar.gz && \
    mv hbase-$HBASE_VERSION /usr/local/hbase && \
    rm hbase-$HBASE_VERSION-bin.tar.gz

# Add HDFS and HBase configuration files
ADD core-site.xml /spark/conf/core-site.xml
ADD hdfs-site.xml /spark/conf/hdfs-site.xml
ADD hbase-site.xml /usr/local/hbase/conf/hbase-site.xml

# Set environment variables for Hadoop and HBase
ENV HADOOP_HOME /usr/local/hadoop
ENV HBASE_HOME /usr/local/hbase
ENV PATH $PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin:$HBASE_HOME/bin

# Start Spark Shell by default
CMD ["/spark/bin/spark-shell"]
