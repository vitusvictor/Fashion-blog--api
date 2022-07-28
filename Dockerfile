FROM adoptopenjdk:17-jre-hotspot
ADD target/fashion-blog-api-v1.jar fashion-blog-api-v1.jar
ENTRYPOINT ["java", "-jar", "/fashion-blog-api-v1"]

#ARG JAR_FILE=*.jar
#COPY ${JAR_FILE} fashion-blog-api-v1.jar

#FROM openjdk:17
#
#EXPOSE 8080
#
#
#ENTRYPOINT ["java", "-jar", "/fashion-blog-api-v1.jar"]