FROM tomcat:8.5-jdk8
COPY server-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps

RUN ln -s /usr/local/tomcat/webapps.dist/manager webapps/manager
RUN ln -s /usr/local/tomcat/webapps.dist/host-manager webapps/host-manager
RUN ln -s /usr/local/tomcat/webapps.dist/ROOT webapps/ROOT