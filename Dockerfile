#FROM openjdk:17
#VOLUME /tmp
#EXPOSE 8888
#ARG JAR_FILE=target/task-test-0.0.1.jar
#
#ADD ${JAR_FILE} app.jar
#
#ENTRYPOINT ["java","-jar","/app.jar"]

ARG REDHAT_REGISTRY="registry.access.redhat.com"
FROM $REDHAT_REGISTRY/ubi8/openjdk-17:1.15 as builder
WORKDIR /opt/app-root/src
COPY . .
ARG MAVEN_REPOSITORY="https://repo.maven.apache.org/maven2"
RUN mvn clean package -Dmaven.repo.remote=$MAVEN_REPOSITORY

# Run
ARG REDHAT_REGISTRY="registry.access.redhat.com"
FROM $REDHAT_REGISTRY/ubi8/openjdk-17:1.15
USER root
RUN mkdir -p /opt/app-root/app && \
    chown -R 1001:1001 /opt/app-root/app
WORKDIR /opt/app-root/app
COPY --from=builder --chown=1001:0 /opt/app-root/src/target/task-test-*.jar ./task.jar
USER 1001
ENTRYPOINT ["java", "-jar", "task.jar"]