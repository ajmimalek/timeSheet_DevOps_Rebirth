FROM java:8
EXPOSE 8090
ADD /target/timesheet_devops.war timesheet_devops.war
ENTRYPOINT ["java", "-jar", "timesheet_devops.war"]