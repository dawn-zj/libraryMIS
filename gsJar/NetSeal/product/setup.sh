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
# 删除appserver原来的jar，放入新的jar
rm -rf ${netsealhome}/appserver/lib
cp -r ${currentpath}/lib ${netsealhome}/appserver
# 删除Tomcat下部署的webserver，放入新的webserver
rm -rf ${netsealhome}/tomcat/webapps/webserver
cp -r ${currentpath}/webserver ${netsealhome}/tomcat/webapps

# 备份watchdog的配置到conf下，删除watchdog
cp -r ${netsealhome}/watchdog/conf/config.txt ${netsealhome}/conf/
rm -rf ${netsealhome}/watchdog

# 安装新的watchdog，并使用原来的watchdog配置
sh ${currentpath}/watchdog/setup.sh

mv ${netsealhome}/conf/config.txt ${netsealhome}/watchdog/conf/

echo "setup finished."
dateinfo=`date -d today +"%Y-%m-%d %T"`
echo "[setup] [${version}] [${dateinfo}] [${info}]">>updatelog.txt
