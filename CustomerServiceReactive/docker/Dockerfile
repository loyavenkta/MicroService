FROM java:8
VOLUME /tmp
ADD CustomerService.jar apps.jar
EXPOSE 6081
ENTRYPOINT ["java","-jar","./apps.jar"]

