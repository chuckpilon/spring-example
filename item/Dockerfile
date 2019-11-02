FROM openjdk:8

ADD ${project.build.directory}/${project.artifactId}-${project.version}.jar /app.jar

ENTRYPOINT java -jar ${JAVAOPTS} app.jar ${OPTS}
