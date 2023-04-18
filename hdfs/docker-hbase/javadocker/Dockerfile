FROM tomcat:9.0-jdk11
COPY server-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps
RUN cp -r $CATALINA_HOME/webapps.dist/* $CATALINA_HOME/webapps/