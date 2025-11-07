FROM maven:3-openjdk-17 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -DskipTests

FROM maven:3-openjdk-17 as runner

WORKDIR /app

COPY --from=builder ./app/target/billing-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 4001
EXPOSE 9001

<<<<<<< HEAD
ENTRYPOINT ["java","-jar","app.jar"]
=======
ENTRYPOINT ["java","-jar","app.jar"]
>>>>>>> e036e29 (initial commit for patient-service)
