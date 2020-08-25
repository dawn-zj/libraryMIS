#!/bin/bash

# sleep 40
#cd /usr/local/swpkcs11/
#./load

defpath="/opt/infosec/NetSeal"
currentpath=`pwd`

cd /opt/infosec/NetSeal/fishman/install
./install.sh

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

TPORT_TOMCAT=`netstat -ntlp | awk '{print $4}' | grep -i '8443'`
TPORT_AUTH=`netstat -ntlp | awk '{print $4}' | grep -i '8451'`

if [ -z "$TPORT_TOMCAT" ] ; then

	cd ${defpath}/tomcat/bin
	./startup.sh
else
	echo "The port 8443 has been in use."
fi



if [ -z "$TPORT_AUTH" ] ; then

	cd ${defpath}/appserver
	nohup ./AppServer.sh -start &
else
	echo "The port 8451 has been in use."
fi&

#export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:${defpath}/ns/lib
#${defpath}/ns/bisafe.sh start

#not X10 X11 mainboard, not 2.6.32 os core, not install watchdog model
#cd $currentpath
#./Swatchdog.sh

