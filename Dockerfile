FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} fashion-blog-api-v1.jar
ENTRYPOINT ["java", "-jar", "/fashion-blog-api-v1"]

#FROM openjdk:17
#
#EXPOSE 8080
#
#ADD target/fashion-blog-api-v1.jar fashion-blog-api-v1.jar
#
#ENTRYPOINT ["java", "-jar", "/fashion-blog-api-v1.jar"]