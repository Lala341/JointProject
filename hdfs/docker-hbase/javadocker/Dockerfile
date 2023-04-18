FROM tomcat:9-jdk11
RUN adduser -D tomcat; chown -R tomcat:tomcat /usr/local/tomcat
USER tomcat
COPY server-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/
