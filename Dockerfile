#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
RUN apk add --no-cache curl tar bash && \
    curl -fsSL https://apache.osuosl.org/maven/maven-3/3.8.1/binaries/apache-maven-3.8.1-bin.tar.gz | tar -xzC /usr/share && \
    mv /usr/share/apache-maven-3.8.1 /usr/share/maven && \
    ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

# Set up the $PATH environment variable
ENV MAVEN_HOME /usr/share/maven
ENV PATH $MAVEN_HOME/bin:$PATH
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/springboot-kotlin-crud-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]