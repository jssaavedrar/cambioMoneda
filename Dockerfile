FROM openjdk:8
ADD target/CambioMontoAPI.jar CambioMontoAPI.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","/CambioMontoAPI.jar"]