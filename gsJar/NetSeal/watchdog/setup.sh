#!/bin/bash

BASE_DIR=`cd $(dirname $0); pwd -P`
X10_11_PATH=${BASE_DIR}/bin/X10-X11

echo "Setup NetSeal WatchDog for X10/X11 CentOS6.8"
testos=`uname -a | grep 2.6.32`
testmb=`dmesg | grep -E 'X10|X11'`

if [[ "$testos" = "" ]];
then
	echo "not support current OS, not install watchdog model"
	exit -1
fi

if [[ "$testmb" = "" ]];
then
	echo "not support current main board, not install watchdog model"
	exit -1
fi

#install watchdog model
cd $X10_11_PATH && ./setup.sh

