FROM openjdk:8
EXPOSE 8088
ADD /target/achat.jar  achat.jar
ENTRYPOINT ["java", "-jar", "achat.jar"]
