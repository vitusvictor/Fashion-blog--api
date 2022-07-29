FROM openjdk:17
ADD target/fashion-blog-api-v1.jar fashion-blog-api-v1.jar
ENTRYPOINT ["java", "-jar", "/fashion-blog-api-v1.jar"]

#EXPOSE 8080
