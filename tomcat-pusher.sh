#!/bin/bash
mvn clean install
cp /home/suppi147/Desktop/Filemei/target/filemei-1.0.war /var/lib/tomcat9/webapps/
service tomcat9 restart