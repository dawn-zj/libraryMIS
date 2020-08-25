#!/bin/bash

BASE_DIR=`cd $(dirname $0); pwd -P`

watchdoghome=/opt/infosec/NetSeal/watchdog
watchdogbin=${watchdoghome}/bin
watchdogconfig=${watchdoghome}/conf
watchdoglib=${watchdoghome}/lib

dmi=`/usr/sbin/dmidecode | grep "Product Name" | grep " X" | awk -F: '{ print $2 }' | sed 's/ //g' | head -1`

gs=${BASE_DIR}/setupfiles/getsensors-$dmi

if [[ ! -e $gs ]];
then
	echo "not support mainboard：$dmi"
	exit -1;
fi

#config input
while [[ $cor != "y" ]]
do
        ./config.sh
        config=`cat config.txt`
        mt=`echo $config | awk -F"<>" '{ print $1 }'`
        chcode=`echo $config | awk -F"<>" '{ print $2 }'`
        mocode=`echo $config | awk -F"<>" '{ print $3 }'`
        sn=`echo $config | awk -F"<>" '{ print $4 }'`
        echo "please conform device info"
        echo "deivce model: "$mt
        echo "host model code: "$chcode
        echo "config code: "$mocode
        echo "device sn: "$sn
        echo -n "is it correct (y/n):"
        read cor
done

tar -xzf ${BASE_DIR}/ipmi.tgz && cd ${BASE_DIR}/ipmi && ./setup.sh && cd .. && rm -rf ipmi

mkdir -p /opt/infosec

if [[ -d $watchdoghome ]];
then
	rm -rf $watchdoghome
	echo "cover watchdog home..."
else
	echo "create watchdog home..."
fi

tar -xzf ${BASE_DIR}/watchdog.tar.gz -C /opt/infosec/NetSeal

cp -rf ${BASE_DIR}/setupfiles/Swatchdog.sh /etc/rc3.d
cp -rf ${BASE_DIR}/setupfiles/Sserver.sh /etc/rc3.d
cp -rf $gs ${watchdogbin}/getsensors
cp -rf ${BASE_DIR}/setupfiles/watchdog.xml.$dmi ${watchdogconfig}/watchdog.xml
cp -rf config.txt ${watchdogconfig}

echo "******************************************"
echo "  install success, please reboot OS!"
echo "******************************************"
