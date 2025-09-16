#FROM openjdk:21-jdk-slim
#WORKDIR /app
#
#COPY pom.xml .
#COPY mvnw .
#COPY .mvn .mvn
#RUN chmod +x mvnw
#RUN ./mvnw dependency:go-offline -B
#
#COPY src ./src
#
#EXPOSE 8081 5005
#
#CMD ["sh", "-c", "./mvnw spring-boot:run -Dspring-boot.run.fork=false -Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005'"]


FROM openjdk:21-jdk-slim
WORKDIR /app

COPY msvc-email-notification/mvnw ./
COPY msvc-email-notification/.mvn .mvn
COPY core ./core
COPY msvc-email-notification ./msvc-email-notification

RUN chmod +x mvnw

RUN ./mvnw -f core clean install -DskipTests

WORKDIR /app/msvc-email-notification
RUN ./mvnw clean package -DskipTests

EXPOSE 8081 5005
CMD ["sh", "-c", "./mvnw spring-boot:run -Dspring-boot.run.fork=false"]