FROM openjdk:17

EXPOSE 8080

ADD target/fashion-blog-api-v1.jar fashion-blog-api-v1.jar

ENTRYPOINT ["java", "-jar", "/fashion-blog-api-v1.jar"]