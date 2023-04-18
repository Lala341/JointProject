FROM tomcat:9.0-jdk13-openjdk-oracle
COPY server-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps
RUN cp -r -avT $CATALINA_HOME/webapps.dist/* $CATALINA_HOME/webapps/*