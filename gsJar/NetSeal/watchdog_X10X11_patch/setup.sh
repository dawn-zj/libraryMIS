initpwd=`pwd`

export NETSEAL_HOME=/opt/infosec/NetSeal

echo "Setup NetSeal WatchDog for X10/X11 CentOS6.8"

testos=`uname -a | grep 2.6.32`
testmb=`dmesg | grep -E 'X10|X11'`

if [ "$testos" = "" ]
then
	echo "not support current OS, not install watchdog model"
	rm -rf ${NETSEAL_HOME}/watchdog
	cp -rf Sserver.sh /etc/rc3.d
	exit -1
fi

if [ "$testmb" = "" ]
then
	echo "not support current main board, not install watchdog model"
	rm -rf ${NETSEAL_HOME}/watchdog
	cp -rf Sserver.sh /etc/rc3.d
	exit -2
fi

cor="n"

#config input
while [ $cor != "y" ]
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

#setup ipmi
cd $initpwd
cd ipmi
./setup.sh

#update watchdog
cd $initpwd
cd watchdog
./setup.sh

if [ "$?" != "0" ]
then
        echo "WatchDog install error"
	exit -8
fi

cd ${initpwd}
cp config.txt ${NETSEAL_HOME}/watchdog/conf/.
echo "watchdog install success"

