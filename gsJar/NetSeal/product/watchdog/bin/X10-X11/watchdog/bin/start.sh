export LANG=zh_CN.GBK

HOST_NAME=`hostname`
if [ "`cat /etc/hosts | grep $HOST_NAME`" == "" ];then
	echo "127.0.0.1   $HOST_NAME" >> /etc/hosts
fi

./watchdog start conf/watchdog.xml

exit

