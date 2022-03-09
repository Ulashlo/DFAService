FROM openjdk
COPY build/libs/DFABackend.jar .
WORKDIR .
ENTRYPOINT ["java", "-jar", "DFABackend.jar"]
EXPOSE 8080
