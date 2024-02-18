FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY . .

RUN mvn clean package

#
# Package stage
#
FROM openjdk:17-alpine
COPY --from=build /app/target/albums-photos-API*.jar albums-photos-API.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","albums-photos-API.jar"]
