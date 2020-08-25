#! /bin/bash
source ./verinfo.sh
netsealhome=/opt/infosec/NetSeal

cd /opt/infosec
if [ -d "backupfiles/NetSeal/" ];then
echo "Rollback files..."
cp -r backupfiles/NetSeal ./
echo "Rollback finished."
dateinfo=`date -d today +"%Y-%m-%d %T"`
echo "[rollback] [${version}] [${dateinfo}]">>updatelog.txt
else
echo "no dir backupfiles/NetSeal/"
fi
