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
#EXPOSE 8080 5005
#
#CMD ["sh", "-c", "./mvnw spring-boot:run -Dspring-boot.run.fork=false -Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005'"]


# dockerfile

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY msvc-products/mvnw ./
COPY msvc-products/mvnw ./
COPY msvc-products/.mvn .mvn
COPY core ./core
COPY msvc-products ./msvc-products
RUN chmod +x mvnw
RUN ./mvnw -f core clean install -DskipTests
WORKDIR /app/msvc-products
RUN ./mvnw clean package -DskipTests

EXPOSE 8080 5005
CMD ["sh", "-c", "./mvnw spring-boot:run -Dspring-boot.run.fork=false"]