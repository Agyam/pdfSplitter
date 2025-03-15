FROM openjdk:17
COPY target/pdfSplitter-0.0.1-SNAPSHOT.jar pdfSplitter.jar
ENTRYPOINT ["java", "-jar", "pdfSplitter.jar"]