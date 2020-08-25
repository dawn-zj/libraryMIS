watchdoghome=/opt/infosec/NetSeal/watchdog
watchdogbin=${watchdoghome}/bin
watchdogconfig=${watchdoghome}/conf
watchdoglib=${watchdoghome}/lib

dmi=`dmidecode | grep "Product Name" | grep " X" | awk -F: '{ print $2 }' | sed 's/ //g'` 

echo "setup Watchdog..."
echo "main board: "$dmi
gs=getsensors-$dmi

if [ -e $gs ]
then
	cp $gs ${watchdogbin}/getsensors
else
	echo "not support main board: "$dmi
	exit -1
fi

cp Swatchdog.sh /etc/rc3.d

cp watchdog.xml.$dmi $watchdogconfig/watchdog.xml

cp watchdog.jar $watchdoglib
