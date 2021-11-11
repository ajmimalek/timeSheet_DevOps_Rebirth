FROM java:8
EXPOSE 8081
ADD /target/Timesheet-1.2-SNAPSHOT.war timesheet.war
ENTRYPOINT [ "java", "-jar", "timesheet.war" ]