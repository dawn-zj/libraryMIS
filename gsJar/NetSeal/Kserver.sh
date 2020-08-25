#!/bin/bash

defpath="/opt/infosec/NetSeal"

PATH="$PATH:${defpath}/tomcat/bin/:${defpath}/jdk1.8.0_121/bin:${defpath}/jdk1.8.0_121/jre/bin"
JAVA_HOME=${defpath}/jdk1.8.0_121/
export JAVA_HOME
JRE_HOME=${defpath}/jdk1.8.0_121/jre/
export JRE_HOME
TOMCAT_HOME=${defpath}/tomcat/
export TOMCAT_HOME
export CATALINA_HOME=${defpath}/tomcat
CLASSPATH=${defpath}/tomcat/lib:${defpath}/jdk1.8.0_121/jre/lib:.
export CLASSPATH

cd ${defpath}/tomcat/bin
./shutdown.sh

cd ${defpath}/appserver
./AppServer2.sh -stop

#${defpath}/ns/bisafe.sh stop
