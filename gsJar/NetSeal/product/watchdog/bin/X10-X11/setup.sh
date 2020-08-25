#!/bin/bash

BASE_DIR=`cd $(dirname $0); pwd -P`

watchdoghome=/opt/infosec/NetSeal/watchdog
watchdogbin=${watchdoghome}/bin
watchdogconfig=${watchdoghome}/conf
watchdoglib=${watchdoghome}/lib

dmi=`/usr/sbin/dmidecode | grep "Product Name" | grep " X" | awk -F: '{ print $2 }' | sed 's/ //g' | head -1`
#echo "配置Watchdog..."
#echo "主板型号:"$dmi

gs=${BASE_DIR}/setupfiles/getsensors-$dmi

if [ ! -e $gs ];then
	echo "not support mainboard：$dmi"
	exit -1;
fi

tar -xzf ${BASE_DIR}/ipmi.tgz && cd ${BASE_DIR}/ipmi && ./setup.sh && cd .. && rm -rf ipmi

mkdir -p /opt/infosec

if [ -d $watchdoghome ];then
	rm -rf $watchdoghome
	echo "cover watchdog home..."
else
	echo "create watchdog home..."
fi

tar -xzf ${BASE_DIR}/watchdog.tar.gz -C /opt/infosec/NetSeal

cp ${BASE_DIR}/setupfiles/Swatchdog.sh /etc/rc3.d
cp $gs ${watchdogbin}/getsensors
cp ${BASE_DIR}/setupfiles/watchdog.xml.$dmi ${watchdogconfig}/watchdog.xml

echo "******************************************"
echo "  install success, please reboot OS!"
echo "******************************************"
