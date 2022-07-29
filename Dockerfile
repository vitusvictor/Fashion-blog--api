FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/fashion-blog-api-v1.jar fashion-blog-api-v1.jar
ENTRYPOINT ["java", "-jar", "/fashion-blog-api-v1.jar"]

#ARG JAR_FILE=*.jar
#COPY ${JAR_FILE} fashion-blog-api-v1.jar

#FROM openjdk:17
# adoptopenjdk/openjdk17:alpine-jre
#EXPOSE 8080
#
#
#ENTRYPOINT ["java", "-jar", "/fashion-blog-api-v1.jar"]