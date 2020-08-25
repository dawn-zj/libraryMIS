#! /bin/bash
source ./verinfo.sh
netsealhome=/opt/infosec/NetSeal
currentpath=`pwd`

cd /opt/infosec
if [ ! -d "backupfiles/" ];then
mkdir backupfiles
fi


echo "Backup files..."
cp -r ${netsealhome} backupfiles/
dateinfo=`date -d today +"%Y-%m-%d %T"`
echo "Backup finished."
echo "[backup] [${version}] [${dateinfo}]">>updatelog.txt

echo "setup copy files..."

echo "cp files..."
rm -rf ${netsealhome}/appserver/lib
cp -r ${currentpath}/lib ${netsealhome}/appserver
rm -rf ${netsealhome}/tomcat/webapps/webserver
cp -r ${currentpath}/webserver ${netsealhome}/tomcat/webapps

cp -r ${netsealhome}/watchdog/conf/config.txt ${netsealhome}/conf/
rm -rf ${netsealhome}/watchdog

sh ${currentpath}/watchdog/setup.sh

mv ${netsealhome}/conf/config.txt ${netsealhome}/watchdog/conf/

echo "setup finished."
dateinfo=`date -d today +"%Y-%m-%d %T"`
echo "[setup] [${version}] [${dateinfo}] [${info}]">>updatelog.txt
